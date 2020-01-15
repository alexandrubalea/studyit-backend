package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.AttendanceCreationDto;
import com.ubbdevs.studyit.dto.AttendanceDto;
import com.ubbdevs.studyit.model.Attendance;

public class AttendanceMapper {

    public AttendanceDto modelToDto(final Attendance attendance){
        return AttendanceDto.builder()
                .id(attendance.getId())
                .professorId(attendance.getProfessor().getId())
                .subjectId(attendance.getSubject().getId())
                .classNumber(attendance.getClassNumber())
                .classType(attendance.getClassType())
                .build();
    }

    public Attendance dtoToModel(final AttendanceCreationDto attendanceCreationDto){
        return Attendance.builder()
                .classNumber(attendanceCreationDto.getClassNumber())
                .classType(attendanceCreationDto.getClassType())
                .build();
    }
}
