package com.example.mydatabasece_backend.rest;
import com.example.mydatabasece_backend.Entity.User;
import com.example.mydatabasece_backend.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DatabaseController {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user) {
        if (user != null) {
            user.compressPassword(); // Aplicar compresión Huffman al password
            userRepository.save(user);
            return "Datos de usuario recibidos correctamente";
        } else {
            return "Error al recibir los datos de usuario";
        }
    }

}
