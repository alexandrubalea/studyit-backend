package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.AttendanceDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl  implements AttendanceService{
    @Override
    public List<AttendanceDto> getListOfAllAttendances(Long professorId, Long subjectId) {
        return null;
    }
}
