package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.model.Student;

public interface UserService {

    Student createStudent(Student student);
    void checkForUserWithEmail(String email);
}
