package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.dto.auth.UserSignupPayload;
import com.darfik.taskmanager.dto.auth.UserSignupResponse;
import com.darfik.taskmanager.entity.Role;
import com.darfik.taskmanager.entity.User;
import com.darfik.taskmanager.mapper.UserMapper;
import com.darfik.taskmanager.repository.UserRepository;
import com.darfik.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                                new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public UserSignupResponse create(UserSignupPayload userSignupPayload) {
        if (userRepository.existsByEmail(userSignupPayload.getEmail())) {
            throw new IllegalStateException("This email is already taken");
        }
        if (!userSignupPayload.getPassword().equals(userSignupPayload.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and password confirmation do not match");
        }

        userSignupPayload.setPassword(passwordEncoder.encode(userSignupPayload.getPassword()));
        User newUser = userMapper.toEntity(userSignupPayload);
        Set<Role> roles = Set.of(Role.ROLE_USER);
        newUser.setRoles(roles);

        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }
}
