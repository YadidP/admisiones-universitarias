package com.mycompany.javafx.backend.repository;

import com.mycompany.javafx.backend.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long> {
}
