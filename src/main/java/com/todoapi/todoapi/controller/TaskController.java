package com.todoapi.todoapi.controller;


import com.todoapi.todoapi.model.Task;
import com.todoapi.todoapi.model.TaskStatus;
import com.todoapi.todoapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/all")
   public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> task = taskService.getAllTask();
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Optional<Task> task = taskService.getTaskById(id);
        return
                task.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }



    @GetMapping("/status/{status}")
    public ResponseEntity<?> getTaskByStatus(@PathVariable String status){
        try {

            TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
            List<Task> task = taskService.getTaskByStatus(taskStatus);
            if(task.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(task);

        }catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("message", "Statut invalide : " + status);
            errorResponse.put("valeurs_acceptées", Arrays.toString(TaskStatus.values()));

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    @PostMapping
    public  ResponseEntity<Task> createTask(@RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        Boolean delete = taskService.deleteTask(id);
        if(!delete){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id ,@RequestBody Task taskDetails){

        Optional<Task> existingTask = taskService.getTaskById(id);

        if(existingTask.isEmpty()){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status",HttpStatus.NOT_FOUND.value());
            errorResponse.put("message","Tache avec ID"+id+"non trouvée");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        }

        Task taskToUpdate = existingTask.get();
        taskToUpdate.setTitle(taskDetails.getTitle());
        taskToUpdate.setDescription(taskDetails.getDescription());
        taskToUpdate.setStatus(taskDetails.getStatus());

        Task updatedTask = taskService.updateTask(taskToUpdate);

        return ResponseEntity.ok(updatedTask);

    }

}
