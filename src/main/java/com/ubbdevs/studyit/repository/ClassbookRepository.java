package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.entity.ClassbookEntry;
import com.ubbdevs.studyit.model.enums.ClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassbookRepository extends JpaRepository<ClassbookEntry, Long> {

    Optional<ClassbookEntry> findBySubjectIdAndStudentIdAndClassTypeAndNumber(Long subjectId, Long studentId,
                                                                              ClassType classType, Integer number);

    List<ClassbookEntry> findAllBySubjectIdAndStudentId(Long subjectId, Long studentId);

    List<ClassbookEntry> findAllBySubjectIdAndStudentIdAndClassType(Long subjectId, Long studentId, ClassType classType);
}
