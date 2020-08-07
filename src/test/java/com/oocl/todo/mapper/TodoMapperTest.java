package com.oocl.todo.mapper;

import com.oocl.todo.dto.TodoRequestDTO;
import com.oocl.todo.dto.TodoResponseDTO;
import com.oocl.todo.model.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoMapperTest {
    @Test
    void should_return_todo_when_to_todo_given_todo_request_dto() {
        //given
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("sleep", false);
        TodoMapper todoMapper = new TodoMapper();
        //when
        Todo todo = todoMapper.toTodo(todoRequestDTO);
        //then
        assertEquals("sleep", todo.getContent());
    }

    @Test
    void should_return_todo_response_dto_when_to_todo_response_dto_given_dto() {
        //given
        Todo todo = new Todo(1, "sleep", true);
        TodoMapper todoMapper = new TodoMapper();
        //when
        TodoResponseDTO todoResponseDTO = todoMapper.toTodoResponseDTO(todo);
        //then
        assertEquals("sleep", todoResponseDTO.getContent());
        assertEquals(true, todoResponseDTO.getStatus());
    }
}
