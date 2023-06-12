package com.example.mydatabasece_backend.rest;

import java.util.Arrays;

public class XMLselect{
    public static void main(String[] args) {
        String[][] matrix = {
                {"ID", "Nombre", "Edad", "Ciudad"},
                {"1", "John", "25", "New York"},
                {"2", "Emma", "32", "London"},
                {"3", "Michael", "28", "Paris"},
                {"4", "Sophia", "30", "Rome"}
        };

        System.out.println("Matriz original:");
        printMatrix(matrix);

        System.out.println("\nColumna 'Edad':");
        String[] column = extractColumn(matrix, "Edad");
        printMatrix(new String[][] {column});

        System.out.println("\nColumnas 'Nombre', 'Edad', 'Ciudad':");
        String[][] columns = extractColumns(matrix, "Nombre", "Edad", "Ciudad");
        printMatrix(columns);
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
