package com.darfik.taskmanager.task.controller;

import com.darfik.taskmanager.task.entity.Task;
import com.darfik.taskmanager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class TasksController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> findTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping
    public Task createTask(NewTaskPayload newTaskPayload) {

    }

}
