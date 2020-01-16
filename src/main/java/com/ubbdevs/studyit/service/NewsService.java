package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;

import java.util.List;

public interface NewsService {

    List<NewsDto> getNewsBySubjectOrAllNewsIfNoSubject(Long subjectId);
    NewsDto postNews(NewsCreationDto newsCreationDto);
}
