package com.ubbdevs.studyit.model.entity;

import com.ubbdevs.studyit.model.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    @Embedded
    private Group group;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}
