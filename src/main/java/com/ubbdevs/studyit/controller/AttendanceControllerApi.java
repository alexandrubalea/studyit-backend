package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.AttendanceDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Attendances API", tags = {"Attendance"})
public interface AttendanceControllerApi {
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the attendance, empty list if no attendance that " +
                    "belong to the given parameters")
    })
    List<AttendanceDto> getListOfAllAttendances(Long professorId, Long subjectId);
}
