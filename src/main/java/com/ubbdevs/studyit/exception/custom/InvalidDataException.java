package com.ubbdevs.studyit.exception.custom;

import com.ubbdevs.studyit.exception.model.RestRuntimeException;

public class InvalidDataException extends RestRuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }
}
