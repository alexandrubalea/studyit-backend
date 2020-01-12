package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.dto.*;
import com.ubbdevs.studyit.exception.custom.DuplicateResourceException;
import com.ubbdevs.studyit.exception.custom.ResourceNotFoundException;
import com.ubbdevs.studyit.mapper.AuthenticationMapper;
import com.ubbdevs.studyit.mapper.GroupMapper;
import com.ubbdevs.studyit.mapper.ProfessorMapper;
import com.ubbdevs.studyit.mapper.StudentMapper;
import com.ubbdevs.studyit.model.entity.Professor;
import com.ubbdevs.studyit.model.entity.Student;
import com.ubbdevs.studyit.model.entity.User;
import com.ubbdevs.studyit.model.enums.Role;
import com.ubbdevs.studyit.repository.UserRepository;
import com.ubbdevs.studyit.service.oauth.AuthorizationService;
import com.ubbdevs.studyit.service.oauth.OauthAdaptorService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ClientDetailsAdaptorService clientDetailsAdaptorService;
    private final AuthorizationService authorizationService;
    private final OauthAdaptorService oauthAdaptorService;
    private final EnrollmentService enrollmentService;

    private final UserRepository userRepository;

    private final AuthenticationMapper authenticationMapper;
    private final StudentMapper studentMapper;
    private final ProfessorMapper professorMapper;
    private final GroupMapper groupMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationDto createStudent(final String clientId, final StudentCreationDto studentCreationDto) {
        clientDetailsAdaptorService.validateClientId(clientId);
        final Student student = studentMapper.dtoToModel(studentCreationDto);
        final Student createdStudent = checkIfEmailIsAvailableAndCreateStudent(student);
        enrollmentService.enrollStudentAtMandatorySubjects(createdStudent);
        final OAuth2AccessToken accessToken = oauthAdaptorService.loginUser(
                clientId,
                studentCreationDto.getEmail(),
                studentCreationDto.getPassword()
        );
        return authenticationMapper.modelToDto(createdStudent.getId(), createdStudent.getRole(), accessToken);
    }

    public StudentDto getStudent() {
        final long studentId = authorizationService.getUserId();
        return studentMapper.modelToDto(getStudentById(studentId));
    }

    public Student getStudentById(final Long studentId) {
        return (Student) findUserById(studentId);
    }

    public ProfessorDto getProfessor() {
        final long professorId = authorizationService.getUserId();
        return professorMapper.modelToDto(getProfessorById(professorId));
    }

    public Professor getProfessorById(final Long professorId) {
        return (Professor) findUserById(professorId);
    }

    public StudentDto updateStudentInformation(final StudentInformationDto studentInformationDto) {
        final long studentId = authorizationService.getUserId();
        final Student student = getStudentById(studentId);
        student.setFirstName(studentInformationDto.getFirstName());
        student.setLastName(studentInformationDto.getLastName());
        student.setGroup(groupMapper.dtoToModel(studentInformationDto.getGroup()));
        return studentMapper.modelToDto(userRepository.save(student));
    }

    public ProfessorDto updateProfessorInformation(final ProfessorInformationDto professorInformationDto) {
        final long professorId = authorizationService.getUserId();
        final Professor professor = getProfessorById(professorId);
        professor.setEmail(professorInformationDto.getEmail());
        professor.setWebpageUrl(professorInformationDto.getWebpageUrl());
        return professorMapper.modelToDto(userRepository.save(professor));
    }

    public void deleteUserAccount() {
        final long userId = authorizationService.getUserId();
        userRepository.deleteById(userId);
    }

    @Override
    public List<EnrollmentDto> enrollStudentAtSubject(EnrollStudentDto enrollStudentDto) {
        final Long studentId = authorizationService.getUserId();
        return enrollmentService.enrollStudentAtSubject(getStudentById(studentId), enrollStudentDto);
    }

    @Override
    public List<EnrollmentDto> getAllStudentEnrollments() {
        final Long studentId = authorizationService.getUserId();
        return enrollmentService.getAllStudentEnrollments(getStudentById(studentId));
    }

    @Override
    public void deleteStudentEnrollment(final Long enrollmentId) {
        enrollmentService.deleteStudentEnrollment(enrollmentId);
    }

    private Student checkIfEmailIsAvailableAndCreateStudent(final Student student) {
        checkForUserWithEmail(student.getEmail());
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setRole(Role.STUDENT);
        return userRepository.save(student);
    }

    private User findUserById(final long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("User with id " + id + " was not found");
                });
    }

    private void checkForUserWithEmail(final String email) {
        userRepository.findByEmail(email)
                .ifPresent(s -> {
                    throw new DuplicateResourceException("Email address already registered");
                });
    }
}
