package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.SolicitudCorreccion;
import com.mycompany.javafx.backend.repository.SolicitudCorreccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudCorreccionService {

    @Autowired
    private SolicitudCorreccionRepository solicitudCorreccionRepository;

    public List<SolicitudCorreccion> findAllSolicitudesCorreccion() {
        return solicitudCorreccionRepository.findAll();
    }

    public Optional<SolicitudCorreccion> findSolicitudCorreccionById(Long id) {
        return solicitudCorreccionRepository.findById(id);
    }

    public SolicitudCorreccion saveSolicitudCorreccion(SolicitudCorreccion solicitudCorreccion) {
        return solicitudCorreccionRepository.save(solicitudCorreccion);
    }

    public void deleteSolicitudCorreccion(Long id) {
        solicitudCorreccionRepository.deleteById(id);
    }
}
