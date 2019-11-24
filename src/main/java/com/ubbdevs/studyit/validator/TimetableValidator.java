package com.ubbdevs.studyit.validator;

import com.ubbdevs.studyit.exception.custom.InvalidDataException;
import org.springframework.stereotype.Component;

@Component
public class TimetableValidator {

    public void validateGroupAndSemigroup(final Integer group, final Integer semigroup) {
        if (group < 100 || group > 999)
            throw new InvalidDataException("Group should have only 3 digits");
        if (semigroup != 1 && semigroup != 2) {
            throw new InvalidDataException("Semigroup should be 1 or 2");
        }
    }
}
