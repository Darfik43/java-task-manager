package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.dto.UserDto;
import com.darfik.taskmanager.mapper.UserMapper;
import com.darfik.taskmanager.service.UserService;
import com.darfik.taskmanager.user.Role;
import com.darfik.taskmanager.user.User;
import com.darfik.taskmanager.user.UserRepository;
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
    public UserDto create(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalStateException("This email is already taken");
        }
        if (!userDto.getPassword().equals(userDto.getPasswordConfirmation())) {
            throw new IllegalStateException("Password and password confirmation do not match");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User newUser = userMapper.toEntity(userDto);
        Set<Role> roles = Set.of(Role.USER);
        newUser.setRoles(roles);

        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }
}
