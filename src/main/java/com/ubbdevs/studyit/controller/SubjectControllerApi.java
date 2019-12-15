package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.enums.SubjectType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Subject API", tags = {"Subject"})
public interface SubjectControllerApi {

    @ApiOperation(value = "List of all subjects with name starting with given parameter. If no parameter is given " +
            "returns all subjects", response = SubjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the subjects, empty list if no subject that " +
                    "starts with given parameter was found")
    })
    List<SubjectDto> getAllSubjectsStartingWith(String startsWith);
}
