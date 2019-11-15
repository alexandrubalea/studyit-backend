package com.ubbdevs.studyit.exception.dto;

import com.ubbdevs.studyit.exception.model.ExceptionHolder;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidDataExceptionDto {

    private Instant timestamp;
    private List<ExceptionHolder> exceptions;
}
