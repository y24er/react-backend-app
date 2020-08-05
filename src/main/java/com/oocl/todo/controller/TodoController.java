package com.oocl.todo.controller;

import com.oocl.todo.service.TodoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
}
