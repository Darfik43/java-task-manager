package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.UserDto;
import com.darfik.taskmanager.user.User;

public interface UserService {

    User getById(Long id);
    User getByUsername(String username);
    UserDto create(UserDto userDto);

}
