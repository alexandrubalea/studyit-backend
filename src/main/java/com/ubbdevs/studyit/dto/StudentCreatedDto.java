package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreatedDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String group;
    private Role role;
}
