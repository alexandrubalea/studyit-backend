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
public class ProfessorInformationDto {

    @Pattern(regexp = "[a-z]{4}[0-9]{4}@cs.ubbcluj.ro", message = "Invalid email address. Please use your cs email " +
            "address")
    @NotNull(message = "You must provide an email address")
    private String email;

    //TODO validate this
    @NotNull(message = "You must provide an url")
    private String webpageUrl;
}
