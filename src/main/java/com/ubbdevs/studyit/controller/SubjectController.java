package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.enums.SubjectType;
import com.ubbdevs.studyit.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/subjects")
public class SubjectController implements SubjectControllerApi {

    private final SubjectService subjectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectDto> getAllSubjectsStartingWith(@RequestParam(required = false) final String startsWith) {
        return subjectService.getAllSubjectsStartingWith(startsWith);
    }
}
