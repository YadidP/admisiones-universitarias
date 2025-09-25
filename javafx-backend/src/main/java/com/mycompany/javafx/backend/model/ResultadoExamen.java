package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    @Enumerated(EnumType.STRING)
    private EstadoAdmision estado;

    private String carreraNombre; // Store carrera name directly or link to Carrera entity
    private double puntajeObtenido;
    private double puntajeMinimo;

    @OneToOne
    @JoinColumn(name = "postulante_id", unique = true) // Each result belongs to one postulante
    private Postulante postulante;

    // Constructor for existing JavaFX model compatibility (without ID)
    public ResultadoExamen(EstadoAdmision estado, String carrera, double puntajeObtenido, double puntajeMinimo, Postulante postulante) {
        this.estado = estado;
        this.carreraNombre = carrera;
        this.puntajeObtenido = puntajeObtenido;
        this.puntajeMinimo = puntajeMinimo;
        this.postulante = postulante;
    }
}
