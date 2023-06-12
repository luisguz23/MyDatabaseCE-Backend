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

    /**
     * Edita un elemento en la matriz de datos.
     * Busca el elemento en la columna especificada y lo cambia por el nuevo valor.
     * Devuelve la matriz actualizada.
     *
     * @param data la matriz de datos que se va a actualizar
     * @param columnName el nombre de la columna en la que se va a buscar el elemento
     * @param currentValue el valor actual del elemento que se va a cambiar
     * @param newValue el nuevo valor que se va a establecer para el elemento
     * @param NombreArchivo el nombre del archivo XML que contiene la matriz
     * @return la matriz actualizada con el elemento editado
     */
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

       //XMLWriter.writeXML(data, NombreArchivo);
        return data;
    }

    /**
     * Establece el nuevo dato en las celdas nulas de la columna especificada en la matriz.
     * Devuelve la matriz actualizada.
     *
     * @param matriz la matriz que se va a actualizar
     * @param nombreColumna el nombre de la columna en la que se va a establecer el nuevo dato
     * @param nuevoDato el nuevo dato que se va a establecer en las celdas nulas
     * @return la matriz actualizada con el nuevo dato establecido
     */
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
