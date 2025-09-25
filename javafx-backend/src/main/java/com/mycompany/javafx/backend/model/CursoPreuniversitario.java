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
public class CursoPreuniversitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for JPA

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    private String horario;
    private String docente;
    private double costo;
    private String estadoInscripcion; // E.g., "Inscrito", "Pendiente de Pago"

    @ManyToMany
    @JoinTable(
            name = "curso_asignatura",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private List<Asignatura> materias;

    // Constructor for existing JavaFX model compatibility (without ID)
    public CursoPreuniversitario(int id, String nombre, Carrera carrera, String horario, String docente, double costo, String estadoInscripcion, List<Asignatura> materias) {
        this.id = (long) id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.horario = horario;
        this.docente = docente;
        this.costo = costo;
        this.estadoInscripcion = estadoInscripcion;
        this.materias = materias;
    }
}
