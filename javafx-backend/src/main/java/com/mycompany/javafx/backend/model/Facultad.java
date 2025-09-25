package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "facultad_id") // This creates a foreign key in the Carrera table
    private List<Carrera> carreras;

    // Constructor for existing JavaFX model compatibility (without ID)
    public Facultad(int id, String nombre, List<Carrera> carreras) {
        this.id = (long) id;
        this.nombre = nombre;
        this.carreras = carreras;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
