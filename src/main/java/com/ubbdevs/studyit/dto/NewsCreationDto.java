package com.ubbdevs.studyit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsCreationDto {

    @NotNull(message = "You need to add a title")
    private String title;

    @NotNull(message = "You need to add a content")
    private String content;

    @NotNull(message = "You must enter a subjectId")
    private Long subjectId;
}
