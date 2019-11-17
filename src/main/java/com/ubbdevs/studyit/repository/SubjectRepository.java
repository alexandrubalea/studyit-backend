package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findBySubjectType(SubjectType subjectType);
    List<Subject> findBySemester(int semester);
    List<Subject> findBySubjectTypeAndSemester(SubjectType subjectType, int semester);
}
