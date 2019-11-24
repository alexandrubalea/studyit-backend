package com.ubbdevs.studyit.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfessorDegree {

    PROFESSOR("Professor", "Prof."),
    ASSOCIATE_PROFESSOR("Associate Professor", "Conf."),
    LECTURER("Lecturer", "Lect."),
    PHD_CANDIDATE("Phd. Candidate", "Drd."),
    ASSISTANT_PROFESSOR("Assistant Professor", "Asist."),
    ASSOCIATE("Associate", "C.d.asociat");

    private String name;
    private String abbreviation;
}
