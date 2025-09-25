package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Examen;
import com.mycompany.javafx.backend.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examenes")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @GetMapping
    public List<Examen> getAllExamenes() {
        return examenService.findAllExamenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long id) {
        return examenService.findExamenById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Examen> createExamen(@RequestBody Examen examen) {
        Examen savedExamen = examenService.saveExamen(examen);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExamen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Examen> updateExamen(@PathVariable Long id, @RequestBody Examen examen) {
        return examenService.findExamenById(id)
                .map(existingExamen -> {
                    examen.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(examenService.saveExamen(examen));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamen(@PathVariable Long id) {
        if (examenService.findExamenById(id).isPresent()) {
            examenService.deleteExamen(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
