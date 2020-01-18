package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.StudentCreationDto;
import com.ubbdevs.studyit.dto.StudentDto;
import com.ubbdevs.studyit.dto.StudentNameDto;
import com.ubbdevs.studyit.model.entity.Student;
import com.ubbdevs.studyit.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final GroupMapper groupMapper;

    public Student dtoToModel(final StudentCreationDto studentCreationDto) {
        return Student.builder()
                .email(studentCreationDto.getEmail())
                .firstName(studentCreationDto.getFirstName())
                .lastName(studentCreationDto.getLastName())
                .password(studentCreationDto.getPassword())
                .role(Role.STUDENT)
                .group(groupMapper.dtoToModel(studentCreationDto.getGroup()))
                .build();
    }

    public StudentDto modelToDto(final Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .email(student.getEmail())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .role(student.getRole())
                .group(groupMapper.modelToDto(student.getGroup()))
                .build();
    }
}
