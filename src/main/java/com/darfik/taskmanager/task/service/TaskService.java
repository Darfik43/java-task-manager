package com.darfik.taskmanager.task.service;

import com.darfik.taskmanager.task.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAllTasks();
    Task createTask(String title, String details);


}
