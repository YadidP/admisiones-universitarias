package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Examen;
import com.mycompany.javafx.backend.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    public List<Examen> findAllExamenes() {
        return examenRepository.findAll();
    }

    public Optional<Examen> findExamenById(Long id) {
        return examenRepository.findById(id);
    }

    public Examen saveExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    public void deleteExamen(Long id) {
        examenRepository.deleteById(id);
    }
}
