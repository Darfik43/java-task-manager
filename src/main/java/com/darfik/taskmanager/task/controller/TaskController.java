package com.darfik.taskmanager.task.controller;

import com.darfik.taskmanager.task.dto.UpdateTaskPayload;
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

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks/{taskId:\\d+}")
public class TaskController {

    private final TaskService taskService;

    @ModelAttribute
    public Task getTask(@PathVariable("taskId") Long taskId) {
        return this.taskService.findTask(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task is not found"));
    }

    @GetMapping
    public Task findProduct(@ModelAttribute("task") Task task) {
        return task;
    }

    @PatchMapping
    public ResponseEntity<?> updateTask(@PathVariable("taskId") Long taskId,
                                           @Valid @RequestBody UpdateTaskPayload updateTaskPayload,
                                           BindingResult bindingResult) {
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
            this.taskService.updateTask(taskId, updateTaskPayload.title(), updateTaskPayload.details());
            return ResponseEntity.noContent()
                    .build();
        }
    }

}
