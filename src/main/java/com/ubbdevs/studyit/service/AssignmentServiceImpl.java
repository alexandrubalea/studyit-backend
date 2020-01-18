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

    public List<AssignmentDto> getListOfAllAssignments(Long professorId, Long subjectId) {
        if(professorId != null) {
            userService.getProfessorById(professorId);
            return assignmentRepository.findByProfessorIdAndSubjectId(professorId, subjectId)
                    .stream()
                    .map(assignmentMapper::modelToDto)
                    .collect(Collectors.toList());
        }
        else{
            return assignmentRepository.findBySubjectId(subjectId)
                    .stream()
                    .map(assignmentMapper::modelToDto)
                    .collect(Collectors.toList());
        }

    }

}
