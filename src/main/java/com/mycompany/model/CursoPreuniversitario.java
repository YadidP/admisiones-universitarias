package com.mycompany.model;


import java.util.List;

public class CursoPreuniversitario {
    private int id;
    private String nombre;
    private Carrera carrera;
    private String horario;
    private String docente;
    private double costo;
    private String estadoInscripcion; // E.g., "Inscrito", "Pendiente de Pago"
    private List<Asignatura> materias;

    public CursoPreuniversitario(int id, String nombre, Carrera carrera, String horario, String docente, double costo, String estadoInscripcion, List<Asignatura> materias) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.horario = horario;
        this.docente = docente;
        this.costo = costo;
        this.estadoInscripcion = estadoInscripcion;
        this.materias = materias;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public String getHorario() {
        return horario;
    }

    public String getDocente() {
        return docente;
    }

    public double getCosto() {
        return costo;
    }

    public String getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(String estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public List<Asignatura> getMaterias() {
        return materias;
    }

    @Override
    public String toString() {
        return nombre + " (" + carrera.getNombre() + ")";
    }
}
