package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.ProfessorDto;
import com.ubbdevs.studyit.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public ProfessorDto modelToDto(final Professor professor) {
        return ProfessorDto.builder()
                .id(professor.getId())
                .email(professor.getEmail())
                .firstName(professor.getFirstName())
                .lastName(professor.getLastName())
                .professorDegree(professor.getProfessorDegree())
                .webpageUrl(professor.getWebpageUrl())
                .build();
    }
}
