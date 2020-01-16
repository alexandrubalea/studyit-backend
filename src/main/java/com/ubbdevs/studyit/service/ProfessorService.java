package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;

import java.util.List;

public interface ProfessorService {

    List<SubjectAndClassTypeDto> getAllSubjectsForProfessorWithId(String clientId, Long id);
}
