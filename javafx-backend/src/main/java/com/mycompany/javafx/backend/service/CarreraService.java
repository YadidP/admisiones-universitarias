package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Carrera;
import com.mycompany.javafx.backend.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public List<Carrera> findAllCarreras() {
        return carreraRepository.findAll();
    }

    public Optional<Carrera> findCarreraById(Long id) {
        return carreraRepository.findById(id);
    }

    public Carrera saveCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public void deleteCarrera(Long id) {
        carreraRepository.deleteById(id);
    }
}
