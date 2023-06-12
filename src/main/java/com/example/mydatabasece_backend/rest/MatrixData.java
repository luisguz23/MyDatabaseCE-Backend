package com.example.mydatabasece_backend.rest;

public class MatrixData {
    private String matrixName;
    private String[][] matrix;

    public String getMatrixName() {
        return matrixName;
    }

    public void setMatrixName(String matrixName) {
        this.matrixName = matrixName;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }
}