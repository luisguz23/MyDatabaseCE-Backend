package com.example.mydatabasece_backend.rest;

import java.io.File;


public class CrearCarpeta {
    public static void main(String[] args) {

    }
    /**
     * Crea una carpeta en la ruta especificada.
     *
     * @param nombreCarpeta El nombre de la carpeta a crear.
     */
    public static void crearCarpeta(String nombreCarpeta){
        // Obtén la ruta del directorio actual del proyecto
        String rutaProyecto = System.getProperty("user.dir");

        // Especifica el nombre de la carpeta que deseas crear
        //String nombreCarpeta = "nombreDeLaCarpeta";

        // Crea la ruta completa de la carpeta dentro de "src" con el nombre especificado
        String rutaCarpeta = rutaProyecto + "/src/XML/BD/" + nombreCarpeta;

        // Crea el objeto File con la ruta de la carpeta
        File carpeta = new File(rutaCarpeta);

        // Verifica si la carpeta no existe
        if (!carpeta.exists()) {
            // Intenta crear la carpeta
            if (carpeta.mkdirs()) {
                System.out.println("La carpeta se ha creado correctamente.");
            } else {
                System.out.println("No se pudo crear la carpeta.");
            }
        } else {
            System.out.println("La carpeta ya existe.");
        }
    }
}