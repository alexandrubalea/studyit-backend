package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.AssignmentCreationDto;
import com.ubbdevs.studyit.dto.AssignmentDto;
import com.ubbdevs.studyit.mapper.AssignmentMapper;
import com.ubbdevs.studyit.model.entity.Assignment;
import com.ubbdevs.studyit.repository.AssignmentRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final UserService userService;
    private final AuthorizationServiceImpl authorizationService;

    public AssignmentDto createAssignment(AssignmentCreationDto assignmentCreationDto) {
        final Long professorId = authorizationService.getUserId();
        final Assignment assignment = assignmentMapper.dtoToModel(assignmentCreationDto);
        assignment.setProfessorId(professorId);
        return assignmentMapper.modelToDto(assignmentRepository.save(assignment));
    }

    public List<AssignmentDto> getListOfAllAssignments(Long professorId, Long subjectId) {
        List<Assignment> assignments;
        if (professorId != null) {
            userService.getProfessorById(professorId);
            assignments = assignmentRepository.findByProfessorIdAndSubjectId(professorId, subjectId);
        } else
            assignments = assignmentRepository.findBySubjectId(subjectId);
        return assignments.stream()
                .map(assignmentMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
