package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getDepartmentsAndYears();
    List<String> getGroupsForDepartmentAndYear(long departmentId, int year);
}
