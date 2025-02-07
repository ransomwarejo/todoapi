package com.todoapi.todoapi.controller;


import com.todoapi.todoapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/todo")
public class TaskController {
    private final TaskService todoService;

}
