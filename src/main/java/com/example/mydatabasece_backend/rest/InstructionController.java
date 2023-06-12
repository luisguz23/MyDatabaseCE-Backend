package com.example.mydatabasece_backend.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InstructionController {

    @PostMapping("/your-endpoint")
    public Map<String, Object> handleInstructions(@RequestBody String instructions) {
        // Eliminar espacios en blanco
        instructions = instructions.trim();

        // Eliminar {"instructions": del primer dato
        if (instructions.startsWith("{\"instructions\":\"")) {
            instructions = instructions.substring(17);
        }

        // Eliminar "} del último dato
        if (instructions.endsWith("\"}")) {
            instructions = instructions.substring(0, instructions.length() - 2);
        }

        // Separar el mensaje en palabras y eliminar los saltos de línea
        String[] palabras = instructions.split("\\s+|\n");
        System.out.println("Mensaje completo:");
        System.out.println(palabras);

        // Procesar las palabras como desees
        for (int i = 0; i < palabras.length; i++) {
            System.out.println(palabras[i]);
        }
        MatrixData Repuesta=XMLHandler.XMLhandler(palabras);

        // Generar la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("matrix",Repuesta.getMatrix());
        System.out.println(Repuesta.getMatrixName());
        response.put("name", Repuesta.getMatrixName());

        System.out.println("Instrucciones recibidas");
        //String response = "Instrucciones recibidas correctamente";
        return response;
    }



}

