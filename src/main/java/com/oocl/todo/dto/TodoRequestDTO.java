package com.oocl.todo.dto;

public class TodoRequestDTO {
    private String content;
    private Boolean status;

    public TodoRequestDTO(String content, Boolean status) {
        this.content = content;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public Boolean getStatus() {
        return status;
    }
}
