package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.SubjectType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Subject API", tags = {"Subject"})
public interface SubjectControllerApi {

    @ApiOperation(value = "List of all subjects of a given type from a given semester", response = SubjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the subjects, empty list if no subject was found")
    })
    List<SubjectDto> getAllSubjectsOfTypeForSemester(SubjectType subjectType, Integer semester);
}
