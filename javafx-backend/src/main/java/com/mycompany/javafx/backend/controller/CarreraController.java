package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Carrera;
import com.mycompany.javafx.backend.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraService.findAllCarreras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id) {
        return carreraService.findCarreraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carrera> createCarrera(@RequestBody Carrera carrera) {
        Carrera savedCarrera = carreraService.saveCarrera(carrera);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarrera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable Long id, @RequestBody Carrera carrera) {
        return carreraService.findCarreraById(id)
                .map(existingCarrera -> {
                    carrera.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(carreraService.saveCarrera(carrera));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
        if (carreraService.findCarreraById(id).isPresent()) {
            carreraService.deleteCarrera(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
