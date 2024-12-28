package com.darfik.taskmanager.client;

import com.darfik.taskmanager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {

    List<Task> findAllTasks();

    Task createTask(String title, String details);

    Optional<Task> findTask(Long taskId);

    void updateTask(Long taskId, String title, String details, boolean isFinished);

    void deleteProduct(Long taskId);

}
