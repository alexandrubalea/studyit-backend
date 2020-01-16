package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.enums.ClassType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectAndClassTypeDto {

    private SubjectDto subject;
    private List<ClassType> classTypes;
}
