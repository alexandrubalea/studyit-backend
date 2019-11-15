package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.StudentCreatedDto;
import com.ubbdevs.studyit.dto.StudentCreationDto;
import com.ubbdevs.studyit.mapper.StudentMapper;
import com.ubbdevs.studyit.model.Role;
import com.ubbdevs.studyit.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final UserService userService;

    private final StudentMapper studentMapper;

    public StudentCreatedDto createStudent(final StudentCreationDto studentCreationDto) {
        final Student student = studentMapper.dtoToModel(studentCreationDto);
        return studentMapper.modelToDto(userService.createStudent(student));
    }
}
