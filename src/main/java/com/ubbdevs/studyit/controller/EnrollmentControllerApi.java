package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Enrollment API", tags = {"Enrollment"})
public interface EnrollmentControllerApi {

    @ApiOperation(value = "Enroll student at multiple subjects", response = SubjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully enrolled student at subjects. Returns list of subjects " +
                    "that student is enrolled at"),
    })
    List<SubjectDto> enrollStudentAtSubject(EnrollStudentDto enrollStudentDto);

    @ApiOperation(value = "Get a list of your enrollments", response = SubjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of your enrollments"),
    })
    List<SubjectDto> getStudentEnrollments();

    @ApiOperation(value = "Delete an enrollment", response = SubjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of your enrollments"),
    })
    void deleteStudentEnrollment(Long id);
}
