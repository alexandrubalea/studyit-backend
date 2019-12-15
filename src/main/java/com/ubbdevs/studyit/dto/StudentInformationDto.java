package com.ubbdevs.studyit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInformationDto {

    // TODO: pattern to capitalize the first letter of first name
    @Size(min=4, max=32, message = "Invalid firstName size")
    @NotNull(message = "You must provide a first name")
    private String firstName;

    // TODO: pattern to capitalize the first letter of last name
    @Size(min=4, max=32, message = "Invalid lastName size")
    @NotNull(message = "You must provide a last name")
    private String lastName;

    // pay attention to regex group digit [1-7]
    @Pattern(regexp = "[1-9][1-3][1-7]/[1-2]", message = "Invalid group. The group should match the following pattern: " +
            "[Department][Group][Year]/[Semigroup]\n eg. 931/1")
    @NotNull(message = "You must provide a group")
    private String group;
}
