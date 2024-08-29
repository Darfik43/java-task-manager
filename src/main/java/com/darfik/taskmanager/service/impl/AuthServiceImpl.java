package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.dto.auth.JwtRequest;
import com.darfik.taskmanager.dto.auth.JwtResponse;
import com.darfik.taskmanager.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public JwtResponse login(JwtRequest jwtRequest) {
        return new JwtResponse();
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return new JwtResponse();
    }
}