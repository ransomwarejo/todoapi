package com.todoapi.todoapi.service;


import com.todoapi.todoapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository todoRepository;
}
