package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.ClassbookEntryDto;
import com.ubbdevs.studyit.model.entity.ClassbookEntry;
import org.springframework.stereotype.Component;

@Component
public class ClassbookEntryMapper {

    public ClassbookEntryDto modelToDto(final ClassbookEntry classbookEntry) {
        return ClassbookEntryDto.builder()
                .id(classbookEntry.getId())
                .professorId(classbookEntry.getProfessorId())
                .studentId(classbookEntry.getStudentId())
                .subjectId(classbookEntry.getSubjectId())
                .classType(classbookEntry.getClassType())
                .classNumber(classbookEntry.getNumber())
                .grade(classbookEntry.getGrade())
                .attendanceType(classbookEntry.getAttendanceType())
                .build();
    }
}
