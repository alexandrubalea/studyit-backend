package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.mapper.ProfessorMapper;
import com.ubbdevs.studyit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController implements UserControllerApi {

    private final UserService userService;

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(final String clientId,
                                    @Valid @RequestBody final StudentCreationDto studentCreationDto) {
        return userService.createStudent(clientId, studentCreationDto);
    }

    @GetMapping("/student/me")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto getStudent() {
        return userService.getStudent();
    }

    @PutMapping("/student/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudentInfo(@Valid @RequestBody final StudentInformationDto studentInformationDto) {
        userService.updateStudentInformation(studentInformationDto);
    }

    @GetMapping("/professor/me")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDto getProfessor() {
        return userService.getProfessor();
    }

    @PutMapping("/professor/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfessorInfo(@Valid @RequestBody final ProfessorInformationDto professorInformationDto) {
        userService.updateProfessorInformation(professorInformationDto);
    }

    @DeleteMapping("/student/me")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserAccount() {
        userService.deleteUserAccount();
    }
}
