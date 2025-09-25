package com.mycompany.model;

import java.time.LocalDateTime;

public class Notificacion {
    private int id;
    private String mensaje;
    private LocalDateTime fecha;
    private boolean leida;

    public Notificacion(int id, String mensaje, LocalDateTime fecha, boolean leida) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }
}
