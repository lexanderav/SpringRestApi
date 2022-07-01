package ru.klyuev.SpringRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.klyuev.SpringRestApi.entity.TodoEntity;

public interface  TodoRepository extends JpaRepository<TodoEntity, Long>  {
}
