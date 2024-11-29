package com.darfik.taskmanager.controller;

import com.darfik.taskmanager.dto.task.NewTaskPayload;
import com.darfik.taskmanager.dto.task.TaskResponse;
import com.darfik.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TasksController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponse> findTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody NewTaskPayload newTaskPayload,
                                           BindingResult bindingResult,
                                           UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            TaskResponse task = this.taskService.createTask(newTaskPayload.title(), newTaskPayload.details());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/v1/tasks/{taskId}")
                            .build(Map.of("taskId", task.getId())))
                    .body(task);

        }
    }

}
