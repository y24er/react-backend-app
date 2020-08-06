package com.oocl.todo.handle;

import com.oocl.todo.exception.NotFoundTodoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundTodoException.class)
    public @ResponseBody
    String notFoundTodoExceptionHandler(NotFoundTodoException notFoundTodoException) {
        return notFoundTodoException.getMessage();
    }
}
