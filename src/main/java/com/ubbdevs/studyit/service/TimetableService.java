package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectInformationDto;
import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.Professor;
import com.ubbdevs.studyit.model.enums.Day;

import java.util.List;

public interface TimetableService {

    SubjectInformationDto getSubjectInformation(Long subjectId);

    void checkIfProfessorTeachesSubject(Long professorId, Long subjectId);

    List<TimetableEntryDto> getTimetableForGroup(String group);

    List<TimetableEntryDto> getStudentTimetableBasedOnDay(final Day day);
    List<TimetableEntryDto> getTimetableForGroupAndSemigroup(Integer group, Integer semigroup);

    List<TimetableEntryDto> getTimetableForProfessor(Long professorID);

    List<SubjectAndClassTypeDto> getAllSubjectsForProfessor(Professor professor);
}

