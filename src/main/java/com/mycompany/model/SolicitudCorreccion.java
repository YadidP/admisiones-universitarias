package com.mycompany.model;

import java.time.LocalDateTime;

public class SolicitudCorreccion {
    private Postulante postulante;
    private String campoAfectado;
    private String valorAnterior;
    private String valorNuevo;
    private String motivo;
    private LocalDateTime fechaSolicitud;
    private EstadoSolicitud estado;

    public SolicitudCorreccion(Postulante postulante, String campoAfectado, String valorAnterior, String valorNuevo, String motivo) {
        this.postulante = postulante;
        this.campoAfectado = campoAfectado;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.motivo = motivo;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    // Getters and Setters
    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public String getCampoAfectado() {
        return campoAfectado;
    }

    public void setCampoAfectado(String campoAfectado) {
        this.campoAfectado = campoAfectado;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }
}
