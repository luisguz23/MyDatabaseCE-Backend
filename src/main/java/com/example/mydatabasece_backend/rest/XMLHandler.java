package com.example.mydatabasece_backend.rest;

import com.example.mydatabasece_backend.ConexionArduino.SerialCommunication;

import javax.sound.midi.Soundbank;
import java.util.ResourceBundle;

public class XMLHandler {

    /**
     * Maneja las instrucciones recibidas en forma de arreglo y realiza operaciones en base a esas instrucciones.
     *
     * @param Mensaje Arreglo de cadenas que contiene las instrucciones a procesar.
     * @return Objeto de la clase MatrixData que contiene la respuesta generada por las operaciones.
     */
    public static MatrixData XMLhandler(String[] Mensaje) {
        System.out.println("Entro al primer Handler para no cambiar la BD");
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
            String NombreTabla=Mensaje[2];
            String[] Condiciones=new String[4];
            int k=0;
            for(int i=3;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                } else if (Mensaje[i].equals("Values")) {
                    System.out.println("se a;adio el el valor actual");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                } else if (Mensaje[i].equals("por"))  {
                    System.out.println("se a;adio el el valor nuevo");
                    Condiciones[k]=Mensaje[i+1];
                }
            }
            System.out.println("NombreDeLaTabla: "+NombreTabla);
            System.out.println("ColumnName: "+Condiciones[0]);
            System.out.println("currentValue: "+Condiciones[1]);
            System.out.println("newValue: "+Condiciones[2]);
            String[][] Matriz3=XMLedit.editElement(XMLreader.readXML(NombreTabla),Condiciones[0],Condiciones[1],Condiciones[2],NombreTabla);
            Repuesta.setMatrixName(NombreTabla);
            Repuesta.setMatrix(Matriz3);
            return Repuesta;

        } else if (Mensaje[0].equals("Delete")) {
            System.out.println("Entro en Delete");

            String NombreTabla=Mensaje[2];
            String[] Condiciones=new String[4];
            int k=0;
            if (Mensaje.length<=3){
                System.out.println("las instrucciones no tiene where,se elimino la carpeta y el XML");
                Repuesta.setMatrixName(NombreTabla);
                Repuesta.setMatrix(XMLdelete.llenarMatrizConNulls(XMLreader.readXML(NombreTabla),NombreTabla));
                //Aqui va el led
                SerialCommunication.enviarDato("2");
                return Repuesta;
            }
            for(int i=3;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];k++;
                } else if (Mensaje[i].equals("Value")) {
                    System.out.println("se a;adio el siguente del Value");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                }else if(Mensaje[i].equals("all")){
                    EliminarCarpeta.EliminarTodo(NombreTabla);
                    Repuesta.setMatrixName("Se elimino por completo la tabla: "+NombreTabla);
                    //Aqui va el led
                    SerialCommunication.enviarDato("2");
                    return Repuesta;

                }
            }
            System.out.println("Where: "+Condiciones[0]);
            System.out.println("Value: "+Condiciones[1]);
            String[][] Matriz3=XMLdelete.eliminarDato(XMLreader.readXML(NombreTabla),Condiciones[0],Condiciones[1],NombreTabla);
            Repuesta.setMatrixName(NombreTabla);
            Repuesta.setMatrix(Matriz3);
            //Aqui va el led
            SerialCommunication.enviarDato("2");
            return Repuesta;


        }else if (Mensaje[0].equals("Insert")) {
            System.out.println("Insert");
            String NombreTabla = Mensaje[2];
            String[] Condiciones=new String[4];
            int k=0;
            for(int i=3;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                } else if (Mensaje[i].equals("Value")) {
                    System.out.println("se a;adio el el valor actual");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                }
            }
            System.out.println("NombreTabla: "+NombreTabla);
            System.out.println("Where: "+Condiciones[0]);
            System.out.println("Value: "+Condiciones[1]);
            String[][] Matriz3=XMLedit.setDato(XMLreader.readXML(NombreTabla),Condiciones[0],Condiciones[1]);
            Repuesta.setMatrixName(NombreTabla);
            Repuesta.setMatrix(Matriz3);
            return Repuesta;
        }else if (Mensaje[0].equals("Reinicio")){
            TablaEjemplos.Creartablas();
        }else if (Mensaje[0].equals("EliminarTodo")){
            TablaEjemplos.Creartablas();
        }
        return Repuesta;
    }
    /**
     * Maneja las instrucciones recibidas en forma de arreglo y realiza operaciones en base a esas instrucciones.
     * En este caso funciona como commit, ya que si esta esta encargada de cambiar los datos en la base de datos y no quedarse solamente en local
     *
     * @param Mensaje Arreglo de cadenas que contiene las instrucciones a procesar.
     * @return Objeto de la clase MatrixData que contiene la respuesta generada por las operaciones.
     */
    public static MatrixData XMLhandler2(String[] Mensaje) {
        System.out.println("Entro al segundo Handler para cambiar la BD");
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
            XMLWriter.writeXML(Matriz3, innerjoin);
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
            String NombreTabla=Mensaje[2];
            String[] Condiciones=new String[4];
            int k=0;
            for(int i=3;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                } else if (Mensaje[i].equals("Values")) {
                    System.out.println("se a;adio el el valor actual");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                } else if (Mensaje[i].equals("por"))  {
                    System.out.println("se a;adio el el valor nuevo");
                    Condiciones[k]=Mensaje[i+1];
                }
            }
            System.out.println("NombreDeLaTabla: "+NombreTabla);
            System.out.println("ColumnName: "+Condiciones[0]);
            System.out.println("currentValue: "+Condiciones[1]);
            System.out.println("newValue: "+Condiciones[2]);
            String[][] Matriz3=XMLedit.editElement(XMLreader.readXML(NombreTabla),Condiciones[0],Condiciones[1],Condiciones[2],NombreTabla);
            Repuesta.setMatrixName(NombreTabla);
            Repuesta.setMatrix(Matriz3);
            return Repuesta;

        } else if (Mensaje[0].equals("Delete")) {
            System.out.println("Entro en Delete");

            String NombreTabla=Mensaje[2];
            String[] Condiciones=new String[4];
            int k=0;
            if (Mensaje.length<=3){
                System.out.println("las instrucciones no tiene where,se elimino la carpeta y el XML");
                String[][] Matriz3=XMLdelete.llenarMatrizConNulls(XMLreader.readXML(NombreTabla),NombreTabla);
                Repuesta.setMatrixName(NombreTabla);
                Repuesta.setMatrix(Matriz3);
                XMLWriter.writeXML(Matriz3, NombreTabla);

                //Aqui va el led
                SerialCommunication.enviarDato("2");
                return Repuesta;
            }
            for(int i=3;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];k++;
                } else if (Mensaje[i].equals("Value")) {
                    System.out.println("se a;adio el siguente del Value");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                }else if(Mensaje[i].equals("all")){
                    EliminarCarpeta.EliminarTodo(NombreTabla);
                    Repuesta.setMatrixName("Se elimino por completo la tabla: "+NombreTabla);
                    //Aqui va el led
                    SerialCommunication.enviarDato("2");
                    return Repuesta;

                }
            }
            System.out.println("Where: "+Condiciones[0]);
            System.out.println("Value: "+Condiciones[1]);
            String[][] Matriz3=XMLdelete.eliminarDato(XMLreader.readXML(NombreTabla),Condiciones[0],Condiciones[1],NombreTabla);
            Repuesta.setMatrixName(NombreTabla);
            Repuesta.setMatrix(Matriz3);
            XMLWriter.writeXML(Matriz3, NombreTabla);
            //Aqui va el led
            SerialCommunication.enviarDato("2");
            return Repuesta;


        }else if (Mensaje[0].equals("Insert")) {
            System.out.println("Insert");
            String NombreTabla = Mensaje[2];
            String[] Condiciones=new String[4];
            int k=0;
            for(int i=3;i< Mensaje.length;i++){
                if(Mensaje[i].equals("Where")  ){
                    System.out.println("Se a;ade el 2 hacia adelante del where");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                } else if (Mensaje[i].equals("Value")) {
                    System.out.println("se a;adio el el valor actual");
                    Condiciones[k]=Mensaje[i+2];
                    k++;
                }
            }
            System.out.println("NombreTabla: "+NombreTabla);
            System.out.println("Where: "+Condiciones[0]);
            System.out.println("Value: "+Condiciones[1]);
            String[][] Matriz3=XMLedit.setDato(XMLreader.readXML(NombreTabla),Condiciones[0],Condiciones[1]);
            Repuesta.setMatrixName(NombreTabla);
            Repuesta.setMatrix(Matriz3);
            XMLWriter.writeXML(Matriz3, NombreTabla);
            return Repuesta;
        }else if (Mensaje[0].equals("Reinicio")){
            TablaEjemplos.Creartablas();
        }else if (Mensaje[0].equals("EliminarTodo")){
            TablaEjemplos.Creartablas();
        }




        return Repuesta;
    }
}





// Select maximo tres condiciones para la busqueda, usando AND o OR LISTO--
//Innerjoin entre dos XML Store y Select maximo tres condiciones para la busqueda, usando AND o OR LISTO--
//Insert Insertar datos a un XML Store, uno o varios un solo script
//Delete Where Eliminar todas las intancias de un XMLStore
//Delete Eliminar ciertas insntacias de un XMLStore LISTO--
//Delete all Eliminar la carpeta y el XMLStore LISTO--
//update Actualizar una instancia de un XML Store LISTO--
//update actuizasr un conjunto especifico de instancias en un XML Store LISTO--
//Los Updates actualizan todos los datos con el valor que se le da por el otro
