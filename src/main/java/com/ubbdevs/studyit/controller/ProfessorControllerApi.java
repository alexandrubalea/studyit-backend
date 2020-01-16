package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import io.swagger.annotations.*;

import java.util.List;

@Api(description = "Professors API", tags = {"Professors"})
public interface ProfessorControllerApi {

    @ApiOperation(value = "Returns all subjects that are teached by the professor with given id (COURSE, SEMINARY or" +
            " LABORATORY)", response = SubjectAndClassTypeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returns all the subjects, empty list if none"),
    })
    List<SubjectAndClassTypeDto> getAllSubjectsForProfessorWithId(String clientId,
                                                                  @ApiParam(value = "Professor id", required = true) Long id);
}
