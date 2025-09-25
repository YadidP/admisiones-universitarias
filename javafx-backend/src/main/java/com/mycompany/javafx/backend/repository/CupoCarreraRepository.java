package com.mycompany.javafx.backend.repository;

import com.mycompany.javafx.backend.model.Carrera;
import com.mycompany.javafx.backend.model.CupoCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupoCarreraRepository extends JpaRepository<CupoCarrera, Long> {
    Optional<CupoCarrera> findByCarrera(Carrera carrera);
}
