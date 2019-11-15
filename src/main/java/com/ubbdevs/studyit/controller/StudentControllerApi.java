package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.StudentCreatedDto;
import com.ubbdevs.studyit.dto.StudentCreationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Student API", tags = {"Student"})
public interface StudentControllerApi {

    @ApiOperation(value = "Create a student account")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the account"),
            @ApiResponse(code = 400, message = "Invalid data"),
            @ApiResponse(code = 409, message = "Email address already registered")
    })
    StudentCreatedDto createStudent(StudentCreationDto studentCreationDto);
}
