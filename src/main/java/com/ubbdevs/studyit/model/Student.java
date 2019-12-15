package com.ubbdevs.studyit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    private int department;
    private int yearOfStudy;
    private int studentGroup;
    private int studentSemigroup;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}
