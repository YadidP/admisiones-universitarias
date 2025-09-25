package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Aula;
import com.mycompany.javafx.backend.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping
    public List<Aula> getAllAulas() {
        return aulaService.findAllAulas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> getAulaById(@PathVariable Long id) {
        return aulaService.findAulaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aula> createAula(@RequestBody Aula aula) {
        Aula savedAula = aulaService.saveAula(aula);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> updateAula(@PathVariable Long id, @RequestBody Aula aula) {
        return aulaService.findAulaById(id)
                .map(existingAula -> {
                    aula.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(aulaService.saveAula(aula));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
        if (aulaService.findAulaById(id).isPresent()) {
            aulaService.deleteAula(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
