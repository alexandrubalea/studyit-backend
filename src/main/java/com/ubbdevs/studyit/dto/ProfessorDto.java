package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.enums.ProfessorDegree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private ProfessorDegree professorDegree;
    private String webpageUrl;
}
