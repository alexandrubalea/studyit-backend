package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.AssignmentCreationDto;
import com.ubbdevs.studyit.dto.AssignmentDto;
import com.ubbdevs.studyit.mapper.AssignmentMapper;
import com.ubbdevs.studyit.model.entity.Assignment;
import com.ubbdevs.studyit.repository.AssignmentRepository;
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

    public AssignmentDto createAssignment(AssignmentCreationDto assignmentCreationDto) {
        final Assignment assignment = assignmentMapper.dtoToModel(assignmentCreationDto);
        return assignmentMapper.modelToDto(assignmentRepository.save(assignment));
    }

    public List<AssignmentDto> getListOfAllAssignments(final Long professorId, final Long subjectId) {
        List<Assignment> assignments;
        if (professorId == null) {
            assignments = assignmentRepository.findBySubjectId(subjectId);
        }
        else {
            userService.getProfessorById(professorId);
            assignments = assignmentRepository.findByProfessorId(professorId);
        }
        return assignments
                .stream()
                .map(assignmentMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
