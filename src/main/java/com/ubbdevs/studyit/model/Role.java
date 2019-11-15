package com.ubbdevs.studyit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER("User"),
    STUDENT("Student"),
    TEACHER("Teacher");

    private String name;
}
