package com.darfik.taskmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String passwordConfirmation;

}
