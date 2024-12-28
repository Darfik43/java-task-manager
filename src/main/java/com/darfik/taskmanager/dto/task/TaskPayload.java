package com.darfik.taskmanager.dto.task;

import jakarta.validation.constraints.NotNull;

public record TaskPayload(

        @NotNull
        Long taskId
) {}


