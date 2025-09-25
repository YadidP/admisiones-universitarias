package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Notificacion;
import com.mycompany.javafx.backend.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> getAllNotificaciones() {
        return notificacionService.findAllNotificaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        return notificacionService.findNotificacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion savedNotificacion = notificacionService.saveNotificacion(notificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> updateNotificacion(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        return notificacionService.findNotificacionById(id)
                .map(existingNotificacion -> {
                    notificacion.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(notificacionService.saveNotificacion(notificacion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        if (notificacionService.findNotificacionById(id).isPresent()) {
            notificacionService.deleteNotificacion(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
