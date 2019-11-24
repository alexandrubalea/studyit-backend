package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.DepartmentDto;
import com.ubbdevs.studyit.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDto modelToDto(final Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .year(department.getYear())
                .build();
    }
}
