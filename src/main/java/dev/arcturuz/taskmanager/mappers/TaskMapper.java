package dev.arcturuz.taskmanager.mappers;

import dev.arcturuz.taskmanager.entities.Task;
import dev.arcturuz.taskmanager.models.TaskModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskModel toModel(Task entity);

    Task toEntity(TaskModel model);
}
