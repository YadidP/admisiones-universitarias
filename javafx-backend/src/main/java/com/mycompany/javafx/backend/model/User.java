package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "app_users") // Cambiar nombre de tabla para evitar conflictos
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING) // Importante: guardar como STRING, no como número
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String password;

    public User(String username, String fullName, Role role, String password) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.password = password;
    }
}