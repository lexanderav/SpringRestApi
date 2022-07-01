package ru.klyuev.SpringRestApi.service;

import org.springframework.stereotype.Service;
import ru.klyuev.SpringRestApi.entity.TodoEntity;
import ru.klyuev.SpringRestApi.entity.UserEntity;
import ru.klyuev.SpringRestApi.model.TodoModel;
import ru.klyuev.SpringRestApi.repository.TodoRepository;
import ru.klyuev.SpringRestApi.repository.UserRepository;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }
    public TodoModel create(TodoEntity todo, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        todo.setUser(user);
        return TodoModel.toModel(todoRepository.save(todo));
    }


    public TodoModel complete(Long id) {
        TodoEntity todo = todoRepository.findById(id).get();
        todo.setCompleted(!todo.getCompleted());
        return TodoModel.toModel(todoRepository.save(todo));
    }


}
