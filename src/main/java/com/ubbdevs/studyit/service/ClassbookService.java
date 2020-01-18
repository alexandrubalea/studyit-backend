package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.model.enums.ClassType;

import java.util.List;

public interface ClassbookService {

    List<ClassbookEntryDto> getListOfClassbookEntriesForStudentBySubjectId(Long subjectId);

    StudentsEntriesAndFrequencyDto getListOfStudentsClassbookEntriesFromGroupAtClass(Long subjectId, String group, ClassType classType);

    ClassbookEntryDto createAttendanceForStudent(CreateAttendanceDto createAttendanceDto);

    ClassbookEntryDto updateAttendanceWithIdForStudent(Long id, UpdateAttendanceDto updateAttendanceDto);

    void deleteAttendanceById(Long id);

    ClassbookEntryDto createGradeForStudent(CreateGradeDto createGradeDto);

    ClassbookEntryDto updateGradeWithIdForStudent(Long id, UpdateGradeDto updateGradeDto);

    void deleteGradeById(Long id);
}
