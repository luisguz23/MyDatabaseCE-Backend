package com.example.mydatabasece_backend.rest;
import com.example.mydatabasece_backend.Entity.User;
import com.example.mydatabasece_backend.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con la base de datos de usuarios.
 */
@RestController
public class DatabaseController {

    private final UserRepository userRepository;

    /**
     * Constructor de la clase DatabaseController.
     *
     * @param userRepository El repositorio de usuarios utilizado para acceder a la base de datos.
     */
    @Autowired
    public DatabaseController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene una lista de todos los usuarios almacenados en la base de datos.
     *
     * @return Una lista de objetos User que representa los usuarios almacenados.
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Agrega un nuevo usuario a la base de datos.
     *
     * @param user El objeto User que contiene los datos del usuario a agregar.
     * @return Un mensaje indicando el resultado de la operación.
     */
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
