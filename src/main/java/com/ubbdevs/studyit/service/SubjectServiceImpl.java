package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.SubjectDto;
import com.ubbdevs.studyit.mapper.SubjectMapper;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.SubjectType;
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

    @Override
    public List<SubjectDto> getAllSubjectsOfTypeForSemester(final SubjectType subjectType, final Integer semester) {
        List<Subject> subjects;
        if (subjectType == null && semester == null)
            subjects = subjectRepository.findAll();
        else if (subjectType != null && semester == null) {
            subjects = subjectRepository.findBySubjectType(subjectType);
        }
        else if (subjectType == null) {
            subjects = subjectRepository.findBySemester(semester);
        }
        else {
            subjects = subjectRepository.findBySubjectTypeAndSemester(subjectType, semester);
        }
        return subjects.stream()
                .map(subjectMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
