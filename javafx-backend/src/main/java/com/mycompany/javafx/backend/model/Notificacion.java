package com.mycompany.javafx.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private String mensaje;
    private LocalDateTime fecha;
    private boolean leida;

    // Constructor for existing JavaFX model compatibility (without ID)
    public Notificacion(int id, String mensaje, LocalDateTime fecha, boolean leida) {
        this.id = (long) id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
    }
}
