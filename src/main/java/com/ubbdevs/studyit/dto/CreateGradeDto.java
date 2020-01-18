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
public class CreateGradeDto {

    private Long subjectId;
    private ClassType classType;
    private Integer classNumber;
    private Long studentId;
    private Integer grade;
}
