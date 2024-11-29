package com.darfik.taskmanager.controller;

import com.darfik.taskmanager.dto.task.TaskResponse;
import com.darfik.taskmanager.dto.task.UpdateTaskPayload;
import com.darfik.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
    public TaskResponse getTask(@PathVariable("taskId") Long taskId) {
        return this.taskService.findTask(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task is not found"));
    }

//    @GetMapping
//    public TaskResponse findTask(@ModelAttribute("task") Task task) {
//        return task;
//    } Ochen' bol'shoy vopros k etoy ruchke

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

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail
                        .forStatusAndDetail(
                                HttpStatus.NOT_FOUND,
                                "Task not found"));
    }

}
