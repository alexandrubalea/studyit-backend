package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectAndClassTypeDto;
import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.mapper.SubjectMapper;
import com.ubbdevs.studyit.model.Professor;
import com.ubbdevs.studyit.model.ProfessorAndClassType;
import com.ubbdevs.studyit.model.Subject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final UserService userService;
    private final TimetableService timetableService;
    private final ClientDetailsAdaptorService clientDetailsAdaptorService;

    private final SubjectMapper subjectMapper;

    @Override
    public List<SubjectAndClassTypeDto> getAllSubjectsForProfessorWithId(final String clientId, final Long id) {
        clientDetailsAdaptorService.validateClientId(clientId);
        final Professor professor = userService.getProfessorById(id);
        return timetableService.getAllSubjectsForProfessor(professor);
    }
}
