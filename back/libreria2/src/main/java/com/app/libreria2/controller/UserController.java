package com.app.libreria2.controller;
import com.app.libreria2.model.User;
import com.app.libreria2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

@Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> findUserById(@PathVariable("id")int id) {
        return repository.findById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User usuario) {
        return repository.save(usuario);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") @RequestBody User usuario){
        return repository.save(usuario);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int id){
        repository.deleteById(id);
    }
}
