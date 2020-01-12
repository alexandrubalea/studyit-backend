package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.ProfessorWithClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectInformationDto;
import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.exception.custom.UnauthorizedException;
import com.ubbdevs.studyit.mapper.GroupMapper;
import com.ubbdevs.studyit.mapper.ProfessorMapper;
import com.ubbdevs.studyit.mapper.TimetableEntryMapper;
import com.ubbdevs.studyit.model.*;
import com.ubbdevs.studyit.model.enums.ClassType;
import com.ubbdevs.studyit.model.enums.Day;
import com.ubbdevs.studyit.repository.TimetableRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationService;
import com.ubbdevs.studyit.validator.TimetableValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;

    private final UserService userService;
    private final DepartmentService departmentService;
    private final AuthorizationService authorizationService;
    private final SubjectService subjectService;

    private final GroupMapper groupMapper;
    private final TimetableEntryMapper timetableEntryMapper;
    private final ProfessorMapper professorMapper;

    private final TimetableValidator timetableValidator;

    @Override
    public SubjectInformationDto getSubjectInformation(Long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        Map<Professor, List<ClassType>> professors = getAMapOfProfessorsWithClassTypesFromList(
                timetableRepository.getDistinctClassTypeAndProfessorId(subject));
        List<ProfessorWithClassTypeDto> professorWithClassTypeDtos = professors.entrySet()
                .stream()
                .map(professor -> ProfessorWithClassTypeDto.builder()
                        .professor(professorMapper.modelToDto(professor.getKey()))
                        .classTypes(professor.getValue())
                        .build())
                .collect(Collectors.toList());
        return SubjectInformationDto.builder()
                .subject(subject)
                .professors(professorWithClassTypeDtos)
                .build();
    }

    private Map<Professor, List<ClassType>> getAMapOfProfessorsWithClassTypesFromList(List<ProfessorWithClassType> professorWithClassTypes) {
        return professorWithClassTypes
                .stream()
                .collect(Collectors.groupingBy(ProfessorWithClassType::getProfessor,
                        Collectors.mapping(ProfessorWithClassType::getClassType, Collectors.toList())));
    }

    @Override
    public void checkIfProfessorTeacherSubject(Long professorId, Long subjectId) {
        timetableRepository.findFirstByProfessor_IdAndSubject_Id(professorId, subjectId)
                .orElseThrow(() -> {
                    throw new UnauthorizedException("Professor with id " + professorId + " does not teach subject " +
                            "with id " + subjectId);
                });
    }

    public List<TimetableEntryDto> getTimetableForGroup(final String group) {
        timetableValidator.validateGroup(group);
        final Group mappedGroup = groupMapper.dtoToModel(group);
        return timetableRepository.getByFormationIn(departmentService.getFormationFromGroup(mappedGroup))
                .stream()
                .map(timetableEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public List<TimetableEntryDto> getStudentTimetableBasedOnDay(Day day) {
        final Long studentId = authorizationService.getUserId();
        final Student student = userService.getStudentById(studentId);
        final List<String> formation = departmentService.getFormationFromGroup(student.getGroup());
        List<Long> subjectsIds = getStudentEnrollmentSubjectIds(studentId);
        List<TimetableEntry> timetable;
        if (day == null)
            timetable = getStudentWeeklyTimetable(formation, subjectsIds);
        else
            timetable = getStudentDailyTimetable(formation, subjectsIds);
        return timetable
                .stream()
                .map(timetableEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }

    private List<Long> getStudentEnrollmentSubjectIds(final Long studentId) {
        return userService.getStudentById(studentId).getEnrollments().stream()
                .map(enrollment -> enrollment.getSubject().getId())
                .collect(Collectors.toList());
    }

    private List<TimetableEntry> getStudentWeeklyTimetable(final List<String> formation, final Collection subjects) {
        return timetableRepository.getByFormationInAndSubject_IdIn(formation, subjects);
    }

    private List<TimetableEntry> getStudentDailyTimetable(final List<String> formation, final Collection subjects) {
        return timetableRepository.getByFormationInAndSubject_IdIn(formation, subjects);
    }
}
