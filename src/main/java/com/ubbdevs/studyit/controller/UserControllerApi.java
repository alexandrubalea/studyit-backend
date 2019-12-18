package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description = "Users API", tags = {"Users"})
public interface UserControllerApi {

    @ApiOperation(value = "Create a student account", response = StudentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the account"),
            @ApiResponse(code = 400, message = "Invalid data"),
            @ApiResponse(code = 409, message = "Email address already registered")
    })
    StudentDto createStudent(String clientId, StudentCreationDto studentCreationDto);

    @ApiOperation(value = "Get your student account information", response = StudentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve information"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    StudentDto getStudent();

    @ApiOperation(value = "Get your professor account information", response = ProfessorDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve information"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    ProfessorDto getProfessor();

    @ApiOperation(value = "Update your student account information")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated the information"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    void updateStudentInfo(StudentInformationDto studentInformationUpdateDto);

    @ApiOperation(value = "Update your professor account information")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated the information"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    void updateProfessorInfo(ProfessorInformationDto professorInformationDto);

    @ApiOperation(value = "Delete your student account")
    @ApiResponses(value = {
            @ApiResponse(code = 20, message = "Successfully deleted the account"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    void deleteUserAccount();
}
