package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Semestre;
import com.mycompany.javafx.backend.service.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semestres")
public class SemestreController {

    @Autowired
    private SemestreService semestreService;

    @GetMapping
    public List<Semestre> getAllSemestres() {
        return semestreService.findAllSemestres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semestre> getSemestreById(@PathVariable Long id) {
        return semestreService.findSemestreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Semestre> createSemestre(@RequestBody Semestre semestre) {
        Semestre savedSemestre = semestreService.saveSemestre(semestre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSemestre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semestre> updateSemestre(@PathVariable Long id, @RequestBody Semestre semestre) {
        return semestreService.findSemestreById(id)
                .map(existingSemestre -> {
                    semestre.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(semestreService.saveSemestre(semestre));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemestre(@PathVariable Long id) {
        if (semestreService.findSemestreById(id).isPresent()) {
            semestreService.deleteSemestre(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
