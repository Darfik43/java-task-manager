package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.auth.JwtResponse;
import com.darfik.taskmanager.dto.auth.UserLoginPayload;
import com.darfik.taskmanager.dto.auth.UserSignupPayload;

public interface AuthService {

    JwtResponse login(UserLoginPayload userLoginPayload);

    JwtResponse refresh(String refreshToken);

}
