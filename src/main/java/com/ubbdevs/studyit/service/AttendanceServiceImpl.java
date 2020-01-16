package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.AttendanceDto;
import com.ubbdevs.studyit.mapper.AttendanceMapper;
import com.ubbdevs.studyit.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl  implements AttendanceService{
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final UserService userService;

    public List<AttendanceDto> getListOfAllAttendances(Long professorId, Long subjectId) {
        userService.getProfessorById(professorId);
        return attendanceRepository.findByProfessorIdAndSubjectId(professorId, subjectId)
                .stream()
                .map(attendanceMapper::modelToDto)
                .collect(Collectors.toList());    }
}
