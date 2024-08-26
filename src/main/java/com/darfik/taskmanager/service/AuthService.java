package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.auth.JwtRequest;
import com.darfik.taskmanager.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest jwtRequest);

    JwtResponse refresh(String refreshToken);

}
