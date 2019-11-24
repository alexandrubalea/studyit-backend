package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.DepartmentDto;
import com.ubbdevs.studyit.model.Department;

import java.util.List;

public interface DepartmentService {

    Department getDepartmentByGroup(final int group);
    List<DepartmentDto> getAllDepartmentsWithYears();
    List<String> getGroupsForDepartmentAndYear(long departmentId);
}
