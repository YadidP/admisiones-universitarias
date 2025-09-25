package com.mycompany.model;

public class CupoCarrera {
    private Carrera carrera;
    private int cantidadCupos;

    public CupoCarrera(Carrera carrera, int cantidadCupos) {
        this.carrera = carrera;
        this.cantidadCupos = cantidadCupos;
    }

    // Getters and Setters
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getCantidadCupos() {
        return cantidadCupos;
    }

    public void setCantidadCupos(int cantidadCupos) {
        this.cantidadCupos = cantidadCupos;
    }
}
