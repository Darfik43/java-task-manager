package com.darfik.taskmanager.controller;

import com.darfik.taskmanager.entity.TaskUser;
import com.darfik.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    // Now returns entity itself just to test, must be changed

    private final UserService userService;


    @GetMapping
    public TaskUser getUser(Authentication authentication) {
        return userService.getByUsername(authentication.getName());
    }

}
