package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private LocalDate fecha;
    private LocalTime hora;
    private String lugar;
    private String modalidad; // Ej: Presencial, En línea

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    // Constructor for existing JavaFX model compatibility (without ID)
    public Examen(int id, LocalDate fecha, LocalTime hora, String lugar, String modalidad, Carrera carrera) {
        this.id = (long) id;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.modalidad = modalidad;
        this.carrera = carrera;
    }
}
