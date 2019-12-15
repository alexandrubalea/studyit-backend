package com.ubbdevs.studyit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api(description = "Semester API", tags = {"Semester"})
public interface SemesterControllerApi {

    @ApiOperation(value = "Get semester completion percentage", response = Integer.class)
    @ApiResponse(code = 200, message = "Successfully returned semester completion percentage")
    long getProgressBar(String clientId);
}
