package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.DepartmentDto;
import com.ubbdevs.studyit.model.Department;
import com.ubbdevs.studyit.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentDto> getDepartmentsAndYears() {
        return departmentRepository.findAll().stream()
                .map(this::createDepartmentsForAllYears)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<String> getGroupsForDepartmentAndYear(long departmentId, int year) {
        Department department = departmentRepository.getOne(departmentId);

        return IntStream.range(1, department.getNumberOfGroups() + 1)
                .mapToObj(i -> IntStream.of(1, 2)
                        .mapToObj(semiGroup -> String.valueOf(departmentId) + year + i + "/" + semiGroup)
                .collect(Collectors.toList()))
                .flatMap(Collection::stream)
        .collect(Collectors.toList());
    }

    private List<DepartmentDto> createDepartmentsForAllYears(Department department){
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        for(int i = 1; i<= department.getNumberOfYears(); i++ ){
            departmentDtos.add(createDepartmentDto(department,i));
        }
//        return IntStream.range(1, department.getNoOfYears() + 1)
//                .mapToObj(i -> createDepartmentDto(department, i))
//                .collect(Collectors.toList());
        return departmentDtos;
    }

    private DepartmentDto createDepartmentDto(Department department, int year){
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .year(year)
                .build();
    }
}
