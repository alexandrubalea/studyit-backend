package com.ubbdevs.studyit.model.entity;

import com.ubbdevs.studyit.model.enums.AttendanceType;
import com.ubbdevs.studyit.model.enums.ClassType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Classbook")
public class ClassbookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long professorId;
    private Long subjectId;
    private Long studentId;
    private Integer number;

    @Enumerated(value = EnumType.STRING)
    private ClassType classType;

    @Enumerated(value = EnumType.STRING)
    private AttendanceType attendanceType;
    private Integer grade;
}


