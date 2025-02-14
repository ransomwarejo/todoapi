package com.todoapi.todoapi.repository;

import com.todoapi.todoapi.model.Task;
import com.todoapi.todoapi.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>  {
    List<Task> findByStatus(TaskStatus status);
}
