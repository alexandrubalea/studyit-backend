package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/professors")
public class ProfessorController implements ProfessorControllerApi {

    private final ProfessorService professorService;

    @GetMapping("{id}/subjects")
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectAndClassTypeDto> getAllSubjectsForProfessorWithId(@RequestParam final String clientId, @PathVariable final Long id) {
        return professorService.getAllSubjectsForProfessorWithId(clientId, id);
    }
}
