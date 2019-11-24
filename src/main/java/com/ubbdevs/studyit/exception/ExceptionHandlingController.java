package com.ubbdevs.studyit.exception;

import com.ubbdevs.studyit.exception.custom.DuplicateResourceException;
import com.ubbdevs.studyit.exception.custom.InvalidDataException;
import com.ubbdevs.studyit.exception.custom.ResourceNotFoundException;
import com.ubbdevs.studyit.exception.dto.ExceptionDto;
import com.ubbdevs.studyit.exception.dto.InvalidDataExceptionDto;
import com.ubbdevs.studyit.exception.model.ExceptionHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(final ResourceNotFoundException exception) {
        log.error("ResourceNotFoundException: ", exception);
        return createException(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handle(final DuplicateResourceException exception) {
        log.error("RestRuntimeException: ", exception);
        return createException(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handle(final InvalidDataException exception) {
        log.error("InvalidDataException: ", exception);
        return createException(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidDataExceptionDto handle(final MethodArgumentNotValidException exception) {
        return createExceptionForInvalidFields(exception.getBindingResult().getFieldErrors());
    }


    private ExceptionDto createException(final String message) {
        return ExceptionDto.builder()
                .timestamp(Instant.now())
                .exceptions(createExceptionHolderFromMessage(message))
                .build();
    }

    private InvalidDataExceptionDto createExceptionForInvalidFields(final List<FieldError> fieldErrors) {
        return InvalidDataExceptionDto.builder()
                .timestamp(Instant.now())
                .exceptions(createExceptionsListForInvalidFields(fieldErrors))
                .build();
    }

    private List<ExceptionHolder> createExceptionsListForInvalidFields(final List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(fieldError -> createExceptionHolderFromMessage(fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    private ExceptionHolder createExceptionHolderFromMessage(final String message) {
        return ExceptionHolder.builder()
                .message(message)
                .build();
    }
}
