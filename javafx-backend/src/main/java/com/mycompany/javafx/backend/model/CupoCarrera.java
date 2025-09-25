package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CupoCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    @OneToOne
    @JoinColumn(name = "carrera_id", unique = true) // Each Carrera has one CupoCarrera
    private Carrera carrera;

    private int cantidadCupos;

    // Constructor for existing JavaFX model compatibility (without ID)
    public CupoCarrera(Carrera carrera, int cantidadCupos) {
        this.carrera = carrera;
        this.cantidadCupos = cantidadCupos;
    }
}
