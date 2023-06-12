package com.example.mydatabasece_backend.rest;

import java.util.Arrays;

public class XMLselect{
    public static void main(String[] args) {
    }

    public static String[] extractColumn(String[][] matrix, String columnName) {
        int columnIndex = findColumnIndex(matrix, columnName);

        if (columnIndex == -1) {
            System.out.println("La columna '" + columnName + "' no existe en la matriz.");
            return new String[0];
        }

        String[] column = new String[matrix.length - 1];

        for (int i = 1; i < matrix.length; i++) {
            column[i - 1] = matrix[i][columnIndex];
        }

        return column;
    }
    /**
     * Extrae las columnas especificadas de una matriz de datos y devuelve una nueva matriz
     * que contiene solo las columnas seleccionadas.
     *
     * @param matrix       La matriz de datos de la cual extraer las columnas.
     * @param columnNames  Los nombres de las columnas a extraer.
     * @return Una nueva matriz que contiene solo las columnas seleccionadas.
     */

    public static String[][] extractColumns(String[][] matrix, String... columnNames) {
        int[] columnIndices = new int[columnNames.length];

        for (int i = 0; i < columnNames.length; i++) {
            columnIndices[i] = findColumnIndex(matrix, columnNames[i]);
        }

        String[][] result = new String[matrix.length - 1][columnNames.length];

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                result[i - 1][j] = matrix[i][columnIndices[j]];
            }
        }

        return result;
    }
    /**
     * Busca el índice de una columna específica en una matriz de datos.
     *
     * @param matrix      La matriz de datos en la que buscar la columna.
     * @param columnName  El nombre de la columna a buscar.
     * @return El índice de la columna buscada, o -1 si no se encuentra.
     */

    private static int findColumnIndex(String[][] matrix, String columnName) {
        int columnIndex = -1;

        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j].equals(columnName)) {
                columnIndex = j;
                break;
            }
        }

        return columnIndex;
    }

    private static void printMatrix(String[][] matrix) {
        // Imprimir la matriz de datos
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
