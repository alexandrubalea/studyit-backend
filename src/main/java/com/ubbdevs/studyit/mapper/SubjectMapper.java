package com.ubbdevs.studyit.mapper;


import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.entity.Subject;
import com.ubbdevs.studyit.model.enums.ClassType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectMapper {

    public SubjectDto modelToDto(final Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .description(subject.getDescription())
                .build();
    }

    public SubjectAndClassTypeDto modelToDto(final Subject subject, final List<ClassType> classTypes) {
        return SubjectAndClassTypeDto.builder()
                .subject(modelToDto(subject))
                .classTypes(classTypes)
                .build();
    }
}
