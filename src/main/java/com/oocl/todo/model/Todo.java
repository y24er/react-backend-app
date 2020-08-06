package com.oocl.todo.model;

public class Todo {
    private Integer id;
    private String content;
    private Boolean status;

    public Todo() {
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Boolean getStatus() {
        return status;
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
