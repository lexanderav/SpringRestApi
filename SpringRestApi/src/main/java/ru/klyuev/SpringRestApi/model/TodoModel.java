package ru.klyuev.SpringRestApi.model;

import ru.klyuev.SpringRestApi.entity.TodoEntity;

public class TodoModel {
    private Long id;
    private String title;
    private Boolean completed;
    private String description;

    public static TodoModel toModel(TodoEntity todoEntity) {
        TodoModel todo = new TodoModel();
        todo.setId(todoEntity.getId());
        todo.setTitle(todoEntity.getTitle());
        todo.setCompleted(todoEntity.getCompleted());
        todo.setDescription(todoEntity.getDescription());
        return todo;
    }

    public TodoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
