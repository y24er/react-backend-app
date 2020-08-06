package com.oocl.todo.service.impl;

import com.oocl.todo.exception.NotFoundTodoException;
import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import com.oocl.todo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Integer id, Todo todo) {
        Todo oldTodo = todoRepository.findById(id).orElseThrow(NotFoundTodoException::new);
        if (todo.getContent() != null) {
            oldTodo.setContent(todo.getContent());
        }
        if (todo.getStatus() != null) {
            oldTodo.setStatus(todo.getStatus());
        }
        return todoRepository.save(oldTodo);
    }

    @Override
    public void deleteTodo(Integer id) {
        todoRepository.findById(id).orElseThrow(NotFoundTodoException::new);
        todoRepository.deleteById(id);
    }
}
