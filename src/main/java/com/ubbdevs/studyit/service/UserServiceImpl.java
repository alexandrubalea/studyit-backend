package com.ubbdevs.studyit.service;

import com.ubbdevs.studyit.exception.custom.DuplicateResourceException;
import com.ubbdevs.studyit.model.Role;
import com.ubbdevs.studyit.model.Student;
import com.ubbdevs.studyit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Student createStudent(final Student student) {
        checkForUserWithEmail(student.getEmail());
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setRole(Role.STUDENT);
        return userRepository.save(student);
    }

    public void checkForUserWithEmail(final String email) {
        userRepository.findByEmail(email)
                .ifPresent(s -> {
                    throw new DuplicateResourceException("Email address already registered");
                });
    }
}
