package com.example.mydatabasece_backend.rest;

import com.example.mydatabasece_backend.ConexionArduino.SerialCommunication;
import com.example.mydatabasece_backend.Entity.User;
import com.example.mydatabasece_backend.Huffman.HuffmanCompression;
import com.example.mydatabasece_backend.Huffman.HuffmanNode;
import com.example.mydatabasece_backend.Repo.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Controlador para manejar las solicitudes de inicio de sesión.
 */
@RestController
public class LoginController {

    private final UserRepository userRepository;

    /**
     * Constructor de la clase LoginController.
     *
     * @param userRepository Repositorio de usuarios para acceder a la base de datos.
     */
    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Maneja la solicitud POST para recibir datos desde Angular y realizar el proceso de inicio de sesión.
     *
     * @param data Datos recibidos desde Angular.
     * @return Una respuesta HTTP que indica si el inicio de sesión fue exitoso o no.
     */
    @PostMapping("/login")
    public ResponseEntity<MyResponse> receiveDataFromAngular(@RequestBody MyData data) {
        String copia = SerialCommunication.lectorArduino();
        Logger logger = Logger.getLogger(LoginController.class.getName());
        String name = data.getName();
        String password = data.getPassword();

        // Calcular la frecuencia de los caracteres en el texto original
        Map<Character, Integer> frequencyMap = HuffmanCompression.calculateFrequencyMap(password);

        // Construir el árbol de Huffman utilizando la frecuencia de los caracteres
        HuffmanNode root = HuffmanCompression.buildHuffmanTree(frequencyMap);

        // Comprimir el password utilizando HuffmanCompression
        String compressedPassword = HuffmanCompression.compress(password, root);

        // Obtener el usuario de la base de datos
        User user = userRepository.findByUsername(name);

        MyResponse response = new MyResponse();
        response.setMessage("Datos recibidos correctamente en Spring");
        logger.info("Recibiendo datos de Angular: nombre=" + data.getName() + ", contraseña=" + data.getPassword());

        System.out.println(name);
        System.out.println(compressedPassword);

        if (user != null && compressedPassword.equals(user.getPassword())) {
            response.setMessage("Login Success");
            //Buzzer
            SerialCommunication.enviarDato("3");

            if (password.equals(copia)){
                System.out.println(copia);
                System.out.println("Si es la contra");

            }

        } else {
            //Led
            SerialCommunication.enviarDato("2");
            response.setMessage("User does not exist or invalid password");
        }

        // Devolver la respuesta
        return ResponseEntity.ok(response);
    }

    /**
     * Clase interna que representa los datos enviados desde Angular en la solicitud de inicio de sesión.
     */
    public static class MyData {
        private String name;
        private String password;

        // Getters y setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * Clase interna que representa la respuesta del servidor para la solicitud de inicio de sesión.
     */
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
