package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.dto.task.TaskResponse;
import com.darfik.taskmanager.entity.Task;
import com.darfik.taskmanager.mapper.TaskMapper;
import com.darfik.taskmanager.repository.TaskRepository;
import com.darfik.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponse> findAllTasks() {
        List<TaskResponse> taskList = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            taskList.add(taskMapper.toDto(task));
        }
        return Collections.unmodifiableList(taskList);
    }

    @Override
    public TaskResponse createTask(String title, String details) {
        return taskMapper.toDto(taskRepository.save(new Task(null, title, details)));
    }

    // Nizhe bol'shoy vopros a nahuya vozvrashat' optionl
    // esli my vikidyem exception i nikogda nam ne vernetsya null po idee
    @Override
    public Optional<TaskResponse> findTask(Long taskId) {
        return Optional.of(taskMapper
                .toDto(taskRepository.findById(taskId).orElseThrow(() ->
                new NoSuchElementException("Task not found"))));
    }

    @Override
    public void updateTask(Long id, String title, String details) {
        this.taskRepository.findById(id)
                .ifPresentOrElse(task -> {
                    task.setTitle(title);
                    task.setDetails(details);
                    // Tut vopros a gde update v db, kak budto
                    // prosto polya obnovlyaem u exemplyara bez obnoveniy v tablice :)))
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

}
