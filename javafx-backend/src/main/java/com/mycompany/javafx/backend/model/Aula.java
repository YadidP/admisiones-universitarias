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
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private String nombre;
    private int capacidad;

    // Constructor for existing JavaFX model compatibility (without ID)
    public Aula(int id, String nombre, int capacidad) {
        this.id = (long) id; // Convert int to Long
        this.nombre = nombre;
        this.capacidad = capacidad;
    }
}
