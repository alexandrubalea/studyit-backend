package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.exception.custom.InvalidDataException;
import com.ubbdevs.studyit.exception.custom.ResourceNotFoundException;
import com.ubbdevs.studyit.mapper.ClassbookEntryMapper;
import com.ubbdevs.studyit.mapper.GroupMapper;
import com.ubbdevs.studyit.model.Group;
import com.ubbdevs.studyit.model.entity.ClassbookEntry;
import com.ubbdevs.studyit.model.entity.Student;
import com.ubbdevs.studyit.model.enums.ClassType;
import com.ubbdevs.studyit.repository.ClassbookRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassbookServiceImpl implements ClassbookService {

    private final GroupMapper groupMapper;
    private final ClassbookEntryMapper classbookEntryMapper;

    private final UserService userService;
    private final SubjectService subjectService;
    private final TimetableService timetableService;
    private final AuthorizationService authorizationService;

    private final ClassbookRepository classbookRepository;

    @Override
    public List<ClassbookEntryDto> getListOfClassbookEntriesForStudentBySubjectId(final Long subjectId) {
        final Long studentId = authorizationService.getUserId();
        return classbookRepository.findAllBySubjectIdAndStudentId(subjectId, studentId)
                .stream()
                .map(classbookEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentsEntriesAndFrequencyDto getListOfStudentsClassbookEntriesFromGroupAtClass(final Long subjectId,
                                                                                                  final String group,
                                                                                                  final ClassType classType) {
        if (classType.equals(ClassType.COURSE))
            throw new InvalidDataException("Cannot query for classType COURSE");
        final Group mappedGroup = groupMapper.dtoToModel(group);
        final int classFrequency = timetableService.checkFrequencyForClassTypeAndSubjectId(classType, subjectId);
        return StudentsEntriesAndFrequencyDto.builder()
                .classFrequency(classFrequency)
                .students(userService.getAllStudentsFromGroup(mappedGroup, mappedGroup.isOnlyGroup())
                        .stream()
                        .map(student -> createStudentEntriesDtoForStudent(subjectId, classType, student))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ClassbookEntryDto createAttendanceForStudent(final CreateAttendanceDto createAttendanceDto) {
        if (createAttendanceDto.getClassType().equals(ClassType.COURSE))
            throw new InvalidDataException("Course attendance not supported");
        subjectService.getSubjectById(createAttendanceDto.getSubjectId());
        userService.getStudentById(createAttendanceDto.getStudentId());
        final Long professorId = authorizationService.getUserId();
        final Optional<ClassbookEntry> classbookEntry = findClassbookEntry(createAttendanceDto.getSubjectId(),
                createAttendanceDto.getStudentId(), createAttendanceDto.getClassType(),
                createAttendanceDto.getClassNumber());
        if (classbookEntry.isEmpty())
            return classbookEntryMapper.modelToDto(
                    classbookRepository.save(createClassbookEntryForStudentFromAttendance(createAttendanceDto, professorId)));
        else {
            classbookEntry.get().setAttendanceType(createAttendanceDto.getAttendanceType());
            return classbookEntryMapper.modelToDto(classbookRepository.save(classbookEntry.get()));
        }
    }

    @Override
    public ClassbookEntryDto updateAttendanceWithIdForStudent(final Long id,
                                                              final UpdateAttendanceDto updateAttendanceDto) {
        final ClassbookEntry classbookEntry = findClassbookEntryById(id);
        authorizationService.validateCanModifyUserWithId(classbookEntry.getProfessorId());
        classbookEntry.setAttendanceType(updateAttendanceDto.getAttendanceType());
        return classbookEntryMapper.modelToDto(classbookRepository.save(classbookEntry));
    }

    @Override
    public void deleteAttendanceById(final Long id) {
        final ClassbookEntry classbookEntry = findClassbookEntryById(id);
        authorizationService.validateCanModifyUserWithId(classbookEntry.getProfessorId());
        if (classbookEntry.getGrade() == null)
            classbookRepository.deleteById(id);
        else {
            classbookEntry.setAttendanceType(null);
            classbookRepository.save(classbookEntry);
        }
    }

    @Override
    public ClassbookEntryDto createGradeForStudent(final CreateGradeDto createGradeDto) {
        if (createGradeDto.getClassType().equals(ClassType.COURSE))
            throw new InvalidDataException("Course grading not supported");
        subjectService.getSubjectById(createGradeDto.getSubjectId());
        userService.getStudentById(createGradeDto.getStudentId());
        final Long professorId = authorizationService.getUserId();
        final Optional<ClassbookEntry> classbookEntry = findClassbookEntry(createGradeDto.getSubjectId(),
                createGradeDto.getStudentId(), createGradeDto.getClassType(), createGradeDto.getClassNumber());
        if (classbookEntry.isEmpty())
            return classbookEntryMapper.modelToDto(
                    classbookRepository.save(createClassbookEntryForStudentFromGrade(createGradeDto, professorId)));
        else {
            classbookEntry.get().setGrade(createGradeDto.getGrade());
            return classbookEntryMapper.modelToDto(classbookRepository.save(classbookEntry.get()));
        }
    }

    @Override
    public ClassbookEntryDto updateGradeWithIdForStudent(final Long id, final UpdateGradeDto updateGradeDto) {
        final ClassbookEntry classbookEntry = findClassbookEntryById(id);
        authorizationService.validateCanModifyUserWithId(classbookEntry.getProfessorId());
        classbookEntry.setGrade(updateGradeDto.getGrade());
        return classbookEntryMapper.modelToDto(classbookRepository.save(classbookEntry));
    }

    @Override
    public void deleteGradeById(Long id) {
        final ClassbookEntry classbookEntry = findClassbookEntryById(id);
        authorizationService.validateCanModifyUserWithId(classbookEntry.getProfessorId());
        if (classbookEntry.getAttendanceType() == null)
            classbookRepository.deleteById(id);
        else {
            classbookEntry.setGrade(null);
            classbookRepository.save(classbookEntry);
        }
    }

    private StudentEntriesDto createStudentEntriesDtoForStudent(final Long subjectId, final ClassType classType,
                                                                final Student student) {
        return StudentEntriesDto.builder()
                .student(StudentNameDto.builder()
                        .id(student.getId())
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .build())
                .entries(getListOfClassbookEntriesForStudentBySubjectIdAndClassType(subjectId, student.getId(), classType))
                .build();
    }

    private List<ClassbookEntryDto> getListOfClassbookEntriesForStudentBySubjectIdAndClassType(final Long subjectId,
                                                                                               final Long studentId,
                                                                                               final ClassType classType) {
        return classbookRepository.findAllBySubjectIdAndStudentIdAndClassType(subjectId, studentId, classType)
                .stream()
                .map(classbookEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }

    private ClassbookEntry createClassbookEntryForStudentFromAttendance(final CreateAttendanceDto createAttendanceDto,
                                                                        final Long professorId) {
        return ClassbookEntry.builder()
                .subjectId(createAttendanceDto.getSubjectId())
                .studentId(createAttendanceDto.getStudentId())
                .professorId(professorId)
                .classType(createAttendanceDto.getClassType())
                .number(createAttendanceDto.getClassNumber())
                .attendanceType(createAttendanceDto.getAttendanceType())
                .build();
    }

    private ClassbookEntry createClassbookEntryForStudentFromGrade(final CreateGradeDto createGradeDto,
                                                                   final Long professorId) {
        return ClassbookEntry.builder()
                .subjectId(createGradeDto.getSubjectId())
                .studentId(createGradeDto.getStudentId())
                .professorId(professorId)
                .classType(createGradeDto.getClassType())
                .number(createGradeDto.getClassNumber())
                .grade(createGradeDto.getGrade())
                .build();
    }

    private ClassbookEntry findClassbookEntryById(final Long id) {
        return classbookRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Entry with id " + id + " was not found");
                });
    }

    private Optional<ClassbookEntry> findClassbookEntry(final Long subjectId, final Long studentId,
                                                        final ClassType classType, final Integer number) {
        return classbookRepository.findBySubjectIdAndStudentIdAndClassTypeAndNumber(subjectId, studentId, classType, number);
    }
}
