package com.ubbdevs.studyit.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    private final ClientDetailsAdaptorService clientDetailsAdaptorService;

    private final static LocalDate START_DATE = LocalDate.of(2019, 10, 1);
    private final static LocalDate END_DATE = LocalDate.of(2020, 1, 20);
    private final static long VACATION_DAYS = 14;

    public long getCompletionPercentage(final String clientId) {
        clientDetailsAdaptorService.validateClientId(clientId);
        long daysBetweenStartAndEnd = ChronoUnit.DAYS.between(START_DATE, END_DATE);
        long completedDays = ChronoUnit.DAYS.between(START_DATE, LocalDate.now());
        return completedDays * 100 / (daysBetweenStartAndEnd - VACATION_DAYS);
    }
}
