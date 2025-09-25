package com.mycompany.javafx.backend.dto;

import lombok.Data;

@Data
public class PostulanteRegistrationDTO {
    private String nombres;
    private String apellidos;
    private String ci;
    private String email;
    private Long carreraId;
    private String username;
    private String password;
}