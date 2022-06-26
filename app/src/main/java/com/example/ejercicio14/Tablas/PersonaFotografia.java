package com.example.ejercicio14.Tablas;

public class PersonaFotografia {
    private int id;
    private String imagen;
    private String Nombres;
    private String Descripcion;

    public PersonaFotografia() {
    }

    public PersonaFotografia(int id, String imagen, String nombres, String descripcion) {
        this.id = id;
        this.imagen = imagen;
        this.Nombres = nombres;
        this.Descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
