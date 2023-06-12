package com.example.mydatabasece_backend.rest;

public class XMLedit {
    public static void main(String[] args) {

        String NombreArchivo="Estudiantes";
        // Especifica la ruta y el nombre del archivo XML que deseas crear
        String rutaProyecto = System.getProperty("user.dir");
        CrearCarpeta.crearCarpeta(NombreArchivo);
        String filePath = rutaProyecto + "/src/XML/BD/"+ NombreArchivo+"/"+ NombreArchivo+".xml";
        String[][] Datos= XMLreader.readXML(filePath);
        editElement(Datos, "Curso", "Datos", "Calculo", filePath);
    }

    public static String[][] editElement(String[][] data, String columnName, String currentValue, String newValue, String NombreArchivo) {
        String rutaProyecto = System.getProperty("user.dir");
        String filePath = rutaProyecto + "/src/XML/BD/"+ NombreArchivo+"/"+ NombreArchivo+".xml";
        int columnIndex = -1;
        String[] headers = data[0];

        // Buscar el índice de la columna basado en el nombre
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
            System.out.println("La columna especificada no existe.");
            return data;
        }

        // Buscar el elemento en la columna y realizar el cambio
        for (int i = 1; i < data.length; i++) {
            if (data[i][columnIndex].equals(currentValue)) {
                data[i][columnIndex] = newValue;
            }
        }

       XMLWriter.writeXML(data, NombreArchivo);
        return data;
    }

    public static String[][] setDato(String[][] matriz, String nombreColumna, String nuevoDato) {
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
            // Recorrer las filas (excluyendo la primera fila) y reemplazar los valores nulos
            for (int i = 1; i < numRows; i++) {
                if (matriz[i][columna].equals("null")) {
                    matriz[i][columna] = nuevoDato;
                }
            }
            System.out.println("Se han establecido los datos correctamente en la columna " + nombreColumna + ".");
        } else {
            System.out.println("No se encontró la columna especificada en la matriz.");
        }

        return matriz;
    }



}
