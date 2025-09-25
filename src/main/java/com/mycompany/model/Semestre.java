package com.mycompany.model;

import java.util.List;

public class Semestre {
    private int numero;
    private List<Asignatura> asignaturas;

    public Semestre(int numero, List<Asignatura> asignaturas) {
        this.numero = numero;
        this.asignaturas = asignaturas;
    }

    public int getNumero() {
        return numero;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }
}
