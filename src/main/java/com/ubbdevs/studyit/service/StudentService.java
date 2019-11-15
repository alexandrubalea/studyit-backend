package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.StudentCreatedDto;
import com.ubbdevs.studyit.dto.StudentCreationDto;

public interface StudentService {

    StudentCreatedDto createStudent(StudentCreationDto studentCreationDto);
}
