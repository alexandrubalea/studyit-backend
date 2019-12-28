package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.TimetableEntry;
import com.ubbdevs.studyit.model.enums.Day;

import java.util.List;
import java.util.Optional;

public interface TimetableService {

    void checkIfProfessorTeacherSubject(Long professorId, Long subjectId);
    List<TimetableEntryDto> getTimetableForGroup(String group);
    List<TimetableEntryDto> getStudentTimetableBasedOnDay(final Day day);
}

