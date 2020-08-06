package com.oocl.todo.service;

import com.oocl.todo.model.Todo;

import java.util.List;


public interface TodoService {
    List<Todo> getTodoList();

    Todo addTodo(Todo todo);

    Todo updateTodo(Integer id, Todo todo);
}
