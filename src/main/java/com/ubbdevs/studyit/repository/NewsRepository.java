package com.ubbdevs.studyit.repository;

import com.ubbdevs.studyit.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllBySubject_IdOrderByCreationDateDesc(Long subjectId);
    List<News> findAllByOrderByCreationDateDesc();
}
