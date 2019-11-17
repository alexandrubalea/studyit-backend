package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.SubjectType;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getAllSubjectsOfTypeForSemester(SubjectType subjectType, Integer semester);
}
