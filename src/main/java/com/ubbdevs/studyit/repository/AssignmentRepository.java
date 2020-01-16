package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByProfessorIdAndSubjectId(Long professorId, Long subjectId);

    List<Assignment> findByProfessorId(Long professorId);

    List<Assignment> findBySubjectId(Long subjectId);
}
