package com.ubbdevs.studyit.mapper;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;
import com.ubbdevs.studyit.model.News;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class NewsMapper {

    private final SubjectMapper subjectMapper;

    public NewsDto modelToDto(final News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .dateTime(news.getDateTime())
                .subjectDto(subjectMapper.modelToDto(news.getSubject()))
                .build();
    }

    public News dtoToModel(final NewsCreationDto newsCreationDto) {
        return News.builder()
                .title(newsCreationDto.getTitle())
                .content(newsCreationDto.getContent())
                .dateTime(LocalDateTime.now())
                .build();
        //TODO: subject
    }
}