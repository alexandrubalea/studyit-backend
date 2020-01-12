package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.Professor;
import com.ubbdevs.studyit.model.ProfessorWithClassType;
import com.ubbdevs.studyit.model.Subject;
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
public class SubjectInformationDto {

    private Subject subject;
    private List<ProfessorWithClassTypeDto> professors;
}
