package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.dto.SubjectInformationDto;
import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.enums.Day;
import com.ubbdevs.studyit.service.TimetableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@AllArgsConstructor
public class TimetableController implements TimetableControllerApi {

    private final TimetableService timetableService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TimetableEntryDto> getTimetableForGroup(@RequestParam final String group) {
        return timetableService.getTimetableForGroup(group);
    }

    @GetMapping("/student/me")
    @ResponseStatus(HttpStatus.OK)
    public List<TimetableEntryDto> getStudentTimetableForDay(@RequestParam(required = false) final Day day) {
        return timetableService.getStudentTimetableBasedOnDay(day);
    }

    @Override
    public List<TimetableEntryDto> getProfessorTimetable() {
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TimetableEntryDto> getTimetableForProfessor(@RequestParam final Long professorID) {
        return timetableService.getTimetableForProfessor(professorID);
    }


    @GetMapping("/subject/{subjectId}/info")
    public SubjectInformationDto getSubjectInformation(@PathVariable Long subjectId) {
        return timetableService.getSubjectInformation(subjectId);
    }
}
