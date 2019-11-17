package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.SubjectType;
import com.ubbdevs.studyit.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private Long id;
    private String name;
    private SubjectType subjectType;
    private Teacher teacher;
    private String description;
    private Integer creditsNumber;
    private Integer semester;
}
