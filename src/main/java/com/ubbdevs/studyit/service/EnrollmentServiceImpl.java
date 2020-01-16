package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.EnrollmentDto;
import com.ubbdevs.studyit.exception.custom.DuplicateResourceException;
import com.ubbdevs.studyit.mapper.EnrollmentMapper;
import com.ubbdevs.studyit.model.entity.Enrollment;
import com.ubbdevs.studyit.model.entity.Student;
import com.ubbdevs.studyit.model.entity.Subject;
import com.ubbdevs.studyit.repository.EnrollmentRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Setter
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final SubjectService subjectService;
    private final EnrollmentMapper enrollmentMapper;
    private final AuthorizationService authorizationService;
    private final DepartmentSubjectService departmentSubjectService;
    private final DepartmentService departmentService;

    @Transactional
    public List<EnrollmentDto> enrollStudentAtSubject(final Student student, final EnrollStudentDto enrollStudentDto) {
        return enrollStudentDto.getSubjects()
                .stream()
                .map(subjectId -> checkIfStudentIsAlreadyEnrolledAndEnrollStudentIfNot(student,
                        subjectService.getSubjectById(subjectId)))
                .collect(Collectors.toList());
    }

    public List<EnrollmentDto> getAllStudentEnrollments(final Student student) {
        return enrollmentRepository.findAllByStudent_Id(student.getId())
                .stream()
                .map(enrollmentMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void enrollStudentAtMandatorySubjects(final Student student) {
        final Long departmentId = departmentService.getDepartmentByGroup(student.getGroup()).getId();
        departmentSubjectService
                .getMandatorySubjectsForDepartmentId(departmentId)
                .forEach(departmentSubject -> enrollStudentAtSubject(student, departmentSubject.getSubject()));
    }

    @Transactional
    public void deleteStudentEnrollment(Long enrollmentId) {
        final Long studentId = authorizationService.getUserId();
        enrollmentRepository.deleteByIdAndStudent_Id(enrollmentId, studentId);
    }

    private EnrollmentDto checkIfStudentIsAlreadyEnrolledAndEnrollStudentIfNot(final Student student, final Subject subject) {
        checkIfEnrollmentForStudentAtSubjectExists(student.getId(), subject.getId());
        return enrollmentMapper.modelToDto(enrollStudentAtSubject(student, subject));
    }

    private Enrollment enrollStudentAtSubject(final Student student, final Subject subject) {
        return enrollmentRepository.save(createEnrollmentForStudentAtSubject(student, subject));
    }

    private Enrollment createEnrollmentForStudentAtSubject(final Student student, final Subject subject) {
        return Enrollment.builder()
                .student(student)
                .subject(subject)
                .build();
    }

    private void checkIfEnrollmentForStudentAtSubjectExists(final Long studentId, final Long subjectId) {
        enrollmentRepository.findByStudent_IdAndSubject_Id(studentId, subjectId)
                .ifPresent(enrollment -> {
                    throw new DuplicateResourceException("Student with id " + studentId + " is already enrolled at " +
                            "subject with id " + subjectId);
                });
    }
}
