package com.mycompany.javafx.backend.repository;

import com.mycompany.javafx.backend.model.CursoPreuniversitario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoPreuniversitarioRepository extends JpaRepository<CursoPreuniversitario, Long> {
}
