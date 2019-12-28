package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.AssignmentCreationDto;
import com.ubbdevs.studyit.dto.AssignmentDto;
import com.ubbdevs.studyit.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/assignments")
public class AssignmentController implements AssignmentControllerApi {

    private final AssignmentService assignmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssignmentDto createAssignment(final AssignmentCreationDto assignmentCreationDto) {
        return assignmentService.createAssignment(assignmentCreationDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AssignmentDto> getListOfAllAssignments(@RequestParam Long professorId, @RequestParam Long subjectId) {
        return assignmentService.getListOfAllAssignments(professorId,subjectId);
    }
}
