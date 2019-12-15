package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.exception.custom.ResourceNotFoundException;
import com.ubbdevs.studyit.mapper.SubjectMapper;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public List<SubjectDto> getAllSubjectsStartingWith(final String startsWith) {
        List<Subject> subjects;
        if (startsWith == null)
            subjects = getAllSubjects();
        else
            subjects = getAllSubjectsWithNameStartingWith(startsWith);
        return subjects.stream()
                .map(subjectMapper::modelToDto)
                .collect(Collectors.toList());
    }

    public Subject getSubjectById(final Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Subject with id " + subjectId + " not found");
                });
    }

    private List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    private List<Subject> getAllSubjectsWithNameStartingWith(String startsWith) {
        return subjectRepository.getAllByNameStartingWith(startsWith);
    }
}
