package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Facultad;
import com.mycompany.javafx.backend.service.FacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultades")
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    @GetMapping
    public List<Facultad> getAllFacultades() {
        return facultadService.findAllFacultades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facultad> getFacultadById(@PathVariable Long id) {
        return facultadService.findFacultadById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Facultad> createFacultad(@RequestBody Facultad facultad) {
        Facultad savedFacultad = facultadService.saveFacultad(facultad);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFacultad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable Long id, @RequestBody Facultad facultad) {
        return facultadService.findFacultadById(id)
                .map(existingFacultad -> {
                    facultad.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(facultadService.saveFacultad(facultad));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable Long id) {
        if (facultadService.findFacultadById(id).isPresent()) {
            facultadService.deleteFacultad(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
