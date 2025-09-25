package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.SolicitudCorreccion;
import com.mycompany.javafx.backend.service.SolicitudCorreccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes-correccion")
public class SolicitudCorreccionController {

    @Autowired
    private SolicitudCorreccionService solicitudCorreccionService;

    @GetMapping
    public List<SolicitudCorreccion> getAllSolicitudesCorreccion() {
        return solicitudCorreccionService.findAllSolicitudesCorreccion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudCorreccion> getSolicitudCorreccionById(@PathVariable Long id) {
        return solicitudCorreccionService.findSolicitudCorreccionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SolicitudCorreccion> createSolicitudCorreccion(@RequestBody SolicitudCorreccion solicitudCorreccion) {
        SolicitudCorreccion savedSolicitudCorreccion = solicitudCorreccionService.saveSolicitudCorreccion(solicitudCorreccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSolicitudCorreccion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudCorreccion> updateSolicitudCorreccion(@PathVariable Long id, @RequestBody SolicitudCorreccion solicitudCorreccion) {
        return solicitudCorreccionService.findSolicitudCorreccionById(id)
                .map(existingSolicitudCorreccion -> {
                    solicitudCorreccion.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(solicitudCorreccionService.saveSolicitudCorreccion(solicitudCorreccion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitudCorreccion(@PathVariable Long id) {
        if (solicitudCorreccionService.findSolicitudCorreccionById(id).isPresent()) {
            solicitudCorreccionService.deleteSolicitudCorreccion(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
