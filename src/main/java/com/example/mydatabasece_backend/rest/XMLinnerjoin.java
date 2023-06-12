package com.example.mydatabasece_backend.rest;

public class XMLinnerjoin {
    public static void main(String[] args) {
        String nombreArchivo = "Estudiantes";
        //String rutaProyecto = System.getProperty("user.dir");
        //String filePath = rutaProyecto + "/src/XML/BD/" + nombreArchivo + "/" + nombreArchivo + ".xml";
        //XMLreader.readXML(filePath);

        String nombreArchivo2 = "NotaCursos";
        //String rutaProyecto2 = System.getProperty("user.dir");
        //String filePath2 = rutaProyecto2 + "/src/XML/BD/" + nombreArchivo2 + "/" + nombreArchivo2 + ".xml";
        //XMLreader.readXML(filePath2);
        String[][] Matriz1= XMLreader.readXML(nombreArchivo);
        String[][] Matriz2= XMLreader.readXML(nombreArchivo2);
        System.out.println("\n"+"Matriz Innerjoin"+"\n");
        // Imprimir la matriz de datos
        for (int i = 0; i < Matriz2.length; i++) {
            for (int j = 0; j < Matriz2[0].length; j++) {
                System.out.print(Matriz2[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n"+"La otra matriz"+"\n");
        for (int i = 0; i < Matriz1.length; i++) {
            for (int j = 0; j < Matriz1[0].length; j++) {
                System.out.print(Matriz1[i][j] + " ");
            }
            System.out.println();
        }
        String[][] Matriz3=unirMatrices(Matriz1,Matriz2);

        System.out.println("\n"+"La nueva matriz"+"\n");
        for (int i = 0; i < Matriz3.length; i++) {
            for (int j = 0; j < Matriz3[0].length; j++) {
                System.out.print(Matriz3[i][j] + " ");
            }
            System.out.println();
        }
        String nombreArchivo3= nombreArchivo+nombreArchivo2;
        String rutaProyecto3 = System.getProperty("user.dir");
        String filePath3 = rutaProyecto3 + "/src/XML/BD/" + nombreArchivo3 + "/" + nombreArchivo3 + ".xml";
        XMLWriter.writeXML(Matriz3,nombreArchivo3);

    }
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
