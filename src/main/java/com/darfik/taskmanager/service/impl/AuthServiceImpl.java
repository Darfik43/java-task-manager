package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.dto.auth.JwtResponse;
import com.darfik.taskmanager.dto.auth.UserLoginPayload;
import com.darfik.taskmanager.dto.auth.UserSignupPayload;
import com.darfik.taskmanager.entity.TaskUser;
import com.darfik.taskmanager.security.JwtTokenProvider;
import com.darfik.taskmanager.service.AuthService;
import com.darfik.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public JwtResponse login(UserLoginPayload loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                ));
        TaskUser taskUser = userService.getByUsername(loginRequest.getEmail());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(taskUser.getId(), taskUser.getEmail(), taskUser.getRoles()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(taskUser.getId(), taskUser.getEmail()));
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}