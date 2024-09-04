package com.darfik.taskmanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    @NotNull
    @Size(min = 3, max = 15)
    private String email;
    private String password;
    private String passwordConfirmation;

}
