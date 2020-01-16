package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.ProfessorDto;
import com.ubbdevs.studyit.dto.ProfessorAndClassTypeDto;
import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.enums.ClassType;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ProfessorAndClassTypeDto modelToDto(final Professor professor, final List<ClassType> classTypes) {
        return ProfessorAndClassTypeDto.builder()
                .professor(modelToDto(professor))
                .classTypes(classTypes)
                .build();
    }
}
