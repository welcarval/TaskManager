package dev.arcturuz.taskmanager.services;

import dev.arcturuz.taskmanager.entities.Task;
import dev.arcturuz.taskmanager.mappers.TaskMapper;
import dev.arcturuz.taskmanager.models.TaskModel;
import dev.arcturuz.taskmanager.repositories.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private TaskMapper mapper;

    @InjectMocks
    private TaskService service;

    @Test
    @DisplayName("Should receive a Task Model and save it in the repository")
    public void saveTask() {
        TaskModel model = new TaskModel("Jantar", "comer proteínas");
        Task entity = new Task(1L, "Jantar", "comer proteínas");
        entity.setName("Jantar");
        entity.setDescription("Comer proteínas");

        when(mapper.toEntity(model)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        service.saveTask(model);

        verify(mapper).toEntity(model);
        verify(repository).save(entity);
    }
}