package com.ubbdevs.studyit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentNameDto {

    private Long id;
    private String firstName;
    private String lastName;
}
