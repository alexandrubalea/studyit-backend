package com.ubbdevs.studyit.exception.dto;

import com.ubbdevs.studyit.exception.model.ExceptionHolder;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {

    private Instant timestamp;
    private ExceptionHolder exceptions;
}
