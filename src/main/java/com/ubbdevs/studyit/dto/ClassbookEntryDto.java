package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.enums.AttendanceType;
import com.ubbdevs.studyit.model.enums.ClassType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassbookEntryDto {

    private Long id;
    private Long professorId;
    private Long studentId;
    private Long subjectId;
    private Integer classNumber;
    private ClassType classType;
    private AttendanceType attendanceType;
    private Integer grade;
}
