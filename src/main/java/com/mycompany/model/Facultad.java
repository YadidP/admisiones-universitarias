package com.mycompany.model;

import java.util.List;

public class Facultad {
    private int id;
    private String nombre;
    private List<Carrera> carreras;

    public Facultad(int id, String nombre, List<Carrera> carreras) {
        this.id = id;
        this.nombre = nombre;
        this.carreras = carreras;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
