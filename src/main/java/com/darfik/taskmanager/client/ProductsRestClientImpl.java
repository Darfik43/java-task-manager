package com.darfik.taskmanager.client;

import com.darfik.taskmanager.dto.NewTaskPayload;
import com.darfik.taskmanager.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductsRestClientImpl implements ProductsRestClient {

    private static final ParameterizedTypeReference<List<Task>> TASKS_TYPE_REFERENCE
            = new ParameterizedTypeReference<List<Task>>() {
    };

    private final RestClient restClient;

    @Override
    public List<Task> findAllTasks() {
        return this.restClient
                .get()
                .uri("/api/v1/tasks")
                .retrieve()
                .body(TASKS_TYPE_REFERENCE);
    }

    @Override
    public Task createTask(String title, String details) {
        return this.restClient
                .post()
                .uri("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new NewTaskPayload(title, details))
                .retrieve()
                .body(Task.class);
    }

    @Override
    public Optional<Task> findTask(Long taskId) {
        return Optional.empty();
    }

    @Override
    public void updateTask(Long taskId, String title, String details) {

    }

    @Override
    public void deleteProduct(Long taskId) {

    }
}
