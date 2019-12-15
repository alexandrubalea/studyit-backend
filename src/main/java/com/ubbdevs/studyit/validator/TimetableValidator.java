package com.ubbdevs.studyit.validator;

import com.ubbdevs.studyit.exception.custom.InvalidDataException;
import org.springframework.stereotype.Component;

@Component
public class TimetableValidator {

    public void validateGroup(final String group) {
        if (!group.matches("[1-9][1-3][1-7]/[1-2]"))
            throw new InvalidDataException("Invalid Group");
        }
}
