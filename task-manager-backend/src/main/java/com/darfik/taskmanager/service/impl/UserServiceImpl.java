package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.dto.auth.UserSignupPayload;
import com.darfik.taskmanager.dto.auth.UserSignupResponse;
import com.darfik.taskmanager.entity.TaskUser;
import com.darfik.taskmanager.mapper.UserMapper;
import com.darfik.taskmanager.repository.RoleRepository;
import com.darfik.taskmanager.repository.UserRepository;
import com.darfik.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public TaskUser getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                                new ResourceNotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public TaskUser getByUsername(String username) {
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
        TaskUser newTaskUser = userMapper.toEntity(userSignupPayload);
        newTaskUser.setRoles(List.of(roleRepository.findById(1)
                .orElseThrow(() ->
                        new NoSuchElementException("Role not found")))
        );

        userRepository.save(newTaskUser);

        return userMapper.toDto(newTaskUser);
    }
}
