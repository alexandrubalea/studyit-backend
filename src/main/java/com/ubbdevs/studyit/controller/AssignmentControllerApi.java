package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.AssignmentCreationDto;
import com.ubbdevs.studyit.dto.AssignmentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Assignment API", tags = {"Assignment"})
public interface AssignmentControllerApi {
@Api(description = "Assignments API", tags = {"Assignments"})
public interface AssignmentControllerApi {

    @ApiOperation(value = "Create an assignment", response = AssignmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the assignment"),
            @ApiResponse(code = 400, message = "Invalid data"),
            @ApiResponse(code = 409, message = "Assignment already registered")
    })
    AssignmentDto createAssignment(AssignmentCreationDto assignmentCreationDto);

    @ApiOperation(value = "List of all assignments given by the professor with the professorId to the subject with subjectId " +
            "returns all assignments", response = AssignmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the assignments, empty list if no assignments that " +
                    "belong to the given parameters")
    })
    List<AssignmentDto> getListOfAllAssignments(Long professorId, Long subjectId);
}
