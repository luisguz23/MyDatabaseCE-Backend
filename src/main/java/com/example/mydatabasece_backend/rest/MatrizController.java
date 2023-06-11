package com.example.mydatabasece_backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MatrizController {

    @GetMapping("/matriz")
    public int[][] getMatriz() {
        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        return matriz;
    }
}
