package com.oocl.todo.mapper;

import com.oocl.todo.dto.TodoRequestDTO;
import com.oocl.todo.model.Todo;

public class TodoMapper {
    public Todo toTodo(TodoRequestDTO todoRequestDTO) {
        Todo todo = new Todo();
        todo.setContent(todoRequestDTO.getContent());
        todo.setStatus(todoRequestDTO.getStatus());
        return todo;
    }
}
