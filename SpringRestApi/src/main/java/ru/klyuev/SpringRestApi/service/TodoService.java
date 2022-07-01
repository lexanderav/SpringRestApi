package ru.klyuev.SpringRestApi.service;

import ru.klyuev.SpringRestApi.entity.TodoEntity;
import ru.klyuev.SpringRestApi.exception.UserNotFoundException;
import ru.klyuev.SpringRestApi.model.TodoModel;

public interface TodoService {
    TodoModel create(TodoEntity todoEntity, Long userId) throws UserNotFoundException;
    TodoModel complete(Long id);
}
