package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getNewsBySubject(Long subjectId);
    NewsDto createNews(NewsCreationDto newsCreationDto);
}
