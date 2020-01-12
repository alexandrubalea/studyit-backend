package com.ubbdevs.studyit.model;

import com.ubbdevs.studyit.model.entity.Subject;
import com.ubbdevs.studyit.model.enums.ClassType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectAndClassType {

    private Subject subject;
    private ClassType classType;
}
