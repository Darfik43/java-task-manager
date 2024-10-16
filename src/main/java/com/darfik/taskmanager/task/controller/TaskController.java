package com.darfik.taskmanager.task.controller;

import com.darfik.taskmanager.task.dto.UpdateTaskPayload;
import com.darfik.taskmanager.task.entity.Task;
import com.darfik.taskmanager.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
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
                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.taskService
                    .updateTask(taskId, updateTaskPayload.title(), updateTaskPayload.details());
            return ResponseEntity.noContent()
                    .build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        this.taskService.deleteTask(taskId);
        return ResponseEntity.noContent()
                .build();
    }

}
