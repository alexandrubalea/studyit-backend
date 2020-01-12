package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectInformationDto;
import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.entity.TimetableEntry;
import com.ubbdevs.studyit.model.enums.Day;
import io.swagger.annotations.*;

import java.util.List;

@Api(description = "Timetable API", tags = {"Timetable"})
public interface TimetableControllerApi {

    @ApiOperation(value = "Get the timetable for a group", response = TimetableEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the timetable, empty list if no entry")
    })
    List<TimetableEntryDto> getTimetableForGroup(@ApiParam(value = "Group format: 931/1", required = true) String group);

    @ApiOperation(value = "Get the timetable for a professor with given id", response = TimetableEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the timetable, empty list if no entry")
    })
    List<TimetableEntryDto> getTimetableForProfessorOnDay(String clientId,
                                                          @ApiParam(value = "Professor id", required = true) Long id,
                                                          @ApiParam(value = "Optional, if empty returns the timetable " +
                                                                  "for entire week") Day day);

    @ApiOperation(value = "Get the timetable for authenticated student", response = TimetableEntryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the timetable, empty list if no entry")
    })
    List<TimetableEntryDto> getTimetableForStudentOnDay(@ApiParam(value = "Optional, if empty returns the timetable " +
                                                                  "for entire week") Day day);

    @ApiOperation(value = "Return subject information", response = SubjectInformationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned the subjects, empty list if no subject")
    })
    SubjectInformationDto getSubjectInformation(@ApiParam(value = "Subject id", required = true) Long id);
}
