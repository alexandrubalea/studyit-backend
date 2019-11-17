package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.GroupEncoder;
import com.ubbdevs.studyit.dto.StudentCreatedDto;
import com.ubbdevs.studyit.dto.StudentCreationDto;
import com.ubbdevs.studyit.model.Role;
import com.ubbdevs.studyit.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final GroupEncoder groupEncoder;

    public Student dtoToModel(final StudentCreationDto studentCreationDto) {
        return Student.builder()
                .email(studentCreationDto.getEmail())
                .firstName(studentCreationDto.getFirstName())
                .lastName(studentCreationDto.getLastName())
                .password(studentCreationDto.getPassword())
                .role(Role.STUDENT)
                .departmentId(groupEncoder.getDepartment(studentCreationDto.getGroup()))
                .yearOfStudy(groupEncoder.getYear(studentCreationDto.getGroup()))
                .studentGroup(groupEncoder.getGroup(studentCreationDto.getGroup()))
                .studentSemigroup(groupEncoder.getSemiGroup(studentCreationDto.getGroup()))
                .build();
    }

    public StudentCreatedDto modelToDto(final Student student) {
        return StudentCreatedDto.builder()
                .id(student.getId())
                .email(student.getEmail())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .role(student.getRole())
                .group(groupEncoder.encodeGroup(student.getDepartmentId(), student.getYearOfStudy(),
                        student.getStudentGroup(), student.getStudentSemigroup()))
                .build();
    }
}