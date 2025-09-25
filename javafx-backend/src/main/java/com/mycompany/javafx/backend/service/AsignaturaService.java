package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Asignatura;
import com.mycompany.javafx.backend.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Optional<Asignatura> findAsignaturaById(Long id) {
        return asignaturaRepository.findById(id);
    }

    public Asignatura saveAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public void deleteAsignatura(Long id) {
        asignaturaRepository.deleteById(id);
    }
}
