package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.StudentCreatedDto;
import com.ubbdevs.studyit.dto.StudentCreationDto;
import com.ubbdevs.studyit.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController implements StudentControllerApi {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentCreatedDto createStudent(@Valid @RequestBody final StudentCreationDto studentCreationDto) {
        return studentService.createStudent(studentCreationDto);
    }
}
