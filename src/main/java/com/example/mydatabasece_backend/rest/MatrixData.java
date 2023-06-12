package com.example.mydatabasece_backend.rest;

/**
 * Representa una matriz de datos con su nombre.
 */
public class MatrixData {
    private String matrixName;
    private String[][] matrix;

    /**
     * Obtiene el nombre de la matriz.
     *
     * @return El nombre de la matriz.
     */
    public String getMatrixName() {
        return matrixName;
    }

    /**
     * Establece el nombre de la matriz.
     *
     * @param matrixName El nombre de la matriz.
     */
    public void setMatrixName(String matrixName) {
        this.matrixName = matrixName;
    }

    /**
     * Obtiene la matriz de datos.
     *
     * @return La matriz de datos.
     */
    public String[][] getMatrix() {
        return matrix;
    }

    /**
     * Establece la matriz de datos.
     *
     * @param matrix La matriz de datos.
     */
    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }
}
