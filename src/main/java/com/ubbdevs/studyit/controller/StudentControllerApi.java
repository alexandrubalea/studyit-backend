package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.EnrollmentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Students API", tags = {"Students"})
public interface StudentControllerApi {

    @ApiOperation(value = "Enroll student at multiple subjects", response = EnrollmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully enrolled student at subjects. Returns list of subjects " +
                    "that student is enrolled at"),
    })
    List<EnrollmentDto> enrollStudentAtSubject(EnrollStudentDto enrollStudentDto);

    @ApiOperation(value = "Get a list of your enrollments", response = EnrollmentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of your enrollments"),
    })
    List<EnrollmentDto> getStudentEnrollments();

    @ApiOperation(value = "Delete an enrollment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns a list of your enrollments"),
    })
    void deleteStudentEnrollment(Long id);
}
