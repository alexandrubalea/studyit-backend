package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {

    List<Assignment> findByProfessorIdAndSubjectId(Long professorId, Long subjectId);
}
