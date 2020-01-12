package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.model.DepartmentSubject;

import java.util.List;

public interface DepartmentSubjectService {

    List<DepartmentSubject> getMandatorySubjectsForDepartmentId(Long departmentId);
}
