package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.auth.UserSignupRequest;
import com.darfik.taskmanager.dto.auth.UserSignupResponse;
import com.darfik.taskmanager.entity.User;

public interface UserService {

    User getById(Long id);
    User getByUsername(String username);
    UserSignupResponse create(UserSignupRequest userSignupRequest);

}
