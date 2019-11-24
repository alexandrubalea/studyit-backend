package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.TimetableEntryDto;
import com.ubbdevs.studyit.mapper.TimetableEntryMapper;
import com.ubbdevs.studyit.model.Department;
import com.ubbdevs.studyit.model.TimetableEntry;
import com.ubbdevs.studyit.repository.TimetableRepository;
import com.ubbdevs.studyit.validator.TimetableValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final DepartmentService departmentService;
    private final TimetableValidator timetableValidator;
    private final TimetableEntryMapper timetableEntryMapper;

    @Override
    public List<TimetableEntryDto> getTimetableForGroupAndSemigroup(final Integer group, final Integer semigroup) {
        timetableValidator.validateGroupAndSemigroup(group, semigroup);
        final Department department = departmentService.getDepartmentByGroup(group);
        return timetableRepository.getByFormationIn(Arrays.asList(department.getAbbreviation(),
                Integer.toString(group), group + "/" + semigroup))
                .stream()
                .map(timetableEntryMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
