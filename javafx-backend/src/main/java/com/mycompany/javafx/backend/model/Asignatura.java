package com.mycompany.javafx.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private String codigo;
    private String nombre;
    private boolean esElectiva;

    // Constructor for existing JavaFX model compatibility (without ID)
    public Asignatura(String codigo, String nombre, boolean esElectiva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.esElectiva = esElectiva;
    }
}
