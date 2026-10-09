package dev.arcturuz.taskmanager.controllers;

import dev.arcturuz.taskmanager.models.TaskModel;
import dev.arcturuz.taskmanager.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping
    public void saveTask(@RequestBody TaskModel model) {
        taskService.saveTask(model);
    }

    @GetMapping
    public List<TaskModel> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskModel getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
