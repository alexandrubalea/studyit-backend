package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;
import com.ubbdevs.studyit.mapper.NewsMapper;
import com.ubbdevs.studyit.model.News;
import com.ubbdevs.studyit.model.Subject;
import com.ubbdevs.studyit.model.User;
import com.ubbdevs.studyit.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final UserService userService;
    private final NewsMapper newsMapper;
    private final SubjectService subjectService;

    @Override
    public List<NewsDto> getNewsBySubject(Long subjectId) {
        subjectService.getSubjectById(subjectId);
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto createNews(NewsCreationDto newsCreationDto) {
        News news = newsMapper.dtoToModel(newsCreationDto);
        Subject subject = subjectService.getSubjectById(newsCreationDto.getSubjectId());
        news.setSubject(subject);
        news.setProfessorId(newsCreationDto.getProfessorId());
        News createdNews = newsRepository.save(news);
        return newsMapper.modelToDto(createdNews);
    }
}
