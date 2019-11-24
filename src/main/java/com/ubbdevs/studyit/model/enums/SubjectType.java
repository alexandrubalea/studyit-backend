package com.ubbdevs.studyit.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectType {

    OPTIONAL("Optional"),
    MANDATORY("Mandatory");

    private String name;
}
