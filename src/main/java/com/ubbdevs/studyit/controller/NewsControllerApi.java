package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.NewsCreationDto;
import com.ubbdevs.studyit.dto.NewsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(description = "News API", tags = {"News"})
public interface NewsControllerApi {

    @ApiOperation(value = "Post news for a subject. A teacher can post news only for a subjects that he teaches ",
            response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created news")
    })
    NewsDto postNews(NewsCreationDto newsCreationDto);

    @ApiOperation(value = "Get all news for a subject ordered by creation data", response = NewsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned all news"),
            @ApiResponse(code = 404, message = "Subject not found")
    })
    List<NewsDto> getNewsBySubject(Long subjectId);
}
