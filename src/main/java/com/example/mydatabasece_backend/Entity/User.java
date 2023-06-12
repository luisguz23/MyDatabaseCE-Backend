package com.example.mydatabasece_backend.Entity;

import com.example.mydatabasece_backend.Huffman.HuffmanCompression;
import com.example.mydatabasece_backend.Huffman.HuffmanNode;
import jakarta.persistence.*;

import java.util.Map;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public User() {
        // Constructor vacío requerido por JPA
    }

    public String updatePassword(String newPassword) {

        // Calcular la frecuencia de los caracteres en el texto original
        Map<Character, Integer> frequencyMap = HuffmanCompression.calculateFrequencyMap(password);

        // Construir el árbol de Huffman utilizando la frecuencia de los caracteres
        HuffmanNode root = HuffmanCompression.buildHuffmanTree(frequencyMap);

        // Realizar la compresión
        String compressedString = HuffmanCompression.compress(password, root);
        System.out.println("Compressed String: " + compressedString);


        return newPassword;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void compressPassword() {
        Map<Character, Integer> frequencyMap = HuffmanCompression.calculateFrequencyMap(password);
        HuffmanNode root = HuffmanCompression.buildHuffmanTree(frequencyMap);
        String compressedPassword = HuffmanCompression.compress(password, root);
        password = compressedPassword;
    }
}
