package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Postulante;
import com.mycompany.javafx.backend.model.ResultadoExamen;
import com.mycompany.javafx.backend.repository.ResultadoExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoExamenService {

    @Autowired
    private ResultadoExamenRepository resultadoExamenRepository;

    public List<ResultadoExamen> findAllResultadosExamen() {
        return resultadoExamenRepository.findAll();
    }

    public Optional<ResultadoExamen> findResultadoExamenById(Long id) {
        return resultadoExamenRepository.findById(id);
    }

    public Optional<ResultadoExamen> findResultadoExamenByPostulante(Postulante postulante) {
        return resultadoExamenRepository.findByPostulante(postulante);
    }

    public ResultadoExamen saveResultadoExamen(ResultadoExamen resultadoExamen) {
        return resultadoExamenRepository.save(resultadoExamen);
    }

    public void deleteResultadoExamen(Long id) {
        resultadoExamenRepository.deleteById(id);
    }
}
