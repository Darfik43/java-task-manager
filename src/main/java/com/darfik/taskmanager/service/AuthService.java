package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.auth.JwtResponse;
import com.darfik.taskmanager.dto.auth.UserSignupPayload;

public interface AuthService {

    JwtResponse login(UserSignupPayload userSignupPayload);

    JwtResponse refresh(String refreshToken);

}
