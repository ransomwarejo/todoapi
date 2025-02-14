package com.todoapi.todoapi.service;


import com.todoapi.todoapi.model.Task;
import com.todoapi.todoapi.model.TaskStatus;
import com.todoapi.todoapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;


    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    public Boolean deleteTask(Long id) {
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Task> getTaskByStatus(TaskStatus taskStatus) {
        return taskRepository.findByStatus(taskStatus);
    }

    public Task updateTask(Task taskToUpdate) {
        return taskRepository.save(taskToUpdate);
    }
}
