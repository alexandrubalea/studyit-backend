package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.DepartmentDto;
import com.ubbdevs.studyit.dto.GroupEncoder;
import com.ubbdevs.studyit.exception.custom.ResourceNotFoundException;
import com.ubbdevs.studyit.mapper.DepartmentMapper;
import com.ubbdevs.studyit.model.entity.Department;
import com.ubbdevs.studyit.model.Group;
import com.ubbdevs.studyit.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final ClientDetailsAdaptorService clientDetailsAdaptorService;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final GroupEncoder groupEncoder;

    public Department getDepartmentByGroup(final Group group) {
        final int departmentCode = group.getDepartmentCode();
        final int year = group.getYear();
        final int groupNumber = group.getGroupNumber();
        return departmentRepository.getDepartmentByCodeAndYearAndNumberOfGroupsGreaterThanEqual(
                departmentCode, year, groupNumber)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Group " + group + " not found");
                });
    }

    public List<DepartmentDto> getAllDepartmentsWithYears(final String clientId) {
        clientDetailsAdaptorService.validateClientId(clientId);
        return departmentRepository.findAll().stream()
                .map(departmentMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public List<String> getGroupsForDepartmentAndYear(final String clientId, final Long departmentId) {
        clientDetailsAdaptorService.validateClientId(clientId);
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

    public List<String> getFormationFromGroup(Group group) {
        final Department department = getDepartmentByGroup(group);
        return Arrays.asList(department.getAbbreviation(), groupEncoder.fromGroup(group),
                groupEncoder.fromGroupWithoutSemigroup(group));
    }
}
