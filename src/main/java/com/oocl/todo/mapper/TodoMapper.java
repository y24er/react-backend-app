package com.oocl.todo.mapper;

import com.oocl.todo.dto.TodoRequestDTO;
import com.oocl.todo.dto.TodoResponseDTO;
import com.oocl.todo.model.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo toTodo(TodoRequestDTO todoRequestDTO) {
        Todo todo = new Todo();
        todo.setContent(todoRequestDTO.getContent());
        todo.setStatus(todoRequestDTO.getStatus());
        return todo;
    }

    public TodoResponseDTO toTodoResponseDTO(Todo todo) {
        return new TodoResponseDTO(todo.getId(), todo.getContent(), todo.getStatus());
    }
}
