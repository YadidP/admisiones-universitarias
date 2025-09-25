package com.mycompany.model;

import java.time.LocalDateTime;

public class ComprobantePago {
    private String codigoTransaccion;
    private Postulante postulante;
    private LocalDateTime fechaPago;
    private double monto;
    private EstadoPago estado;

    public ComprobantePago(String codigoTransaccion, Postulante postulante, LocalDateTime fechaPago, double monto, EstadoPago estado) {
        this.codigoTransaccion = codigoTransaccion;
        this.postulante = postulante;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.estado = estado;
    }

    // Getters and Setters
    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }
}
