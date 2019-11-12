package com.ubbdevs.studyit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timetable")
public class TimeTableController implements TimeTableControllerApi{

    @Override
    @GetMapping
    public String justATest() {
        return "Works fine";
    }
}
