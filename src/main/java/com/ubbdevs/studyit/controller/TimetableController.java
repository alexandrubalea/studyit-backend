package com.ubbdevs.studyit.controller;

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

    @GetMapping("/professors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TimetableEntryDto> getTimetableForProfessorOnDay(@RequestParam final String clientId,
                                                                 @PathVariable final Long id,
                                                                 @RequestParam(required = false) final Day day) {
        return timetableService.getTimetableForProfessorBasedOnDay(clientId, id, day);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<TimetableEntryDto> getTimetableForStudentOnDay(@RequestParam(required = false) final Day day) {
        return timetableService.getTimetableForStudentBasedOnDay(day);
    }

    @GetMapping("/subject/{id}/info")
    @ResponseStatus(HttpStatus.OK)
    public SubjectInformationDto getSubjectInformation(@PathVariable final Long id) {
        return timetableService.getSubjectInformation(id);
    }
}
