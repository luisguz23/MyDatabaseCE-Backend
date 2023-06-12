package com.example.mydatabasece_backend.rest;

import java.io.File;

public class EliminarCarpeta {
    public static void main(String[] args) {
        // Obtén la ruta del directorio actual del proyecto
        String rutaProyecto = System.getProperty("user.dir");

        // Especifica el nombre de la carpeta que deseas eliminar
        String nombreCarpeta = "Estudiantes";

        // Crea la ruta completa de la carpeta dentro de "src" con el nombre especificado
        String rutaCarpeta = rutaProyecto + "/src/XML/BD/" + nombreCarpeta;

        // Crea el objeto File con la ruta de la carpeta
        File carpeta = new File(rutaCarpeta);

        // Verifica si la carpeta existe
        if (carpeta.exists() && carpeta.isDirectory()) {
            // Elimina la carpeta y su contenido
            EliminarCarpetaFull(carpeta);
        } else {
            System.out.println("La carpeta no existe.");
        }
    }

    /**
     * Elimina una carpeta y todo su contenido, incluyendo archivos y subdirectorios.
     *
     * @param nombreCarpeta El nombre de la carpeta a eliminar.
     */
    public static void EliminarTodo(String nombreCarpeta){
        // Obtén la ruta del directorio actual del proyecto
        String rutaProyecto = System.getProperty("user.dir");

        // Especifica el nombre de la carpeta que deseas eliminar
       // String nombreCarpeta = "Estudiantes";

        // Crea la ruta completa de la carpeta dentro de "src" con el nombre especificado
        String rutaCarpeta = rutaProyecto + "/src/XML/BD/" + nombreCarpeta;

        // Crea el objeto File con la ruta de la carpeta
        File carpeta = new File(rutaCarpeta);

        // Verifica si la carpeta existe
        if (carpeta.exists() && carpeta.isDirectory()) {
            // Elimina la carpeta y su contenido
            EliminarCarpetaFull(carpeta);
        } else {
            System.out.println("La carpeta no existe.");
        }
    }

    /**
     * Elimina una carpeta y todo su contenido, incluyendo archivos y subdirectorios, de forma recursiva.
     *
     * @param carpeta La carpeta a eliminar.
     */
    private static void EliminarCarpetaFull(File carpeta) {
        // Obtiene la lista de archivos y subdirectorios dentro de la carpeta
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    // Llama recursivamente para eliminar subdirectorios
                    EliminarCarpetaFull(archivo);
                } else {
                    // Elimina los archivos
                    archivo.delete();
                }
            }
        }

        // Elimina la carpeta vacía
        carpeta.delete();

        //Aqui tendria que ir la eliminacion a las referencias del XML para el futuro, o cualquier cosa que se haya guardado sobre el XML para la base de datos

        System.out.println("La carpeta y su contenido se han eliminado correctamente.");
    }
}