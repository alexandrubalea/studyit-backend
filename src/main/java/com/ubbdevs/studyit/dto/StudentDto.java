package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String group;
    private Role role;
}
