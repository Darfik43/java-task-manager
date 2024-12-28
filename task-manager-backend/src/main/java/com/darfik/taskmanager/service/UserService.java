package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.auth.UserSignupPayload;
import com.darfik.taskmanager.dto.auth.UserSignupResponse;
import com.darfik.taskmanager.entity.TaskUser;

public interface UserService {

    TaskUser getById(Long id);
    TaskUser getByUsername(String username);
    UserSignupResponse create(UserSignupPayload userSignupPayload);

}
