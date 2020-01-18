package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.ProfessorAndClassType;
import com.ubbdevs.studyit.model.SubjectAndClassType;
import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.entity.Subject;
import com.ubbdevs.studyit.model.entity.TimetableEntry;
import com.ubbdevs.studyit.model.enums.ClassType;
import com.ubbdevs.studyit.model.enums.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<TimetableEntry, Long> {

    Optional<TimetableEntry> findFirstByProfessor_IdAndSubject_Id(Long professorId, Long subjectId);

    Optional<TimetableEntry> findFirstByClassTypeAndSubjectId(ClassType classType, Long subjectId);

    List<TimetableEntry> getByFormationIn(Collection formations);

    List<TimetableEntry> getByProfessorId(Long professorId);

    List<TimetableEntry> getByProfessorIdAndDay(Long professorId, Day day);

    List<TimetableEntry> getByFormationInAndSubjectIdIn(Collection formation, Collection subjectIds);

    List<TimetableEntry> getByFormationInAndSubjectIdInAndDay(Collection formation, Collection subjectIds, Day day);

    @Query(value = "select distinct new com.ubbdevs.studyit.model.ProfessorAndClassType(t.professor, t.classType) " +
            "from TimetableEntry t where t.subject = ?1")
    List<ProfessorAndClassType> getDistinctClassTypeAndProfessorId(Subject subject);

    @Query(value = "select distinct new com.ubbdevs.studyit.model.SubjectAndClassType(t.subject, t.classType) " +
            "from TimetableEntry  t where t.professor = ?1")
    List<SubjectAndClassType> getDistinctSubjectsForProfessorId(Professor professor);
}
