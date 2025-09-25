package com.mycompany.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Examen {
    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    private String lugar;
    private String modalidad; // Ej: Presencial, En línea
    private Carrera carrera;

    public Examen(int id, LocalDate fecha, LocalTime hora, String lugar, String modalidad, Carrera carrera) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.modalidad = modalidad;
        this.carrera = carrera;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getLugar() {
        return lugar;
    }

    public String getModalidad() {
        return modalidad;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}