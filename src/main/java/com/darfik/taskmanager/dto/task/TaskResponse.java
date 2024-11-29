package com.darfik.taskmanager.dto.task;

import lombok.Data;

@Data
public class TaskResponse {

    private Long id;
    private String title;
    private String details;

}
