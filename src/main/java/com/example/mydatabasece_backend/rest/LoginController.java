package com.example.mydatabasece_backend.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<String> receiveDataFromAngular(@RequestBody MyData data) {
        // Obtener los valores del objeto MyData y guardarlos en variables separadas
        String name = data.getNombre();

        String password = data.getPassword();

        // Realizar cualquier otra lógica necesaria con los datos


        MyResponse response = new MyResponse();
        response.setMessage("Datos recibidos correctamente en Spring");


        // Convertir la respuesta a una cadena de texto
        ObjectMapper mapper = new ObjectMapper();
        String responseString;
        try {
            responseString = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            // Manejar el error en caso de que no se pueda convertir a JSON
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Imprimir los datos recibidos en la consola
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("Nombre: " + name);
        logger.info("Contraseña: " + password);
        logger.info("---------------------------");

        /*if ("Luis".equals(name) && "password".equals(password)){
            response.setMessage("Login Success");
        }
        else { response.setMessage("User does not exist");}
*/
        // Devolver la respuesta como una cadena de texto
        return ResponseEntity.ok(responseString);


    }


    public static class MyData {
        private String name;
        private String password;

        // Getters y setters

        public String getNombre() {
            return name;
        }

        public void setNombre(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class MyResponse {
        private String message;

        // Getters y setters

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
