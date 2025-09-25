package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private String nombre;

    @Enumerated(EnumType.STRING)
    private EstadoDocumento estado;

    private String observacion;
    private LocalDate fechaLimite;

    // Constructor for existing JavaFX model compatibility (without ID)
    public Documento(String nombre, EstadoDocumento estado, String observacion, LocalDate fechaLimite) {
        this.nombre = nombre;
        this.estado = estado;
        this.observacion = observacion;
        this.fechaLimite = fechaLimite;
    }

    public Documento(String nombre) {
        this(nombre, EstadoDocumento.PENDIENTE, null, null);
    }
}
