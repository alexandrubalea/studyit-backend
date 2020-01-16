package com.ubbdevs.studyit.model;

import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.enums.ClassType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfessorAndClassType {

    private Professor professor;
    private ClassType classType;
}
