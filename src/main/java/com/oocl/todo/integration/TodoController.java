package com.oocl.todo.integration;

import com.oocl.todo.dto.TodoRequestDTO;
import com.oocl.todo.dto.TodoResponseDTO;
import com.oocl.todo.mapper.TodoMapper;
import com.oocl.todo.model.Todo;
import com.oocl.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:3000", methods = {GET, POST, PUT, DELETE})
public class TodoController {
    private final TodoMapper todoMapper;
    private final TodoService todoService;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponseDTO addTodo(@RequestBody TodoRequestDTO todoRequestDTO) {
        Todo todo = todoMapper.toTodo(todoRequestDTO);
        Todo savedTodo = todoService.addTodo(todo);
        TodoResponseDTO todoResponseDTO = todoMapper.toTodoResponseDTO(savedTodo);
        return todoResponseDTO;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponseDTO updateTodo(@PathVariable Integer id, @RequestBody TodoRequestDTO todoRequestDTO) {
        Todo todo = todoMapper.toTodo(todoRequestDTO);
        Todo savedTodo = todoService.updateTodo(id, todo);
        TodoResponseDTO todoResponseDTO = todoMapper.toTodoResponseDTO(savedTodo);
        return todoResponseDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
    }
}
