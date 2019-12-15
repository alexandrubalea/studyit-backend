package com.ubbdevs.studyit.model;

import lombok.*;

import javax.persistence.Embeddable;


@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private int departmentCode;
    private int year;
    private int groupNumber;
    private int semiGroup;

}
