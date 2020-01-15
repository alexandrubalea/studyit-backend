package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.AssignmentCreationDto;
import com.ubbdevs.studyit.dto.AssignmentDto;
import com.ubbdevs.studyit.model.entity.Assignment;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

    public AssignmentDto modelToDto(final Assignment assignment) {
        return AssignmentDto.builder()
                .id(assignment.getId())
                .professorId((assignment.getProfessorId()))
                .subjectId(assignment.getSubjectId())
                .content(assignment.getContent())
                .deadline(assignment.getDeadline())
                .build();
    }

    public Assignment dtoToModel(final AssignmentCreationDto assignment) {
        return Assignment.builder()
                .professorId((assignment.getProfessorId()))
                .subjectId(assignment.getSubjectId())
                .content(assignment.getContent())
                .deadline(assignment.getDeadline())
                .build();
    }
}
