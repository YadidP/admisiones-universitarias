package com.mycompany.model;

public class Asignatura {
    private String codigo;
    private String nombre;
    private boolean esElectiva;

    public Asignatura(String codigo, String nombre, boolean esElectiva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.esElectiva = esElectiva;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEsElectiva() {
        return esElectiva;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}
