package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.SubjectDto;

import java.util.List;

public interface EnrollmentService {

    List<SubjectDto> enrollStudentAtSubject(EnrollStudentDto enrollStudentDto);

    List<SubjectDto> getAllStudentEnrollments();

    void deleteStudentEnrollment(Long enrollmentId);
}
