package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByStudent_IdAndSubject_Id(Long studentId, Long subjectId);
    List<Enrollment> findByStudent_Id(Long studentId);
    void deleteByIdAndStudent_Id(Long id, Long studentId);

}
