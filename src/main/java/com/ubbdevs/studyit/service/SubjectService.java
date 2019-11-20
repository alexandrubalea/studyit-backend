package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.enums.SubjectType;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getAllSubjects();
    Subject getSubjectById(Long subjectId);
}
