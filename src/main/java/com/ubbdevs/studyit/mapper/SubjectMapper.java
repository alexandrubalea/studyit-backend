package com.ubbdevs.studyit.mapper;


import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.Subject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubjectMapper {

    public SubjectDto modelToDto(final Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .subjectType(subject.getSubjectType())
                .description(subject.getDescription())
                .creditsNumber(subject.getCreditsNumber())
                .teacher(subject.getTeacher())
                .semester(subject.getSemester())
                .build();
    }
}
