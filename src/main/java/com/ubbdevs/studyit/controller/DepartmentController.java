package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.DepartmentDto;
import com.ubbdevs.studyit.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departments")
public class DepartmentController implements DepartmentControllerApi{

    private final DepartmentService departmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartmentDto> getDepartmentsAndYears() {
        return departmentService.getAllDepartmentsWithYears();
    }

    @GetMapping("/{departmentId}/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getGroupsForDepartmentAndYear(@PathVariable long departmentId) {
        return departmentService.getGroupsForDepartmentAndYear(departmentId);
    }
}
