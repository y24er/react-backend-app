package com.oocl.todo.service;

import com.oocl.todo.exception.NotFoundTodoException;
import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import com.oocl.todo.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TodoServiceImplTest {
    @Mock
    TodoRepository todoRepository;
    @InjectMocks
    TodoServiceImpl todoService;

    @Test
    void should_return_todo_list_when_get_all_todo_given_nothing() {
        //given
        when(todoRepository.findAll()).thenReturn(asList(new Todo(1, "finished work", false)));
        //when
        List<Todo> todoList = todoService.getTodoList();
        //then
        assertEquals(1, todoList.size());
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() {
        //given
        Todo todo = new Todo("finished work");
        when(todoRepository.save(todo)).thenReturn(todo);
        //when
        Todo savedTodo = todoService.addTodo(todo);
        //then
        assertEquals("finished work", savedTodo.getContent());
        assertEquals(false, savedTodo.getStatus());
    }

    @Test
    void should_return_updated_todo_when_update_todo_given_updated_info() {
        //given
        Todo todo = new Todo(2, "eating", false);
        when(todoRepository.findById(2)).thenReturn(Optional.of(todo));
        Todo updatedInfo = new Todo();
        updatedInfo.setContent("eating....");
        updatedInfo.setStatus(true);
        when(todoRepository.save(todo)).thenReturn(new Todo(2, "eating....", true));
        //when
        Todo updatedTodo = todoService.updateTodo(2, updatedInfo);
        //then
        assertEquals(2, updatedTodo.getId());
        assertEquals("eating....", updatedTodo.getContent());
        assertEquals(true, updatedTodo.getStatus());
    }

    @Test
    void should_return_updated_todo_when_update_todo_given_partial_updated_info_content() {
        Todo todo = new Todo(2, "eating", false);
        when(todoRepository.findById(2)).thenReturn(Optional.of(todo));
        Todo updatedInfo = new Todo();
        updatedInfo.setContent("eating....");
        when(todoRepository.save(todo)).thenReturn(new Todo(2, "eating....", false));
        //when
        Todo updatedTodo = todoService.updateTodo(2, updatedInfo);
        //then
        assertEquals(2, updatedTodo.getId());
        assertEquals("eating....", updatedTodo.getContent());
        assertEquals(false, updatedTodo.getStatus());
    }

    @Test
    void should_return_updated_todo_when_update_todo_given_partial_updated_info_status() {
        Todo todo = new Todo(2, "eating", false);
        when(todoRepository.findById(2)).thenReturn(Optional.of(todo));
        Todo updatedInfo = new Todo();
        updatedInfo.setStatus(true);
        when(todoRepository.save(todo)).thenReturn(new Todo(2, "eating", true));
        //when
        Todo updatedTodo = todoService.updateTodo(2, updatedInfo);
        //then
        assertEquals(2, updatedTodo.getId());
        assertEquals("eating", updatedTodo.getContent());
        assertEquals(true, updatedTodo.getStatus());
    }

    @Test
    void should_throw_not_found_todo_when_update_todo_given_not_exist_id() {
        //given
        when(todoRepository.findById(100)).thenReturn(Optional.empty());
        //when
        Throwable exception = assertThrows(NotFoundTodoException.class, () -> todoService.updateTodo(100, new Todo()));
        //then
        assertEquals("Not found this todo!", exception.getMessage());
    }

    @Test
    void should_return_nothing_when_delete_todo_given_id() {
        //given
        Integer id = 2;
        when(todoRepository.findById(id)).thenReturn(Optional.of(new Todo()));
        //when
        todoService.deleteTodo(id);
        //then
        verify(todoRepository, times(1)).deleteById(id);
    }

    @Test
    void should_throw_not_found_todo_exception_when_delete_todo_given_not_exist_id() {
        //given
        Integer id = 100;
        //when
        when(todoRepository.findById(id)).thenReturn(Optional.empty());
        Throwable exception = assertThrows(NotFoundTodoException.class, () -> todoService.deleteTodo(id));
        //then
        assertEquals("Not found this todo!", exception.getMessage());
        verify(todoRepository, times(0)).deleteById(id);
    }
}
