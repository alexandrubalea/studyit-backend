package com.ubbdevs.studyit.exception.custom;

import com.ubbdevs.studyit.exception.model.RestRuntimeException;

public class ResourceNotFoundException extends RestRuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
