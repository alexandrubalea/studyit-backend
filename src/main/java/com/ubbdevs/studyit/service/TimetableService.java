package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectInformationDto;
import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.enums.Day;

import java.util.List;

public interface TimetableService {

    void checkIfProfessorTeachesSubject(Long professorId, Long subjectId);

    List<TimetableEntryDto> getTimetableForGroup(String group);

    SubjectInformationDto getSubjectInformation(Long subjectId);

    List<SubjectAndClassTypeDto> getAllSubjectsForProfessor(Professor professor);

    List<TimetableEntryDto> getTimetableForProfessorBasedOnDay(String clientId, Long id, Day day);

    List<TimetableEntryDto> getTimetableForStudentBasedOnDay(Day day);
}

