package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Aula;
import com.mycompany.javafx.backend.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public List<Aula> findAllAulas() {
        return aulaRepository.findAll();
    }

    public Optional<Aula> findAulaById(Long id) {
        return aulaRepository.findById(id);
    }

    public Aula saveAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    public void deleteAula(Long id) {
        aulaRepository.deleteById(id);
    }
}
