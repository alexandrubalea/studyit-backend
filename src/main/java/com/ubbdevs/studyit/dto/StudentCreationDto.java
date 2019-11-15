package com.ubbdevs.studyit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreationDto {

    @Pattern(regexp = "[a-z]{4}[0-9]{4}@scs.ubbcluj.ro", message = "Invalid email address. Please use your scs email " +
            "address")
    @NotNull(message = "You must provide an email address")
    private String email;

    // TODO: pattern to capitalize the first letter of first name
    @NotNull(message = "You must provide a first name")
    private String firstName;

    // TODO: pattern to capitalize the first letter of last name
    @NotNull(message = "You must provide a last name")
    private String lastName;

    // TODO: pattern to limit password length and to force user to also use capital letters and numbers
    @NotNull(message = "You must provide a password")
    private String password;

    // pay attention to regex group digit [1-7]
    @Pattern(regexp = "[1-9][1-3][1-7]/[1-2]", message = "Invalid group. The group should match the following pattern: " +
            "[Department][Group][Year]/[Semigroup]\n eg. 931/1")
    @NotNull(message = "You must provide a group")
    private String group;
}

