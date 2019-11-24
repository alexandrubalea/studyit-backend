package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.TimetableEntryDto;
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
    public List<TimetableEntryDto> getTimetableForGroupAndSemigroup(@RequestParam final Integer group,
                                                                    @RequestParam final Integer semigroup) {
        return timetableService.getTimetableForGroupAndSemigroup(group, semigroup);
    }
}
