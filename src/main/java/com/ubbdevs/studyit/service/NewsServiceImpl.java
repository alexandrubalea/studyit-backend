package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;
import com.ubbdevs.studyit.mapper.NewsMapper;
import com.ubbdevs.studyit.model.News;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.TimetableEntry;
import com.ubbdevs.studyit.repository.NewsRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    private final AuthorizationService authorizationService;
    private final SubjectService subjectService;
    private final TimetableService timetableService;

    @Override
    public List<NewsDto> getNewsBySubjectOrAllNewsIfNoSubject(final Long subjectId) {
        List<News> news;
        if (subjectId != null)
            news = getNewsBySubjectOrderedDescending(subjectId);
        else
            news = getAllNewsOrderedDescending();
        return news
                .stream()
                .map(newsMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NewsDto postNews(final NewsCreationDto newsCreationDto) {
        final Long professorId = authorizationService.getUserId();
        final Subject subject = subjectService.getSubjectById(newsCreationDto.getSubjectId());
        timetableService.checkIfProfessorTeacherSubject(professorId, newsCreationDto.getSubjectId());
        News news = newsMapper.dtoToModel(newsCreationDto);
        news.setSubject(subject);
        return newsMapper.modelToDto(newsRepository.save(news));
    }

    private List<News> getAllNewsOrderedDescending() {
        return newsRepository.findAllByOrderByCreationDateDesc();
    }

    private List<News> getNewsBySubjectOrderedDescending(final Long subjectId) {
        subjectService.getSubjectById(subjectId);
        return newsRepository.findAllBySubject_IdOrderByCreationDateDesc(subjectId);
    }
}
