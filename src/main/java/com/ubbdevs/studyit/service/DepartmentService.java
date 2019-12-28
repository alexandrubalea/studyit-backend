package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.DepartmentDto;
import com.ubbdevs.studyit.model.Department;
import com.ubbdevs.studyit.model.Group;

import java.util.List;

public interface DepartmentService {


    Department getDepartmentByGroup(Group group);

    List<DepartmentDto> getAllDepartmentsWithYears(String clientId);

    List<String> getGroupsForDepartmentAndYear(String clientId, Long departmentId);

    List<String> getFormationFromGroup(Group group);
}
