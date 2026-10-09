package dev.arcturuz.taskmanager.repositories;

import dev.arcturuz.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
