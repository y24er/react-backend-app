package com.oocl.todo.serviceTest;

import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import com.oocl.todo.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(todoRepository.findAll()).thenReturn(asList(new Todo("finished work", false)));
        //when
        List<Todo> todoList = todoService.getTodoList();
        //then
        assertEquals(1, todoList.size());

    }
}
