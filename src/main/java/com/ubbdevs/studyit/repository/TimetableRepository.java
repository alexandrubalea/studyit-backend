package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.ProfessorWithClassType;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.TimetableEntry;
import com.ubbdevs.studyit.model.enums.ClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<TimetableEntry, Long> {

    Optional<TimetableEntry> findFirstByProfessor_IdAndSubject_Id(Long professorId, Long subjectId);
    List<TimetableEntry> getByFormationIn(Collection formations);
    List<TimetableEntry> getByFormationInAndSubject_IdIn(Collection formation, Collection ids);

    @Query(value = "select distinct new com.ubbdevs.studyit.model.ProfessorWithClassType(t.professor, t.classType) from TimetableEntry t where t.subject = ?1")
    List<ProfessorWithClassType> getDistinctClassTypeAndProfessorId(Subject subject);
}
