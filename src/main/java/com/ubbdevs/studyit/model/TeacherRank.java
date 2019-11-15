package com.ubbdevs.studyit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeacherRank {

    PROFESSOR("Professor", "Prof.");

    private String name;
    private String abbreviation;
}
