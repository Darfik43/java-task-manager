package com.darfik.taskmanager.task.service;

import com.darfik.taskmanager.task.entity.Task;

import java.util.List;

public interface TaskService {

    public List<Task> findAllTasks();

}
