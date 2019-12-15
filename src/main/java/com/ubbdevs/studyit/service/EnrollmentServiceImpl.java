package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.EnrollmentDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.exception.custom.DuplicateResourceException;
import com.ubbdevs.studyit.mapper.EnrollmentMapper;
import com.ubbdevs.studyit.mapper.SubjectMapper;
import com.ubbdevs.studyit.model.Enrollment;
import com.ubbdevs.studyit.model.Student;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.repository.EnrollmentRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;
    private final EnrollmentMapper enrollmentMapper;
    private final AuthorizationService authorizationService;

    @Transactional
    public List<EnrollmentDto> enrollStudentAtSubject(EnrollStudentDto enrollStudentDto) {
        final Long studentId = authorizationService.getUserId();
        return enrollStudentDto.getSubjects()
                .stream()
                .map(subjectId -> checkIfStudentIsAlreadyEnrolledAndEnrollStudentIfNot(studentId, subjectId))
                .collect(Collectors.toList());
    }

    public List<EnrollmentDto> getAllStudentEnrollments() {
        final Long studentId = authorizationService.getUserId();
        return userService.getStudentById(studentId).getEnrollments()
                .stream()
                .map(enrollmentMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteStudentEnrollment(Long enrollmentId) {
        final Long studentId = authorizationService.getUserId();
        enrollmentRepository.deleteByIdAndStudent_Id(enrollmentId, studentId);
    }

    private EnrollmentDto checkIfStudentIsAlreadyEnrolledAndEnrollStudentIfNot(Long studentId, Long subjectId) {
        checkIfEnrollmentForStudentAtSubjectExists(studentId, subjectId);
        return enrollmentMapper.modelToDto(enrollStudentAtSubject(studentId, subjectId));
    }

    private Enrollment enrollStudentAtSubject(final Long studentId, final Long subjectId) {
        return enrollmentRepository.save(createEnrollmentForStudentAtSubject(userService.getStudentById(studentId),
                subjectService.getSubjectById(subjectId)));
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
