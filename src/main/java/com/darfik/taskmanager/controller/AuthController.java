package com.darfik.taskmanager.controller;

import com.darfik.taskmanager.dto.auth.JwtResponse;
import com.darfik.taskmanager.dto.auth.UserSignupRequest;
import com.darfik.taskmanager.dto.auth.UserSignupResponse;
import com.darfik.taskmanager.service.AuthService;
import com.darfik.taskmanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody UserSignupRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<UserSignupResponse> register(@Valid @RequestBody UserSignupRequest userSignupRequest,
                                            BindingResult bindingResult,
                                            UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            UserSignupResponse responseUserDto = userService.create(userSignupRequest);
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/v1/users/{userId}")
                            .build(Map.of("userId", responseUserDto.getId()))
                    )
                    .body(responseUserDto);
        }
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
