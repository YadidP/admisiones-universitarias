package com.mycompany.model;

public class Postulante {
    private Long id;
    private String nombres;
    private String apellidos;
    private String ci;
    private String email;
    private Carrera carrera;
    private User user; // Campo para la relación con User

    // Constructor vacío para Jackson
    public Postulante() {}

    // Constructor para la creación desde el formulario de registro
    public Postulante(String nombres, String apellidos, String ci, String email, Carrera carrera, User user) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
        this.email = email;
        this.carrera = carrera;
        this.user = user;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Carrera getCarrera() { return carrera; }
    public void setCarrera(Carrera carrera) { this.carrera = carrera; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    // Método de conveniencia para mostrar el nombre completo
    public String getNombreCompleto() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }
}