package com.mycompany.javafx.backend.repository;

import com.mycompany.javafx.backend.model.SolicitudCorreccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudCorreccionRepository extends JpaRepository<SolicitudCorreccion, Long> {
}
