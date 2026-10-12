package dev.arcturuz.taskmanager.controllers;

import dev.arcturuz.taskmanager.models.TaskModel;
import dev.arcturuz.taskmanager.services.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    @DisplayName("Should receive a Get request and return a Task")
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

}