package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.TimetableEntryDto;

import java.util.List;

public interface TimetableService {

    List<TimetableEntryDto> getTimetableForGroupAndSemigroup(Integer group, Integer semigroup);
}

