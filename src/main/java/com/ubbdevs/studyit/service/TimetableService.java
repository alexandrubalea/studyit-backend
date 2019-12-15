package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.enums.Day;

import java.util.List;

public interface TimetableService {

    List<TimetableEntryDto> getTimetableForGroup(String group);

    List<TimetableEntryDto> getStudentTimetableBasedOnDay(final Day day);
}

