package com.darfik.taskmanager.task.service;

import com.darfik.taskmanager.task.entity.Task;
import com.darfik.taskmanager.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAllTasks() {
        return Collections.unmodifiableList(taskRepository.findAll());
    }

    @Override
    public Task createTask(String title, String details) {
        return taskRepository.save(new Task(null, title, details));
    }

    @Override
    public Optional<Task> findTask(Long taskId) {
        return taskRepository.findById(taskId);
    }
}
