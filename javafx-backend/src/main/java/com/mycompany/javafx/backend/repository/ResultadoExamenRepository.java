package com.mycompany.javafx.backend.repository;

import com.mycompany.javafx.backend.model.Postulante;
import com.mycompany.javafx.backend.model.ResultadoExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultadoExamenRepository extends JpaRepository<ResultadoExamen, Long> {
    Optional<ResultadoExamen> findByPostulante(Postulante postulante);
}
