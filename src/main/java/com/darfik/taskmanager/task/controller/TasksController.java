package com.darfik.taskmanager.task.controller;

import com.darfik.taskmanager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;

}
