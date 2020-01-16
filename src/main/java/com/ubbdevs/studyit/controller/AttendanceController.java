package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.AttendanceDto;
import com.ubbdevs.studyit.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/attendances")
public class AttendanceController implements AttendanceControllerApi {

    private final AttendanceService attendanceService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AttendanceDto> getListOfAllAttendances(Long professorId, Long subjectId) {

        return attendanceService.getListOfAllAttendances(professorId,subjectId);
    }
}
