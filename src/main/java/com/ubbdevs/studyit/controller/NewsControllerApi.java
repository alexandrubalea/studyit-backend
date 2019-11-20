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

    @ApiOperation(value = "Create news operation", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created news")
    })
    NewsDto createNews (NewsCreationDto newsCreationDto);

    @ApiOperation(value = "Get news by subject operation", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned news"),
            @ApiResponse(code = 404, message = "Subject not found")
    })
    List<NewsDto> getNewsBySubject (Long subjectId);
}
