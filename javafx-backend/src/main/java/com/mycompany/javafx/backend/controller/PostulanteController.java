package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.dto.PostulanteRegistrationDTO;
import com.mycompany.javafx.backend.model.Postulante;
import com.mycompany.javafx.backend.service.PostulanteService;
import com.mycompany.javafx.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/postulantes")
@CrossOrigin(origins = "*")
public class PostulanteController {

    @Autowired
    private PostulanteService postulanteService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Postulante> getAllPostulantes() {
        return postulanteService.findAllPostulantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postulante> getPostulanteById(@PathVariable Long id) {
        return postulanteService.findPostulanteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ci/{ci}")
    public ResponseEntity<Postulante> getPostulanteByCi(@PathVariable String ci) {
        return postulanteService.findPostulanteByCi(ci)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPostulante(@RequestBody PostulanteRegistrationDTO dto) {
        if (userService.findUserByUsername(dto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "El nombre de usuario ya existe"));
        }

        if (postulanteService.findPostulanteByCi(dto.getCi()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "El CI ya está registrado"));
        }

        try {
            Postulante savedPostulante = postulanteService.registerPostulante(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPostulante);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}