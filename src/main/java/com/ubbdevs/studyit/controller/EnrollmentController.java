package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.EnrollStudentDto;
import com.ubbdevs.studyit.dto.EnrollmentDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.Enrollment;
import com.ubbdevs.studyit.service.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController implements EnrollmentControllerApi {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<EnrollmentDto> enrollStudentAtSubject(final EnrollStudentDto enrollStudentDto) {
        return enrollmentService.enrollStudentAtSubject(enrollStudentDto);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public List<EnrollmentDto> getStudentEnrollments() {
        return enrollmentService.getAllStudentEnrollments();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentEnrollment(@PathVariable final Long id) {
        enrollmentService.deleteStudentEnrollment(id);
    }
}

