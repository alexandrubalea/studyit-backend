package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.model.Group;
import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.entity.Student;

import java.util.List;

public interface UserService {

    List<Student> getAllStudentsFromGroup(Group group, boolean entireGroup);
    AuthenticationDto createStudent(String clientId, StudentCreationDto studentCreationDto);
    StudentDto getStudent();
    Student getStudentById(Long studentId);
    Professor getProfessorById(Long professorId);
    ProfessorDto getProfessor();
    StudentDto updateStudentInformation(UpdateStudentDto studentInformationUpdateDto);
    ProfessorDto updateProfessorInformation(ProfessorInformationDto professorInformationDto);
    void deleteUserAccount();
    List<EnrollmentDto> enrollStudentAtSubject(EnrollStudentDto enrollStudentDto);
    List<EnrollmentDto> getAllStudentEnrollments();
    void deleteStudentEnrollment(Long enrollmentId);
}
