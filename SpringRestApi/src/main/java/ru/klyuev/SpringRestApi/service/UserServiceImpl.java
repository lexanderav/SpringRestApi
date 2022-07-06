package ru.klyuev.SpringRestApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.klyuev.SpringRestApi.entity.UserEntity;
import ru.klyuev.SpringRestApi.exception.UserAlreadyExistException;
import ru.klyuev.SpringRestApi.exception.UserNotFoundException;
import ru.klyuev.SpringRestApi.model.UserModel;
import ru.klyuev.SpringRestApi.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity create(UserEntity user) throws UserAlreadyExistException {
        if (userRepository.findByUsername(user.getUsername()) != null ) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userRepository.save(user);
    }

    @Override
    public List<UserModel> readAll() {
        return userRepository.findAll().stream().map(UserModel::toModel).collect(Collectors.toList());
    }

    @Override
    public UserModel read(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return UserModel.toModel(user);
    }

    @Override
    public boolean update(UserEntity user, Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        user.setId(id);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean delete(Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        userRepository.deleteById(id);
        return true;
    }
}
