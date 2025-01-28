package com.darfik.taskmanager.dto.auth;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginPayload {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
