package com.darfik.taskmanager.mapper;

import com.darfik.taskmanager.dto.task.NewTaskPayload;
import com.darfik.taskmanager.dto.task.TaskResponse;
import com.darfik.taskmanager.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponse toDto(Task task);

    Task toEntity(NewTaskPayload newTaskPayload);

}
