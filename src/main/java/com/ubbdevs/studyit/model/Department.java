package com.ubbdevs.studyit.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    private Long id;

    private String name;
    private int numberOfYears;
    private int numberOfGroups;
}
