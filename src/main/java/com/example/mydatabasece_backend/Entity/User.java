package com.example.mydatabasece_backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String email;

    public User() {
        // Constructor vacío requerido por JPA
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
