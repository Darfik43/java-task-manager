package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.service.UserService;
import com.darfik.taskmanager.user.User;
import com.darfik.taskmanager.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                                new ResourceNotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }
}
