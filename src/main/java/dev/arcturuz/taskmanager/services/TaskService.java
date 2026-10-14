package dev.arcturuz.taskmanager.services;

import dev.arcturuz.taskmanager.entities.Task;
import dev.arcturuz.taskmanager.mappers.TaskMapper;
import dev.arcturuz.taskmanager.models.TaskModel;
import dev.arcturuz.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository repository;
    private TaskMapper mapper;

    public Long saveTask(TaskModel model) {
        Task entity = mapper.toEntity(model);
        entity = repository.save(entity);
        return entity.getId();
    }

    public TaskModel getTask(Long id) {
        return mapper.toModel(repository.findById(id).orElseThrow());
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public List<TaskModel> getAllTasks() {
        return repository.findAll().stream().map(entity -> mapper.toModel(entity)).collect(Collectors.toList());
    }
}
