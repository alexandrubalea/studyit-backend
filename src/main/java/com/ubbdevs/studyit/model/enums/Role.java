package com.ubbdevs.studyit.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER("User"),
    STUDENT("Student"),
    PROFESSOR("Professor");

    private String name;
}
