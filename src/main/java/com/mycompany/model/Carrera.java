package com.mycompany.model;

// Esta clase DEBE ser un espejo del JSON que envía el backend.
public class Carrera {
    private int id;
    private String nombre;

    // Constructor sin argumentos - ¡Esto es lo que faltaba para que Jackson funcione!
    public Carrera() {
    }

    // Getters y Setters (necesarios para que Jackson pueda llenar el objeto)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // El método toString() es usado por el ComboBox para mostrar el texto.
    @Override
    public String toString() {
        return nombre;
    }
}