package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.enums.AttendanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAttendanceDto {

    private AttendanceType attendanceType;
}
