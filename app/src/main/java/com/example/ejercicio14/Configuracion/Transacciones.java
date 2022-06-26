package com.example.ejercicio14.Configuracion;

public class Transacciones {

    public static final String NameDatabase = "PM01DB";
    public static final String tablapersonasFoto = "personasImagen";

        public static final String id = "id";
        public static final String imagenes = "imagenes";
        public static final String nombres = "nombres";
        public static final String descripcion = "descripcion";


        public static final String createTablePersonasFoto = "CREATE TABLE " + tablapersonasFoto +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "imagenes BLOB, nombres TEXT, descripcion TEXT)";

        public static final String dropTablePersonasFoto = "DROP TABLE IF EXIST" + tablapersonasFoto;
}
