package com.darfik.taskmanager.dto.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTaskPayload(
        @NotNull(message = "Title can not be null")
        @Size(min = 3, max = 50, message = "Details can not be more than 50 symbols and less than 3 symbols")
        String title,
        @Size(max = 1000, message = "Details can not be more than 1000 symbols")
        String details,

        boolean isFinished
) {
}
