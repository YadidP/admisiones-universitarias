package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Notificacion;
import com.mycompany.javafx.backend.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> findAllNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> findNotificacionById(Long id) {
        return notificacionRepository.findById(id);
    }

    public Notificacion saveNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void deleteNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }
}
