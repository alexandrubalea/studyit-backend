package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.enums.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
