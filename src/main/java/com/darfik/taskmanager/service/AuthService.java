package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.auth.JwtResponse;
import com.darfik.taskmanager.dto.auth.UserSignupRequest;

public interface AuthService {

    JwtResponse login(UserSignupRequest userSignupRequest);

    JwtResponse refresh(String refreshToken);

}
