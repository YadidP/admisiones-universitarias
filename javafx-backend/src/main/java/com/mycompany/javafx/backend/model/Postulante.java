package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "postulante")
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ci;
    
    private String nombres;
    private String apellidos;
    
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public Postulante(String nombres, String apellidos, String ci, String email, Carrera carrera, User user) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
        this.email = email;
        this.carrera = carrera;
        this.user = user;
    }

    public String getNombreCompleto() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }
}