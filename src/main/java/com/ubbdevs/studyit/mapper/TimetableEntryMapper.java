package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.model.entity.TimetableEntry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TimetableEntryMapper {

    private final SubjectMapper subjectMapper;
    private final ProfessorMapper professorMapper;

    public TimetableEntryDto modelToDto(final TimetableEntry timetableEntry) {
        return TimetableEntryDto.builder()
                .id(timetableEntry.getId())
                .day(timetableEntry.getDay())
                .startTime(timetableEntry.getStartTime())
                .endTime(timetableEntry.getEndTime())
                .frequency(timetableEntry.getFrequency())
                .room(timetableEntry.getRoom())
                .formation(timetableEntry.getFormation())
                .classType(timetableEntry.getClassType())
                .subjectDto(subjectMapper.modelToDto(timetableEntry.getSubject()))
                .professorDto(professorMapper.modelToDto(timetableEntry.getProfessor()))
                .build();
    }
}
