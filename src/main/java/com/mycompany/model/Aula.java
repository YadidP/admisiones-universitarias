package com.mycompany.model;

public class Aula {
    private int id;
    private String nombre;
    private int capacidad;

    public Aula(int id, String nombre, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return nombre + " (Cap: " + capacidad + ")";
    }
}
