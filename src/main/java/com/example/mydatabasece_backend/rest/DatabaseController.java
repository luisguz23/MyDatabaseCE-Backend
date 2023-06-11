package com.example.mydatabasece_backend.rest;
import com.example.mydatabasece_backend.Entity.User;
import com.example.mydatabasece_backend.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseController{

    @Autowired
    public DatabaseController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;




    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

}
