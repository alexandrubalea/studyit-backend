package com.ubbdevs.studyit.model.entity;

import com.ubbdevs.studyit.model.enums.ClassType;
import com.ubbdevs.studyit.model.enums.Day;
import com.ubbdevs.studyit.model.enums.Frequency;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "Timetable")
@NoArgsConstructor
@AllArgsConstructor
public class TimetableEntry {

    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Day day;

    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(value = EnumType.STRING)
    private Frequency frequency;

    @ManyToOne
    private Room room;

    private String formation;

    @Enumerated(value = EnumType.STRING)
    private ClassType classType;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Professor professor;
}
