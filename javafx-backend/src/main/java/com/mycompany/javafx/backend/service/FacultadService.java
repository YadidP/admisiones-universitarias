package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Facultad;
import com.mycompany.javafx.backend.repository.FacultadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    public List<Facultad> findAllFacultades() {
        return facultadRepository.findAll();
    }

    public Optional<Facultad> findFacultadById(Long id) {
        return facultadRepository.findById(id);
    }

    public Facultad saveFacultad(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    public void deleteFacultad(Long id) {
        facultadRepository.deleteById(id);
    }
}
