package dev.arcturuz.taskmanager.mappers;

import dev.arcturuz.taskmanager.entities.Task;
import dev.arcturuz.taskmanager.models.TaskModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper mapper;

    @Test
    @DisplayName("Should receive a Task Entity and create a Task Model")
    public void mapperEntityToModel() {
        Task entity = new Task(1L, "Jantar", "comer proteínas");
        TaskModel model = mapper.toModel(entity);

        Assertions.assertNotNull(model);
        Assertions.assertEquals(entity.getName(), model.name());
        Assertions.assertEquals(entity.getDescription(), model.description());
    }

    @Test
    @DisplayName("Should receive a Task Model and create a Task Entity")
    public void mapperModelToEntity() {
        TaskModel model = new TaskModel("Almoçar", "comer legumes");
        Task entity = mapper.toEntity(model);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(model.name(), entity.getName());
        Assertions.assertEquals(model.description(), entity.getDescription());
    }
}
