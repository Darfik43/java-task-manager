package com.darfik.taskmanager.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {

    private String accessToken;
    private String refreshToken;

}
