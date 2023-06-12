package com.example.mydatabasece_backend.rest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter {
    public static void main(String[] args) {
        /*
        String[][] Datos = {
                {"ID", "Nombre", "Edad", "Carrera", "Curso"},
                {"3322", "Carlos", "24", "Computadores", "Datos"},
                {"1024", "Felipe", "20", "Industrial", "Calculo"},
                {"3052", "Juan", "22", "Electronica", "Fisica"},
                {"2060", "Jose", "25", "Computadores", "Calculo"}
        };
        String NombreArchivo="Estudiantes";
        // Especifica la ruta y el nombre del archivo XML que deseas crear
        String rutaProyecto = System.getProperty("user.dir");
        String nombreArchivo=NombreArchivo;
        CrearCarpeta.crearCarpeta(NombreArchivo);
        String filePath = rutaProyecto + "/src/"+ NombreArchivo+"/"+ NombreArchivo+".xml";

       // String filePath = "ruta/del/archivo/datos.xml"; // Cambiar la ruta y el nombre del archivo según sea necesario
        writeXML(Datos, filePath);

         */
    }

    public static void writeXML(String[][] data, String NombreArchivo) {
        try {
            // Especifica la ruta y el nombre del archivo XML que deseas crear
            String rutaProyecto = System.getProperty("user.dir");
            String nombreArchivo=NombreArchivo;
            CrearCarpeta.crearCarpeta(NombreArchivo);
            String filePath = rutaProyecto + "/src/XML/BD/"+ NombreArchivo+"/"+ NombreArchivo+".xml";
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<data>\n");

            String[] headers = data[0];
            for (int i = 1; i < data.length; i++) {
                writer.write("\t<row>\n");
                for (int j = 0; j < headers.length; j++) {
                    String header = headers[j];
                    String value = data[i][j];
                    writer.write("\t\t<" + header + ">" + value + "</" + header + ">\n");
                }
                writer.write("\t</row>\n");
            }

            writer.write("</data>");
            writer.close();

            System.out.println("Archivo XML creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo XML: " + e.getMessage());
        }
    }
}

