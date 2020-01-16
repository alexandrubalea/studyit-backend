package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.EnrollmentDto;
import com.ubbdevs.studyit.model.entity.Student;

import java.util.List;

public interface EnrollmentService {

    List<EnrollmentDto> enrollStudentAtSubject(Student student, EnrollStudentDto enrollStudentDto);

    List<EnrollmentDto> getAllStudentEnrollments(Student student);

    void enrollStudentAtMandatorySubjects(Student student);

    void deleteStudentEnrollment(Long enrollmentId);
}
