package com.ubbdevs.studyit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Timetable API", tags = {"Timetable"})
public interface TimeTableControllerApi {

    @ApiOperation(value = "This is just a test methods", response = String.class)
    @ApiResponses(
        @ApiResponse(code = 200, message = "Succesfully retrieved string")
    )
    String justATest();
}
