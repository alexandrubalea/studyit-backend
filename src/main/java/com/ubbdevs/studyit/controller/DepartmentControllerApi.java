package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.DepartmentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(description = "Department API", tags = {"Department"})
public interface DepartmentControllerApi {

    @ApiOperation(value = "Get a list of all departments and all years")
    List<DepartmentDto> getDepartmentsAndYears();

    @ApiOperation(value = "Get a list of all groups for a department name and year")
    List<String> getGroupsForDepartmentAndYear(long departmentId, int year);
}
