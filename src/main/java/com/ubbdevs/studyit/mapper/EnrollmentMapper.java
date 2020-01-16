package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.EnrollmentDto;
import com.ubbdevs.studyit.model.entity.Enrollment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnrollmentMapper {

    private final SubjectMapper subjectMapper;

    public EnrollmentDto modelToDto(final Enrollment enrollment) {
        return EnrollmentDto.builder()
                .id(enrollment.getId())
                .subjectDto(subjectMapper.modelToDto(enrollment.getSubject()))
                .build();
    }
}
