package ru.klyuev.SpringRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.klyuev.SpringRestApi.entity.UserEntity;
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String user);
}
