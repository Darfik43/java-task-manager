package com.darfik.taskmanager.service;

import com.darfik.taskmanager.dto.task.NewTaskPayload;
import com.darfik.taskmanager.dto.task.TaskResponse;
import com.darfik.taskmanager.dto.task.UpdateTaskPayload;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskResponse> findAllTasks();
    TaskResponse createTask(NewTaskPayload newTaskPayload);

    Optional<TaskResponse> findTask(Long taskId);

    void updateTask(Long id, UpdateTaskPayload updateTaskPayload);

    void deleteTask(Long id);

}
