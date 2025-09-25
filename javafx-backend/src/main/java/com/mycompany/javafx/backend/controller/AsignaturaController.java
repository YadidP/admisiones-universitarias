package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Asignatura;
import com.mycompany.javafx.backend.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaService.findAllAsignaturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Long id) {
        return asignaturaService.findAsignaturaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura savedAsignatura = asignaturaService.saveAsignatura(asignatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAsignatura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Long id, @RequestBody Asignatura asignatura) {
        return asignaturaService.findAsignaturaById(id)
                .map(existingAsignatura -> {
                    asignatura.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(asignaturaService.saveAsignatura(asignatura));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id) {
        if (asignaturaService.findAsignaturaById(id).isPresent()) {
            asignaturaService.deleteAsignatura(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
