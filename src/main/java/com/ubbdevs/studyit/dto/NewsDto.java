package com.ubbdevs.studyit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private SubjectDto subjectDto;
}
