package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.DepartmentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "Departments API", tags = {"Departments"})
public interface DepartmentControllerApi {

    @ApiOperation(value = "Get a list of all departments and all years", response = DepartmentDto.class)
    @ApiResponse(code = 200, message = "Successfully return all departments")
    List<DepartmentDto> getDepartmentsAndYears(String clientId);

    @ApiOperation(value = "Get a list of all groups for a department and year", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully return all groups for department"),
            @ApiResponse(code = 404, message = "Department not found")
    })
    List<String> getGroupsForDepartmentAndYear(String clientId, Long departmentId);
}
