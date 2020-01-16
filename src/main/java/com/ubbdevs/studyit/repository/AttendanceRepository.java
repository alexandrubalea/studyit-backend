package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    List<Attendance> findByProfessorIdAndSubjectId(Long professorId, Long subjectId);

}
