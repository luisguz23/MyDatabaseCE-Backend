package com.example.mydatabasece_backend.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para gestionar las instrucciones recibidas mediante endpoints REST.
 */
@RestController
public class InstructionController {


    /**
     * Maneja las instrucciones recibidas en el endpoint "/your-endpoint".
     *
     * @param instructions Las instrucciones recibidas como cadena JSON en el cuerpo de la solicitud.
     * @return Un mapa que contiene la respuesta generada.
     */
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



    /**
     * Maneja las instrucciones recibidas en el endpoint "/commit".
     * En este caso el clas funciones haran los cambios  también en la base de datos
     *
     * @param instructions Las instrucciones recibidas como cadena JSON en el cuerpo de la solicitud.
     * @return Un mapa que contiene la respuesta generada.
     */
        @PostMapping("/commit")
        public Map<String, Object> handleInstructions2(@RequestBody String instructions) {
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
            MatrixData Repuesta=XMLHandler.XMLhandler2(palabras);

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

