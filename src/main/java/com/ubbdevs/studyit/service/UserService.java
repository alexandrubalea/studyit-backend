package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.model.Professor;
import com.ubbdevs.studyit.model.Student;

public interface UserService {

    StudentDto createStudent(String clientId, StudentCreationDto studentCreationDto);
    StudentDto getStudent();
    Student getStudentById(Long studentId);
    Professor getProfessorById(Long professorId);
    ProfessorDto getProfessor();
    void updateStudentInformation(StudentInformationDto studentInformationUpdateDto);
    void updateProfessorInformation(ProfessorInformationDto professorInformationDto);
    void deleteUserAccount();
}
