package com.darfik.taskmanager.service;

import com.darfik.taskmanager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAllTasks();
    Task createTask(String title, String details);

    Optional<Task> findTask(Long taskId);

    void updateTask(Long id, String title, String details);

    void deleteTask(Long id);

}
