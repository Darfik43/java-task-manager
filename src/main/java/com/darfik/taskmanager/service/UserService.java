package com.darfik.taskmanager.service;

import com.darfik.taskmanager.user.User;

public interface UserService {

    User getById(Long id);
    User getByUsername(String username);
    User create(User user);

}
