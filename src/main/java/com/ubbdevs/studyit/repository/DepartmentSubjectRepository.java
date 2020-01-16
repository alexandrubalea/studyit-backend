package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.entity.DepartmentSubject;
import com.ubbdevs.studyit.model.enums.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentSubjectRepository extends JpaRepository<DepartmentSubject, Long> {

    List<DepartmentSubject> findAllBySubjectTypeAndDepartmentId(SubjectType subjectType, Long departmentId);
}
