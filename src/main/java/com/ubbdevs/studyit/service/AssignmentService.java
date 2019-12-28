package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.AssignmentCreationDto;
import com.ubbdevs.studyit.dto.AssignmentDto;

import java.util.List;

public interface AssignmentService {
    AssignmentDto createAssignment(AssignmentCreationDto assignmentCreationDto);
    List<AssignmentDto> getListOfAllAssignments(Long professorId, Long subjectId);
}
