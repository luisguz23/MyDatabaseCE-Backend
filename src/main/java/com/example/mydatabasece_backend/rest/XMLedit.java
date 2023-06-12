package com.example.mydatabasece_backend.rest;

public class XMLedit {
    public static void main(String[] args) {

        String NombreArchivo="Estudiantes";
        // Especifica la ruta y el nombre del archivo XML que deseas crear
        String rutaProyecto = System.getProperty("user.dir");
        String nombreArchivo=NombreArchivo;
        CrearCarpeta.crearCarpeta(NombreArchivo);
        String filePath = rutaProyecto + "/src/XML/BD/"+ NombreArchivo+"/"+ NombreArchivo+".xml";
        String[][] Datos= XMLreader.readXML(filePath);
        editElement(Datos, "Curso", "Datos", "Calculo", filePath);
    }

    public static void editElement(String[][] data, String columnName, String currentValue, String newValue, String filePath) {
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
            return;
        }

        // Buscar el elemento en la columna y realizar el cambio
        for (int i = 1; i < data.length; i++) {
            if (data[i][columnIndex].equals(currentValue)) {
                data[i][columnIndex] = newValue;
            }
        }

       XMLWriter.writeXML(data, filePath);
    }

    //Para colocar un dato donde haya un null que seria la manera que tiene el programa para poner los espacios donde no hay datos
    public static void setDato(String[][] matriz, String nombreColumna, String valorPrimeraFila, String nuevoValor) {
        int numRows = matriz.length;
        int numCols = matriz[0].length;

        int columna = -1; // Índice de la columna
        int primeraFila = -1; // Índice de la primera fila

        // Buscar el índice de la columna y la primera fila
        for (int j = 0; j < numCols; j++) {
            if (matriz[0][j].equals(nombreColumna)) {
                columna = j;
                break;
            }
        }

        for (int i = 1; i < numRows; i++) {
            if (matriz[i][0].equals(valorPrimeraFila)) {
                primeraFila = i;
                break;
            }
        }

        // Verificar si la ubicación ya tiene un valor
        if (columna != -1 && primeraFila != -1) {
            if (matriz[primeraFila][columna] != null) {
                System.out.println("No se puede establecer el valor. La ubicación ya contiene un dato.");
                return;
            }
        }

        // Establecer el nuevo valor en la ubicación
        if (columna != -1 && primeraFila != -1) {
            matriz[primeraFila][columna] = nuevoValor;
            System.out.println("Se ha establecido el valor correctamente.");
        } else {
            System.out.println("No se encontró la ubicación especificada en la matriz.");
        }
    }


}
