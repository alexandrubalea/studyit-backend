package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.AttendanceDto;

import java.util.List;

public interface AttendanceService {
    List<AttendanceDto> getListOfAllAttendances(Long professorId, Long subjectId);
}
