package com.ubbdevs.studyit.mapper;


import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.Subject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public SubjectDto modelToDto(final Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .description(subject.getDescription())
                .build();
    }
}
