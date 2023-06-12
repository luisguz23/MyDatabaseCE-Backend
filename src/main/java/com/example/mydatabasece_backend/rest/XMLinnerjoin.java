package com.example.mydatabasece_backend.rest;

public class XMLinnerjoin {
    public static void main(String[] args) {
    }
    /**
     * Une dos matrices de datos en una sola matriz.
     * Crea una nueva matriz con el tamaño adecuado y copia los datos de ambas matrices en ella.
     * Devuelve la nueva matriz unida.
     *
     * @param matriz1 la primera matriz que se va a unir
     * @param matriz2 la segunda matriz que se va a unir
     * @return la nueva matriz unida
     */
    public static String[][] unirMatrices(String[][] matriz1, String[][] matriz2) {
        int numRows1 = matriz1.length;
        int numRows2 = matriz2.length;
        int numCols = matriz1[0].length;
        int numCols2 = matriz2[0].length;

        // Crear la nueva matriz con el tamaño adecuado
        String[][] newmat = new String[numRows1][numCols+numCols2];

        // Copiar la primera matriz a la nueva matriz
        for (int i = 0; i < numRows1; i++) {
            for (int j = 0; j < numCols; j++) {
                newmat[i][j] = matriz1[i][j];
            }
        }

        // Copiar la segunda matriz a la nueva matriz
        for (int i = 0; i < numRows2; i++) {
            for (int j = 0; j < numCols2; j++) {
                newmat[i][numCols+j] = matriz2[i][j];
            }
        }

        return newmat;
    }
}
