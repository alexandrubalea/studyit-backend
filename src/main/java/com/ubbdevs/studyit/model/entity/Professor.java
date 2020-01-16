package com.ubbdevs.studyit.model.entity;

import com.ubbdevs.studyit.model.entity.User;
import com.ubbdevs.studyit.model.enums.ProfessorDegree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User {

    @Enumerated(EnumType.STRING)
    private ProfessorDegree professorDegree;

    private String webpageUrl;
}
