package com.darfik.taskmanager.service.impl;

import com.darfik.taskmanager.dto.task.NewTaskPayload;
import com.darfik.taskmanager.dto.task.TaskResponse;
import com.darfik.taskmanager.dto.task.UpdateTaskPayload;
import com.darfik.taskmanager.entity.Task;
import com.darfik.taskmanager.mapper.TaskMapper;
import com.darfik.taskmanager.repository.TaskRepository;
import com.darfik.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public TaskResponse createTask(NewTaskPayload newTaskPayload) {
        return taskMapper.toDto(taskRepository.save(new Task(null, newTaskPayload.title(),
                newTaskPayload.details(), false)));
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
    @Transactional
    public void updateTask(Long id, UpdateTaskPayload updateTaskPayload) {
        this.taskRepository.findById(id)
                .ifPresentOrElse(task -> {
                    task.setTitle(updateTaskPayload.title());
                    task.setDetails(updateTaskPayload.details());
                    task.setFinished(updateTaskPayload.isFinished());
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        this.taskRepository.deleteById(id);
    }

}
