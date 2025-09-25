package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.CursoPreuniversitario;
import com.mycompany.javafx.backend.repository.CursoPreuniversitarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoPreuniversitarioService {

    @Autowired
    private CursoPreuniversitarioRepository cursoPreuniversitarioRepository;

    public List<CursoPreuniversitario> findAllCursosPreuniversitarios() {
        return cursoPreuniversitarioRepository.findAll();
    }

    public Optional<CursoPreuniversitario> findCursoPreuniversitarioById(Long id) {
        return cursoPreuniversitarioRepository.findById(id);
    }

    public CursoPreuniversitario saveCursoPreuniversitario(CursoPreuniversitario cursoPreuniversitario) {
        return cursoPreuniversitarioRepository.save(cursoPreuniversitario);
    }

    public void deleteCursoPreuniversitario(Long id) {
        cursoPreuniversitarioRepository.deleteById(id);
    }
}
