package com.todoapi.todoapi.repository;

import com.todoapi.todoapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>  {
}
