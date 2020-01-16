package com.ubbdevs.studyit.model;

import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.entity.Subject;
import com.ubbdevs.studyit.model.enums.ClassType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Subject subject;
    private ClassType classType;
    private int classNumber;
}
