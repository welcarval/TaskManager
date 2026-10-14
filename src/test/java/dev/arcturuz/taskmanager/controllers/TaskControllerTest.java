package dev.arcturuz.taskmanager.controllers;

import dev.arcturuz.taskmanager.models.TaskModel;
import dev.arcturuz.taskmanager.services.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;

    @Test
    @DisplayName("Should receive a GET request and return a Task")
    public void getTask() throws Exception {
        TaskModel mockTask = new TaskModel("Jantar", "comer proteinas");
        when(taskService.getTask(1L)).thenReturn(mockTask);

        MockHttpServletRequestBuilder request = get("/api/tasks/1");

        mockMvc.perform(request)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.name").value("Jantar"),
                        jsonPath("$.description").value("comer proteinas")
                );
    }

    @Test
    @DisplayName("Should receive a POST request with a Task and save it")
    public void saveTask() throws Exception {
        TaskModel mockTask = new TaskModel("Jantar", "comer proteinas");
        String json = objectMapper.writeValueAsString(mockTask);

        when(taskService.saveTask(mockTask)).thenReturn(1L);

        MockHttpServletRequestBuilder request = post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, endsWith("/api/tasks/1")));
    }

    @Test
    @DisplayName("Should receive a GET request and return all tasks")
    public void getAllTasks() throws Exception {
        TaskModel task1 = new TaskModel("Jantar", "comer proteinas");
        TaskModel task2 = new TaskModel("Almoçar", "comer legumes");
        List<TaskModel> mockTasks = List.of(task1, task2);

        when(taskService.getAllTasks()).thenReturn(mockTasks);

        MockHttpServletRequestBuilder request = get("/api/tasks");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].name").value("Jantar"))
                .andExpect(jsonPath("$.[0].description").value("comer proteinas"))
                .andExpect(jsonPath("$.[1].name").value("Almoçar"))
                .andExpect(jsonPath("$.[1].description").value("comer legumes"));
    }

    @Test
    @DisplayName("Should receive a DELETE request and return 204 No content")
    public void deleteTask() throws Exception {

        MockHttpServletRequestBuilder request = delete("/api/tasks/1");
        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }

}