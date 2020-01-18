package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.AssignmentCreationDto;
import com.ubbdevs.studyit.dto.AssignmentDto;
import io.swagger.annotations.*;

import java.util.List;

@Api(description = "Assignments API", tags = {"Assignments"})
public interface AssignmentControllerApi {

    @ApiOperation(value = "Create an assignment", response = AssignmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the assignment"),
            @ApiResponse(code = 400, message = "Invalid data"),
            @ApiResponse(code = 409, message = "Assignment already registered")
    })
    AssignmentDto createAssignment(AssignmentCreationDto assignmentCreationDto);

    @ApiOperation(value = "Get the list of all assignments", response = AssignmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the assignments, empty list if no assignments that " +
                    "belong to the given parameters")
    })
    List<AssignmentDto> getListOfAllAssignments(Long professorId,
                                                @ApiParam(value = "Optional, if empty returns all the assignments " +
                                                        "for the subject")Long subjectId);
}
