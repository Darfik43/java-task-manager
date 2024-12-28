package com.darfik.taskmanager.client;

import com.darfik.taskmanager.dto.task.NewTaskPayload;
import com.darfik.taskmanager.dto.task.UpdateTaskPayload;
import com.darfik.taskmanager.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductsRestClientImpl implements ProductsRestClient {

    private static final ParameterizedTypeReference<List<Task>> TASKS_TYPE_REFERENCE
            = new ParameterizedTypeReference<>() {
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
        try {
            return this.restClient
                    .post()
                    .uri("/api/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NewTaskPayload(title, details))
                    .retrieve()
                    .body(Task.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public Optional<Task> findTask(Long taskId) {
        try {
            return Optional.ofNullable(this.restClient.get()
                    .uri("/api/v1/tasks/{taskId}", taskId)
                    .retrieve()
                    .body(Task.class));
        } catch (HttpClientErrorException.NotFound exception) {
            return Optional.empty();
        }

    }

    @Override
    public void updateTask(Long taskId, String title, String details, boolean isFinished) {
        try {
            this.restClient
                    .patch()
                    .uri("/api/v1/tasks/{taskId", taskId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateTaskPayload(title, details, isFinished))
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public void deleteProduct(Long taskId) {
        try{
            this.restClient
                    .delete()
                    .uri("/api/v1/tasks")
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }
}
