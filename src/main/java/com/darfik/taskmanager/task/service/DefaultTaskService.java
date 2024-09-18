package com.darfik.taskmanager.task.service;

import com.darfik.taskmanager.task.entity.Task;
import com.darfik.taskmanager.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAllTasks() {
        return Collections.unmodifiableList(taskRepository.findAll());
    }
}
