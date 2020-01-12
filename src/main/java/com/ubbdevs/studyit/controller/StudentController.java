package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.EnrollmentDto;
import com.ubbdevs.studyit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController implements StudentControllerApi {

    private final UserService userService;

    @PostMapping("/enroll")
    @ResponseStatus(HttpStatus.CREATED)
    public List<EnrollmentDto> enrollStudentAtSubject(final EnrollStudentDto enrollStudentDto) {
        return userService.enrollStudentAtSubject(enrollStudentDto);
    }

    @GetMapping("/enrollments")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrollmentDto> getStudentEnrollments() {
        return userService.getAllStudentEnrollments();
    }

    @DeleteMapping("/enrollments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentEnrollment(@PathVariable final Long id) {
        userService.deleteStudentEnrollment(id);
    }
}

