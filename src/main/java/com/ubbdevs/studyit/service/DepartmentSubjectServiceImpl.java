package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.model.DepartmentSubject;
import com.ubbdevs.studyit.model.enums.SubjectType;
import com.ubbdevs.studyit.repository.DepartmentSubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentSubjectServiceImpl implements DepartmentSubjectService {

    private final DepartmentSubjectRepository departmentSubjectRepository;

    @Override
    public List<DepartmentSubject> getMandatorySubjectsForDepartmentId(Long departmentId) {
        return departmentSubjectRepository.findAllBySubjectTypeAndDepartmentId(SubjectType.MANDATORY, departmentId);
    }
}
