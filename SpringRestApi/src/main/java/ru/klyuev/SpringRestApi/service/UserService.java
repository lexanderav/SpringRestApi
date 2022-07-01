package ru.klyuev.SpringRestApi.service;

import ru.klyuev.SpringRestApi.entity.UserEntity;
import ru.klyuev.SpringRestApi.exception.UserAlreadyExistException;
import ru.klyuev.SpringRestApi.exception.UserNotFoundException;
import ru.klyuev.SpringRestApi.model.UserModel;

import java.util.List;

public interface UserService {
    UserEntity create(UserEntity user) throws UserAlreadyExistException;
    List<UserModel> readAll();
    UserModel read(Long id) throws UserNotFoundException;
    boolean update(UserEntity user, Long id) throws UserNotFoundException;
    boolean delete(Long id) throws UserNotFoundException;
    UserEntity readByUsername(String user);
}
