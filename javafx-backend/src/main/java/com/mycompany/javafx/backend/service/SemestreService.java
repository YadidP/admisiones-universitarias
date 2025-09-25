package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Semestre;
import com.mycompany.javafx.backend.repository.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    public List<Semestre> findAllSemestres() {
        return semestreRepository.findAll();
    }

    public Optional<Semestre> findSemestreById(Long id) {
        return semestreRepository.findById(id);
    }

    public Semestre saveSemestre(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    public void deleteSemestre(Long id) {
        semestreRepository.deleteById(id);
    }
}
