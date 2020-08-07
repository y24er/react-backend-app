package com.oocl.todo.dto;

public class TodoResponseDTO {
    private Integer id;
    private String content;
    private Boolean status;

    public TodoResponseDTO(Integer id, String content, Boolean status) {
        this.id = id;
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
