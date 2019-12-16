package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.dto.SubjectInformationDto;
import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.TimetableEntry;
import com.ubbdevs.studyit.model.enums.Day;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Timetable API", tags = {"Timetable"})
public interface TimetableControllerApi {

    @ApiOperation(value = "Get the timetable for a group", response = TimetableEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved timetable"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    List<TimetableEntryDto> getTimetableForGroupAndSemigroup(Integer group, Integer semigroup);

    @ApiOperation(value = "Get timetable for a professor", response = TimetableEntryDto.class)
    @ApiResponse(code = 200, message = "Successfully retreived timetable")
    List<TimetableEntryDto> getTimetableForProfessor(Long professorID);
    List<TimetableEntryDto> getTimetableForGroup(String group);

    @ApiOperation(value = "Get the timetable for a student. Day is optional, if not given you'll get the timetable " +
            "for all week")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved timetable")
    })
    List<TimetableEntryDto> getStudentTimetableForDay(Day day);

    List<TimetableEntryDto> getProfessorTimetable();


    @ApiOperation(value = "Return subject information", response = SubjectInformationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the subjects, empty list if no subject")
    })
    SubjectInformationDto getSubjectInformation(Long subjectId);
}
