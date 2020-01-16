package com.ubbdevs.studyit.exception.custom;

import com.ubbdevs.studyit.exception.model.RestRuntimeException;

public class UnauthorizedException extends RestRuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
