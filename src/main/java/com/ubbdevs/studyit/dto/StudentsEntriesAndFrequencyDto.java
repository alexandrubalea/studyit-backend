package com.ubbdevs.studyit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentsEntriesAndFrequencyDto {

    private int classFrequency;
    private List<StudentEntriesDto> students;
}
