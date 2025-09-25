package com.mycompany.model;

public class ResultadoExamen {
    private EstadoAdmision estado;
    private String carrera;
    private double puntajeObtenido;
    private double puntajeMinimo;
    private Postulante postulante;

    public ResultadoExamen(EstadoAdmision estado, String carrera, double puntajeObtenido, double puntajeMinimo, Postulante postulante) {
        this.estado = estado;
        this.carrera = carrera;
        this.puntajeObtenido = puntajeObtenido;
        this.puntajeMinimo = puntajeMinimo;
        this.postulante = postulante;
    }

    // Getters and Setters
    public EstadoAdmision getEstado() {
        return estado;
    }

    public void setEstado(EstadoAdmision estado) {
        this.estado = estado;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public double getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(double puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    public double getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(double puntajeMinimo) {
        this.puntajeMinimo = puntajeMinimo;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }
}