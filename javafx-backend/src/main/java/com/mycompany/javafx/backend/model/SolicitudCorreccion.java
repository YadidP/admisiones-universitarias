package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCorreccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    @ManyToOne
    @JoinColumn(name = "postulante_id")
    private Postulante postulante;

    private String campoAfectado;
    private String valorAnterior;
    private String valorNuevo;
    private String motivo;
    private LocalDateTime fechaSolicitud;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    // Constructor for existing JavaFX model compatibility (without ID)
    public SolicitudCorreccion(Postulante postulante, String campoAfectado, String valorAnterior, String valorNuevo, String motivo) {
        this.postulante = postulante;
        this.campoAfectado = campoAfectado;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.motivo = motivo;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = EstadoSolicitud.PENDIENTE;
    }
}
