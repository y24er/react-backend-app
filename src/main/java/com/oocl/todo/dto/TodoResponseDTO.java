package com.oocl.todo.dto;

public class TodoResponseDTO {
    private Integer id;
    private String content;
    private Boolean status;

    public TodoResponseDTO() {
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
