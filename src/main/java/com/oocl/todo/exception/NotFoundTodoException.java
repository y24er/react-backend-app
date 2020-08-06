package com.oocl.todo.exception;

public class NotFoundTodoException extends RuntimeException {
    private static final String NOT_FOUNT_TODO = "Not found this todo!";

    @Override
    public String getMessage() {
        return NOT_FOUNT_TODO;
    }
}
