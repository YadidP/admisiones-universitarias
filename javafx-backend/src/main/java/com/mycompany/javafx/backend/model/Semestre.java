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
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private int numero;

    @ManyToMany
    @JoinTable(
            name = "semestre_asignatura",
            joinColumns = @JoinColumn(name = "semestre_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private List<Asignatura> asignaturas;

    // Constructor for existing JavaFX model compatibility (without ID)
    public Semestre(int numero, List<Asignatura> asignaturas) {
        this.numero = numero;
        this.asignaturas = asignaturas;
    }
}
