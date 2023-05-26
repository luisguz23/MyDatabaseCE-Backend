package com.example.mydatabasece_backend.rest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController{

    @GetMapping("/prueba")
    public String prueba(){
        return "Conexion Realizada desde el servidor";
    }

}
