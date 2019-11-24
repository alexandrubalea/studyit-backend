package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.TimetableEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Timetable API", tags = {"Timetable"})
public interface TimetableControllerApi {

    @ApiOperation(value = "Get the timetable for a group", response = TimetableEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved timetable"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    List<TimetableEntryDto> getTimetableForGroupAndSemigroup(Integer group, Integer semigroup);
}
