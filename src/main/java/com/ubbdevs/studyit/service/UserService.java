package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.model.Professor;
import com.ubbdevs.studyit.model.Student;

public interface UserService {

    AuthenticationDto createStudent(String clientId, StudentCreationDto studentCreationDto);
    StudentDto getStudent();
    Student getStudentById(Long studentId);
    Professor getProfessorById(Long professorId);
    ProfessorDto getProfessor();
    StudentDto updateStudentInformation(StudentInformationDto studentInformationUpdateDto);
    ProfessorDto updateProfessorInformation(ProfessorInformationDto professorInformationDto);
    void deleteUserAccount();
}
