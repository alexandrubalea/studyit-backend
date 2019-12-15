package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.EnrollmentDto;

import java.util.List;

public interface EnrollmentService {

    List<EnrollmentDto> enrollStudentAtSubject(EnrollStudentDto enrollStudentDto);

    List<EnrollmentDto> getAllStudentEnrollments();

    void deleteStudentEnrollment(Long enrollmentId);
}
