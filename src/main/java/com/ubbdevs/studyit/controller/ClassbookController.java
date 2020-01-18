package com.ubbdevs.studyit.controller;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.model.enums.ClassType;
import com.ubbdevs.studyit.service.ClassbookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/classbook")
public class ClassbookController implements ClassbookControllerApi {

    private final ClassbookService classbookService;

    @GetMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassbookEntryDto> getListOfClassbookEntriesForStudentBySubjectId(@RequestParam final Long subjectId) {
        return classbookService.getListOfClassbookEntriesForStudentBySubjectId(subjectId);
    }

    @GetMapping("/professor")
    public StudentsEntriesAndFrequencyDto getListOfStudentsClassbookEntriesFromGroupAtClass(@RequestParam final Long subjectId,
                                                                                     @RequestParam final String group,
                                                                                     @RequestParam final ClassType classType) {
        return classbookService.getListOfStudentsClassbookEntriesFromGroupAtClass(subjectId, group, classType);
    }

    @PostMapping("/attendances")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassbookEntryDto createAttendanceForStudent(@Valid @RequestBody final CreateAttendanceDto createAttendanceDto) {
        return classbookService.createAttendanceForStudent(createAttendanceDto);
    }

    @PutMapping("/attendances/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassbookEntryDto updateAttendanceWithIdForStudent(@PathVariable final Long id,
                                                              @Valid @RequestBody UpdateAttendanceDto updateAttendanceDto) {
        return classbookService.updateAttendanceWithIdForStudent(id, updateAttendanceDto);
    }

    @DeleteMapping("/attendances/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteAttendanceById(@PathVariable final Long id) {
        classbookService.deleteAttendanceById(id);
    }

    @PostMapping("/grades")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassbookEntryDto createGradeForStudent(@Valid @RequestBody final CreateGradeDto createGradeDto) {
        return classbookService.createGradeForStudent(createGradeDto);
    }

    @PutMapping("/grades/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassbookEntryDto updateGradeWithIdForStudent(@PathVariable final Long id,
                                                         @Valid @RequestBody final UpdateGradeDto updateGradeDto) {
        return classbookService.updateGradeWithIdForStudent(id, updateGradeDto);
    }

    @DeleteMapping("/grades/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteGradeById(@PathVariable final Long id) {
        classbookService.deleteGradeById(id);
    }
}
