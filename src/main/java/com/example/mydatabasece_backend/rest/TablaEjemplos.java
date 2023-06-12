package com.example.mydatabasece_backend.rest;

public class TablaEjemplos {
    static String[][] Datos =
            {{"ID","Nombre","Edad","Carrera","Curso"},
            {"3322","Carlos","24","Computadores","Datos"},
            {"1024","Felipe","20","Industrial","Calculo"}
            ,{"3052","Juan","22","Electronica","Fisica"},
            {"2060","Jose","25","Computadores","Calculo"}};

    static String[][] Carros = {
            {"ID", "Marca", "Modelo", "Año", "Color", "Precio"},
            {"1", "Toyota", "Corolla", "2018", "Rojo", null},
            {"2", "Honda", "Civic", "2019", "Negro", "18000"},
            {"3", "Ford", "Focus", "2017", null, "12000"},
            {"4", "Chevrolet", "Cruze", "2020", null, "16000"},
            {"5", "Nissan", null, "2016", "Azul", "13000"}};

    static String[][] Clientes =
            {{"ID","Nombre","Membresia"},
            {"2029","Kevin","Oro"},
            {"9921","Charly","Platino"}
            ,{"4021127","Matias","Plata"},
            {"312783789","Alexis","Cobre"}};

    static String[][] DatosPedido =
            {{"IDp","Dia","Mes","Año","Direccion"},
            {"1","21","Enero","2020","null"},
            {"2","14","Febrero","2023","null"}
            ,{"3","31","22","2022","null"},
            {"4","22","25","2001","null"}};

    static String[][] NotaCursos=
            {
                    {"Nota","Estado","Profesor"},
                    {"80","Aprobada","Marcos"},
                    {"50","Reprobada","Diego"}
                    ,{null,"Pendiente","Luis"},
                    {null,"Incompleta","Gerardo"}};
    static String[][] plantilla =
            {{"","","","",""},
                    {"","","","",""},
                    {"","","","",""}
                    ,{"","","","",""},
                    {"","","","",""}};


public static void main(String[] args){

}
public static void Creartablas(){
    XMLWriter.writeXML(Datos,"Estudiantes");
    XMLWriter.writeXML(Carros,"Carros");
    XMLWriter.writeXML(Clientes,"Clientes");
    XMLWriter.writeXML(DatosPedido,"DatosPedidos");
    XMLWriter.writeXML(NotaCursos,"NotaCursos");
}
}
