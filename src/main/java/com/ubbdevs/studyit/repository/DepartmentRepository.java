package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> getDepartmentByCodeAndYearAndNumberOfGroupsGreaterThanEqual(int code, int year, int group);
}
