package com.mycompany.model;

import java.time.LocalDate;

public class Documento {
    private Long id;
    private String nombre;
    private EstadoDocumento estado;
    private String observacion;
    private LocalDate fechaLimite;

    public Documento(String nombre, EstadoDocumento estado, String observacion, LocalDate fechaLimite) {
        this.id = null;
        this.nombre = nombre;
        this.estado = estado;
        this.observacion = observacion;
        this.fechaLimite = fechaLimite;
    }

    public Documento(String nombre) {
        this(nombre, EstadoDocumento.PENDIENTE, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoDocumento getEstado() {
        return estado;
    }

    public void setEstado(EstadoDocumento estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
