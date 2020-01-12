package com.ubbdevs.studyit.dto;

import com.ubbdevs.studyit.model.entity.Room;
import com.ubbdevs.studyit.model.enums.ClassType;
import com.ubbdevs.studyit.model.enums.Day;
import com.ubbdevs.studyit.model.enums.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimetableEntryDto {

    private Long id;
    private Day day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Frequency frequency;
    private Room room;
    private String formation;
    private ClassType classType;
    private SubjectDto subjectDto;
    private ProfessorDto professorDto;
}
