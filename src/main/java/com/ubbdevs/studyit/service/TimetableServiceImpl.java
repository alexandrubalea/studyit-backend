package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.ProfessorAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectInformationDto;
import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.exception.custom.ResourceNotFoundException;
import com.ubbdevs.studyit.mapper.GroupMapper;
import com.ubbdevs.studyit.mapper.ProfessorMapper;
import com.ubbdevs.studyit.mapper.SubjectMapper;
import com.ubbdevs.studyit.mapper.TimetableEntryMapper;
import com.ubbdevs.studyit.model.Group;
import com.ubbdevs.studyit.model.ProfessorAndClassType;
import com.ubbdevs.studyit.model.SubjectAndClassType;
import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.entity.Student;
import com.ubbdevs.studyit.model.entity.Subject;
import com.ubbdevs.studyit.model.entity.TimetableEntry;
import com.ubbdevs.studyit.model.enums.ClassType;
import com.ubbdevs.studyit.model.enums.Day;
import com.ubbdevs.studyit.model.enums.Frequency;
import com.ubbdevs.studyit.model.enums.Role;
import com.ubbdevs.studyit.repository.TimetableRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationService;
import com.ubbdevs.studyit.validator.TimetableValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final GroupMapper groupMapper;
    private final SubjectMapper subjectMapper;
    private final ProfessorMapper professorMapper;
    private final TimetableEntryMapper timetableEntryMapper;

    private final TimetableValidator timetableValidator;

    private final UserService userService;
    private final SubjectService subjectService;
    private final DepartmentService departmentService;
    private final AuthorizationService authorizationService;
    private final ClientDetailsAdaptorService clientDetailsAdaptorService;

    private final TimetableRepository timetableRepository;

    @Override
    public void checkIfProfessorTeachesSubject(Long professorId, Long subjectId) {
        timetableRepository.findFirstByProfessor_IdAndSubject_Id(professorId, subjectId)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Professor with id " + professorId + " does not teach subject " +
                            "with id " + subjectId);
                });
    }

    @Override
    public int checkFrequencyForClassTypeAndSubjectId(final ClassType classType, final Long subjectId) {
        final Frequency frequency = timetableRepository.findFirstByClassTypeAndSubjectId(classType, subjectId)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Subject with id " + subjectId + " does not have class type " +
                            classType);
                }).getFrequency();
        return frequency.equals(Frequency.BOTH) ? 14 : 7;
    }

    @Override
    public List<TimetableEntryDto> getTimetableForGroup(final String group) {
        timetableValidator.validateGroup(group);
        final Group mappedGroup = groupMapper.dtoToModel(group);
        return timetableRepository.getByFormationIn(departmentService.getFormationFromGroup(mappedGroup))
                .stream()
                .map(timetableEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectInformationDto getSubjectInformation(final Long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        List<ProfessorAndClassTypeDto> professors = mapProfessorToClassType(timetableRepository
                .getDistinctClassTypeAndProfessorId(subject))
                .entrySet()
                .stream()
                .map(professor -> professorMapper.modelToDto(professor.getKey(), professor.getValue()))
                .collect(Collectors.toList());
        return SubjectInformationDto.builder()
                .subject(subject)
                .professors(professors)
                .build();
    }

    @Override
    public List<SubjectAndClassTypeDto> getAllSubjectsForProfessor(final Professor professor) {
        return mapSubjectToClassType(timetableRepository.getDistinctSubjectsForProfessorId(professor))
                .entrySet()
                .stream()
                .map(subject -> subjectMapper.modelToDto(subject.getKey(), subject.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableEntryDto> getTimetableForProfessorBasedOnDay(final String clientId, final Long id, final Day day) {
        clientDetailsAdaptorService.validateClientId(clientId);
        userService.getProfessorById(id);
        List<TimetableEntry> timetable;
        if (day == null)
            timetable = getWeeklyTimetableForProfessor(id);
        else
            timetable = getDailyTimetableForProfessor(id, day);
        return timetable
                .stream()
                .map(timetableEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableEntryDto> getTimetableForStudentBasedOnDay(final Day day) {
        final Long id = authorizationService.getUserId();
        final Student student = userService.getStudentById(id);
        final List<String> formation = departmentService.getFormationFromGroup(student.getGroup());
        List<Long> subjectsIds = getStudentEnrollmentSubjectIds(id);
        List<TimetableEntry> timetable;
        if (day == null)
            timetable = getWeeklyTimetableForFormation(formation, subjectsIds);
        else
            timetable = getDailyTimetableForFormation(formation, subjectsIds, day);
        return timetable
                .stream()
                .map(timetableEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }

    private Map<Professor, List<ClassType>> mapProfessorToClassType(List<ProfessorAndClassType> professors) {
        return professors
                .stream()
                .collect(Collectors.groupingBy(ProfessorAndClassType::getProfessor,
                        Collectors.mapping(ProfessorAndClassType::getClassType, Collectors.toList())));
    }

    private Map<Subject, List<ClassType>> mapSubjectToClassType(final List<SubjectAndClassType> subjects) {
        return subjects
                .stream()
                .collect(Collectors.groupingBy(SubjectAndClassType::getSubject,
                        Collectors.mapping(SubjectAndClassType::getClassType, Collectors.toList())));
    }

    private List<Long> getStudentEnrollmentSubjectIds(final Long studentId) {
        return userService.getStudentById(studentId).getEnrollments().stream()
                .map(enrollment -> enrollment.getSubject().getId())
                .collect(Collectors.toList());
    }

    private List<TimetableEntry> getWeeklyTimetableForFormation(final List<String> formation, final Collection subjects) {
        return timetableRepository.getByFormationInAndSubjectIdIn(formation, subjects);
    }

    private List<TimetableEntry> getDailyTimetableForFormation(final List<String> formation, final Collection subjects,
                                                               final Day day) {
        return timetableRepository.getByFormationInAndSubjectIdInAndDay(formation, subjects, day);
    }

    private List<TimetableEntry> getWeeklyTimetableForProfessor(final Long id) {
        return timetableRepository.getByProfessorId(id);
    }

    private List<TimetableEntry> getDailyTimetableForProfessor(final Long id, final Day day) {
        return timetableRepository.getByProfessorIdAndDay(id, day);
    }
}
