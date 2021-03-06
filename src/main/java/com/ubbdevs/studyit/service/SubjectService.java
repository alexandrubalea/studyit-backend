package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.model.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getAllSubjectsStartingWith(String startsWith);
    Subject getSubjectById(Long subjectId);
}
