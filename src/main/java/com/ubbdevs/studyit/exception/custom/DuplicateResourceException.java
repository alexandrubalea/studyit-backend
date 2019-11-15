package com.ubbdevs.studyit.exception.custom;

import com.ubbdevs.studyit.exception.model.RestRuntimeException;

public class DuplicateResourceException extends RestRuntimeException {

    public DuplicateResourceException(final String message) {
        super(message);
    }
}
