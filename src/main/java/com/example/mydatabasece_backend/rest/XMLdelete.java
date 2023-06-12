package com.example.mydatabasece_backend.rest;

import java.util.Arrays;

public class XMLdelete {
    public static void main(String[] args) {

    }
    public static String[][] eliminarDato(String[][] matriz, String nombreColumna, String valorBusqueda, String NombreArchivo) {
        String rutaProyecto = System.getProperty("user.dir");
        String filePath = rutaProyecto + "/src/XML/BD/"+ NombreArchivo+"/"+ NombreArchivo+".xml";
        int numRows = matriz.length;
        int numCols = matriz[0].length;

        int columna = -1; // Índice de la columna

        // Buscar el índice de la columna
        for (int j = 0; j < numCols; j++) {
            if (matriz[0][j].equals(nombreColumna)) {
                columna = j;
                break;
            }
        }

        // Verificar si se encontró la columna
        if (columna != -1) {
            // Buscar el valor de búsqueda y establecerlo como null si se encuentra
            for (int i = 1; i < numRows; i++) {
                if (matriz[i][columna].equals(valorBusqueda)) {
                    matriz[i][columna] = null;
                    System.out.println("Se ha eliminado el dato correctamente.");
                    XMLWriter.writeXML(matriz, NombreArchivo);
                    return matriz;
                }
            }
            System.out.println("No se encontró el valor especificado en la matriz.");
        } else {
            System.out.println("No se encontró la columna especificada en la matriz.");
        }
        return matriz;
    }

    public static void eliminarDatos(String[][] matriz, String nombreColumna, String valorBusqueda,String filePath) {
        int numRows = matriz.length;
        int numCols = matriz[0].length;

        int columna = -1; // Índice de la columna

        // Buscar el índice de la columna
        for (int j = 0; j < numCols; j++) {
            if (matriz[0][j].equals(nombreColumna)) {
                columna = j;
                break;
            }
        }

        // Verificar si se encontró la columna
        if (columna != -1) {
            int contadorEliminados = 0; // Contador de filas eliminadas

            // Eliminar las filas que contienen el valor de búsqueda
            for (int i = 1; i < numRows; i++) {
                if (matriz[i][columna].equals(valorBusqueda)) {
                    matriz[i] = new String[numCols]; // Crear una nueva fila vacía
                    contadorEliminados++;
                }
            }

            if (contadorEliminados > 0) {
                System.out.println("Se han eliminado " + contadorEliminados + " filas con el valor especificado.");
                XMLWriter.writeXML(matriz, filePath);
            } else {
                System.out.println("No se encontraron filas con el valor especificado en la matriz.");
            }
        } else {
            System.out.println("No se encontró la columna especificada en la matriz.");
        }
    }
    public static String[][] llenarMatrizConNulls(String[][] matriz, String NombreArchivo) {
        int numRows = matriz.length;
        int numCols = matriz[0].length;

        // Llenar la matriz con nulls excepto la primera fila
        for (int i = 1; i < numRows; i++) {
            Arrays.fill(matriz[i], null); // Llenar la fila con nulls
        }

        // Guardar la matriz actualizada en el archivo XML
        XMLWriter.writeXML(matriz, NombreArchivo);
        System.out.println("La matriz se ha llenado con nulls, excepto la primera fila con los datos intactos.");
        return matriz;


    }




}
