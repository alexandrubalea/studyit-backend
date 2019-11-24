package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.DepartmentDto;
import com.ubbdevs.studyit.dto.GroupEncoder;
import com.ubbdevs.studyit.exception.custom.ResourceNotFoundException;
import com.ubbdevs.studyit.mapper.DepartmentMapper;
import com.ubbdevs.studyit.model.Department;
import com.ubbdevs.studyit.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final GroupEncoder groupEncoder;

    public Department getDepartmentByGroup(final int group) {
        final int departmentCode = groupEncoder.getDepartmentCode(group);
        final int year = groupEncoder.getYear(group);
        final int groupNumber = groupEncoder.getGroup(group);
        return departmentRepository.getDepartmentByCodeAndYearAndNumberOfGroupsGreaterThanEqual(
                departmentCode, year, groupNumber)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Group " + group + " not found");
                });
    }

    public List<DepartmentDto> getAllDepartmentsWithYears() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public List<String> getGroupsForDepartmentAndYear(final long departmentId) {
        final Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Department with id " + departmentId + " not found");
                });
        return IntStream.range(1, department.getNumberOfGroups() + 1)
                .mapToObj(i -> IntStream.of(1, 2)
                        .mapToObj(semiGroup -> String.valueOf(department.getCode()) + department.getYear() + i + "/"
                                + semiGroup)
                .collect(Collectors.toList()))
                .flatMap(Collection::stream)
        .collect(Collectors.toList());
    }
}
