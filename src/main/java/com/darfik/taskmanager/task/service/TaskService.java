package com.darfik.taskmanager.task.service;

import com.darfik.taskmanager.task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAllTasks();
    Task createTask(String title, String details);

    Optional<Task> findTask(Long taskId);

    void updateTask(Long id, String title, String details);

}
