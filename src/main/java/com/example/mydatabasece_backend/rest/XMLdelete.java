package com.example.mydatabasece_backend.rest;

public class XMLdelete {
    public static void main(String[] args) {

    }
    public static void eliminarDato(String[][] matriz, String nombreColumna, String valorBusqueda,String filePath) {
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
                    XMLWriter.writeXML(matriz, filePath);
                    return;
                }
            }
            System.out.println("No se encontró el valor especificado en la matriz.");
        } else {
            System.out.println("No se encontró la columna especificada en la matriz.");
        }
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


}
