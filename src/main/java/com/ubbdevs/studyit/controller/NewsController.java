package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;
import com.ubbdevs.studyit.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController implements NewsControllerApi {

    private final NewsService newsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto postNews(@RequestBody NewsCreationDto newsCreationDto) {
        return newsService.postNews(newsCreationDto);
    }

    @GetMapping
    public List<NewsDto> getNewsBySubject(@RequestParam(required = false) Long subjectId) {
        return newsService.getNewsBySubjectOrAllNewsIfNoSubject(subjectId);
    }
}
