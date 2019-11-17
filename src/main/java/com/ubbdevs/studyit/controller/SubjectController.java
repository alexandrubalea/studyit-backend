package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.mapper.SubjectMapper;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.SubjectType;
import com.ubbdevs.studyit.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/subjects")
public class SubjectController implements SubjectControllerApi {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectDto> getAllSubjectsOfTypeForSemester(@RequestParam(required = false) final SubjectType subjectType,
                                                            @RequestParam(required = false) final Integer semester) {
        return subjectService.getAllSubjectsOfTypeForSemester(subjectType, semester);
    }
}
