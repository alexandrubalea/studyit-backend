package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Professors API", tags = {"Professors"})
public interface ProfessorControllerApi {

    @ApiOperation(value = "Returns all subjects that are teached by the professor with given id (COURSE, SEMINARY or" +
            " LABORATORY) or empty list if there are no subjects.", response = SubjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returns all the subjects"),
    })
    List<SubjectAndClassTypeDto> getAllSubjectsForProfessorWithId(String clientId, Long id);
}
