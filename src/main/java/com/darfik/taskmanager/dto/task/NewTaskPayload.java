package com.darfik.taskmanager.dto.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewTaskPayload(
        @NotNull
        @Size(min = 3, max = 50, message = "Title can not be less than 3 symbols and more than 50 symbols")
        String title,

        @Size(max = 1000, message = "Title can not be more than 1000 symbols")
        String details

) {
}
