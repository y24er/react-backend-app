package com.oocl.todo.mapper;

import com.oocl.todo.dto.TodoRequestDTO;
import com.oocl.todo.dto.TodoResponseDTO;
import com.oocl.todo.model.Todo;
import org.springframework.stereotype.Component;

//@Mapper(componentModel = "spring")
@Component
public class TodoMapper {
    public Todo todoRequestDTO2Todo(TodoRequestDTO todoRequestDTO) {
        Todo todo = new Todo();
        todo.setContent(todoRequestDTO.getContent());
        todo.setStatus(todoRequestDTO.getStatus());
        return todo;
    }

    public TodoResponseDTO todo2TodoResponseDTO(Todo todo) {
        return new TodoResponseDTO(todo.getId(), todo.getContent(), todo.getStatus());
    }
}
