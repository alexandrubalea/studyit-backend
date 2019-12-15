package com.ubbdevs.studyit.controller;

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
        List<TimetableEntryDto> timetable = timetableService.getStudentTimetableBasedOnDay(day);
        return timetable;
    }

    @Override
    public List<TimetableEntryDto> getProfessorTimetable() {
        return null;
    }
}
