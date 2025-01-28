package com.darfik.taskmanager.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignupPayload {

    @NotNull
    @Size(min = 3, max = 15)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String passwordConfirmation;

}
