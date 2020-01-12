package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;
import com.ubbdevs.studyit.model.entity.News;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class NewsMapper {

    private final SubjectMapper subjectMapper;

    public NewsDto modelToDto(final News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .creationDate(news.getCreationDate())
                .subjectDto(subjectMapper.modelToDto(news.getSubject()))
                .build();
    }

    public News dtoToModel(final NewsCreationDto newsCreationDto) {
        return News.builder()
                .title(newsCreationDto.getTitle())
                .content(newsCreationDto.getContent())
                .creationDate(LocalDateTime.now())
                .build();
    }
}
