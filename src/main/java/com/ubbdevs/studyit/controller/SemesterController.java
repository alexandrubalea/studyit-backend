package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.service.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/semester")
public class SemesterController implements SemesterControllerApi {

    private final SemesterService semesterService;

    @GetMapping("/completion-percentage")
    @ResponseStatus(HttpStatus.OK)
    public long getProgressBar(final String clientId) {
        return semesterService.getCompletionPercentage(clientId);
    }
}
