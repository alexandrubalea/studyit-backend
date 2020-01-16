package com.ubbdevs.studyit.model.entity;

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

    private int code;
    private String name;
    private String abbreviation;
    private int year;
    private int numberOfGroups;
}
