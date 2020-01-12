package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInformationDto {

    private Subject subject;
    private List<ProfessorAndClassTypeDto> professors;
}
