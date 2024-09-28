package com.darfik.taskmanager.task.controller;

import com.darfik.taskmanager.task.dto.NewTaskPayload;
import com.darfik.taskmanager.task.entity.Task;
import com.darfik.taskmanager.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TasksController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> findTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody NewTaskPayload newTaskPayload,
                                           BindingResult bindingResult,
                                           UriComponentsBuilder uriComponentsBuilder,
                                           Locale locale) {
        if (bindingResult.hasErrors()) {
            ProblemDetail problemDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Bad title or details");
            problemDetail.setProperty("errors",
                    bindingResult.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .toList());

            return ResponseEntity.badRequest()
                    .body(problemDetail);
        } else {
            Task task = this.taskService.createTask(newTaskPayload.title(), newTaskPayload.details());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/v1/tasks/{taskId}")
                            .build(Map.of("taskId", task.getId())))
                    .body(task);

        }
    }

}
