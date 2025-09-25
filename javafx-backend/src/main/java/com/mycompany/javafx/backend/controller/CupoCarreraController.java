package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Carrera;
import com.mycompany.javafx.backend.model.CupoCarrera;
import com.mycompany.javafx.backend.service.CupoCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupos-carrera")
public class CupoCarreraController {

    @Autowired
    private CupoCarreraService cupoCarreraService;

    @GetMapping
    public List<CupoCarrera> getAllCuposCarrera() {
        return cupoCarreraService.findAllCuposCarrera();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupoCarrera> getCupoCarreraById(@PathVariable Long id) {
        return cupoCarreraService.findCupoCarreraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CupoCarrera> createCupoCarrera(@RequestBody CupoCarrera cupoCarrera) {
        CupoCarrera savedCupoCarrera = cupoCarreraService.saveCupoCarrera(cupoCarrera);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCupoCarrera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CupoCarrera> updateCupoCarrera(@PathVariable Long id, @RequestBody CupoCarrera cupoCarrera) {
        return cupoCarreraService.findCupoCarreraById(id)
                .map(existingCupoCarrera -> {
                    cupoCarrera.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(cupoCarreraService.saveCupoCarrera(cupoCarrera));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCupoCarrera(@PathVariable Long id) {
        if (cupoCarreraService.findCupoCarreraById(id).isPresent()) {
            cupoCarreraService.deleteCupoCarrera(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
