package ru.klyuev.SpringRestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.klyuev.SpringRestApi.entity.UserEntity;
import ru.klyuev.SpringRestApi.exception.UserAlreadyExistException;
import ru.klyuev.SpringRestApi.exception.UserNotFoundException;
import ru.klyuev.SpringRestApi.model.UserModel;
import ru.klyuev.SpringRestApi.service.UserService;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    //https://javarush.ru/groups/posts/2488-obzor-rest-chastjh-3-sozdanie-restful-servisa-na-spring-boot

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try {
            userService.create(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> readUsers() {
        final List<UserModel> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity readUser(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(userService.read(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }


//        final User user = userService.read(id);
//
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }

//        final boolean delete = userService.delete(id);
//        return delete
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,
                                    @RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok(userService.update(user, id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }

//        return update
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
