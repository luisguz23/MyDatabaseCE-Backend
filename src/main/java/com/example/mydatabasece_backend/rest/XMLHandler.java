package com.example.mydatabasece_backend.rest;

import java.util.ResourceBundle;

public class XMLHandler {

    public static MatrixData XMLhandler(String[] Mensaje) {
        MatrixData Repuesta= new MatrixData();

        // Select maximo tres condiciones para la busqueda, usando AND o OR
        if(Mensaje[0].equals("Select")){
            System.out.println("Entro en Select");
            System.out.println(Mensaje.length);
            String NombreTabla=Mensaje[2];
            if (Mensaje.length<=3){
                System.out.println("las instrucciones no tiene where, se retorna la tabla completa");
                Repuesta.setMatrixName(NombreTabla);
                Repuesta.setMatrix(XMLreader.readXML(NombreTabla));
                return Repuesta;
            }
            String[] Condiciones=new String[4];
            int k=0;
            for(int i=3;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];k++;
                } else if (Mensaje[i].equals("and") || Mensaje[i].equals("or")) {
                    System.out.println("se a;adio el siguente del and o el or");
                    Condiciones[k]=Mensaje[i+1];
                    k++;
                }
            }
            System.out.println(Condiciones.length);
            for (int j=0;j< Condiciones.length;j++){
                System.out.println(Condiciones[j]);
            }
            if (Condiciones[1]==null){
                System.out.println("Entro a 1 condiciones");
                Repuesta.setMatrix(XMLselect.extractColumns(XMLreader.readXML(NombreTabla),Condiciones[0]));
            } else if (Condiciones[2]==null) {
                System.out.println("Entro a 2 condiciones");
                Repuesta.setMatrix(XMLselect.extractColumns(XMLreader.readXML(NombreTabla),Condiciones[0],Condiciones[1]));
            } else if (Condiciones[3]==null) {
                System.out.println("Entro a 3 condiciones");
                Repuesta.setMatrix(XMLselect.extractColumns(XMLreader.readXML(Mensaje[2]),Condiciones[0],Condiciones[1],Condiciones[2]));
            }

            Repuesta.setMatrixName(NombreTabla);
            return Repuesta;

        }
        else if (Mensaje[0].equals("InnerJoin")) {
            System.out.println("Entro en InnerJoin");
            String NombreTabla1=Mensaje[2];String NombreTabla2=Mensaje[4];
            String innerjoin=NombreTabla1+NombreTabla2;
            String[] Condiciones=new String[4];
            String[][] Matriz3=XMLinnerjoin.unirMatrices(XMLreader.readXML(NombreTabla1),XMLreader.readXML(NombreTabla2));
            int k=0;
            for(int i=5;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];k++;
                } else if (Mensaje[i].equals("and") || Mensaje[i].equals("or")) {
                    System.out.println("se a;adio el siguente del and o el or");
                    Condiciones[k]=Mensaje[i+1];
                    k++;
                }
            }
            System.out.println(Condiciones.length);
            for (int j=0;j< Condiciones.length;j++){
                System.out.println(Condiciones[j]);
            }
            if (Condiciones[1]==null){
                System.out.println("Entro a 1 condiciones");
                Repuesta.setMatrix(XMLselect.extractColumns(Matriz3,Condiciones[0]));
            } else if (Condiciones[2]==null) {
                System.out.println("Entro a 2 condiciones");
                Repuesta.setMatrix(XMLselect.extractColumns(Matriz3,Condiciones[0],Condiciones[1]));
            } else if (Condiciones[3]==null) {
                System.out.println("Entro a 3 condiciones");
                Repuesta.setMatrix(XMLselect.extractColumns(Matriz3,Condiciones[0],Condiciones[1],Condiciones[2]));
            }
            Repuesta.setMatrixName(innerjoin);
            return Repuesta;




        } else if (Mensaje[0].equals("Update")) {
            System.out.println("Entro en Update");

        } else if (Mensaje[0].equals("Delete")) {
            System.out.println("Entro en Delete");

        }else if (Mensaje[0].equals("Insert")){
            System.out.println("Insert");

        }



        return Repuesta;
    }
}





// Select maximo tres condiciones para la busqueda, usando AND o OR LISTO--
//Innerjoin entre dos XML Store y Select maximo tres condiciones para la busqueda, usando AND o OR LISTO--
//Insert Insertar datos a un XML Store, uno o varios un solo script
//Delete WhereEliminar todas las intancias de un XMLStore
//Delete Eliminar ciertas insntacias de un XMLStore
//Delete all Eliminar la carpeta y el XMLStore
//update Actualizar una instancia de un XML Store
//update actuizasr un conjunto especifico de instancias en un XML Store
