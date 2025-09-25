package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Carrera;
import com.mycompany.javafx.backend.model.CupoCarrera;
import com.mycompany.javafx.backend.repository.CupoCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CupoCarreraService {

    @Autowired
    private CupoCarreraRepository cupoCarreraRepository;

    public List<CupoCarrera> findAllCuposCarrera() {
        return cupoCarreraRepository.findAll();
    }

    public Optional<CupoCarrera> findCupoCarreraById(Long id) {
        return cupoCarreraRepository.findById(id);
    }

    public Optional<CupoCarrera> findCupoCarreraByCarrera(Carrera carrera) {
        return cupoCarreraRepository.findByCarrera(carrera);
    }

    public CupoCarrera saveCupoCarrera(CupoCarrera cupoCarrera) {
        return cupoCarreraRepository.save(cupoCarrera);
    }

    public void deleteCupoCarrera(Long id) {
        cupoCarreraRepository.deleteById(id);
    }
}
