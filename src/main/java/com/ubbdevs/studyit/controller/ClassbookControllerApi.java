package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.model.enums.ClassType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Classbook API", tags = {"Classbook"})
public interface ClassbookControllerApi {

    @ApiOperation(value = "Get a list of all entries for a student at a given subject",
            response = ClassbookEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned all entries")
    })
    List<ClassbookEntryDto> getListOfClassbookEntriesForStudentBySubjectId(Long subjectId);

    @ApiOperation(value = "Get a list of students with classbook entries for a group at a class (SEMINARY or LABORATORY)",
            response = StudentsEntriesAndFrequencyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned all entries")
    })
    StudentsEntriesAndFrequencyDto getListOfStudentsClassbookEntriesFromGroupAtClass(Long subjectId, String group,
                                                                                     ClassType classType);

    @ApiOperation(value = "Create attendance for student", response = ClassbookEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the attendance")
    })
    ClassbookEntryDto createAttendanceForStudent(CreateAttendanceDto createAttendanceDto);

    @ApiOperation(value = "Update attendance for student", response = ClassbookEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the attendance")
    })
    ClassbookEntryDto updateAttendanceWithIdForStudent(Long id, UpdateAttendanceDto updateAttendanceDto);

    @ApiOperation(value = "Remove attendance")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the attendance")
    })
    void deleteAttendanceById(Long id);

    @ApiOperation(value = "Post a grade for a student", response = ClassbookEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the grade")
    })
    ClassbookEntryDto createGradeForStudent(CreateGradeDto createGradeDto);

    @ApiOperation(value = "Update a grade for a student", response = ClassbookEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the grade")
    })
    ClassbookEntryDto updateGradeWithIdForStudent(Long id, UpdateGradeDto updateGradeDto);

    @ApiOperation(value = "Remove a grade")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the grade")
    })
    void deleteGradeById(Long id);
}
