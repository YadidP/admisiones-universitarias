package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.CursoPreuniversitario;
import com.mycompany.javafx.backend.service.CursoPreuniversitarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos-preuniversitarios")
public class CursoPreuniversitarioController {

    @Autowired
    private CursoPreuniversitarioService cursoPreuniversitarioService;

    @GetMapping
    public List<CursoPreuniversitario> getAllCursosPreuniversitarios() {
        return cursoPreuniversitarioService.findAllCursosPreuniversitarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoPreuniversitario> getCursoPreuniversitarioById(@PathVariable Long id) {
        return cursoPreuniversitarioService.findCursoPreuniversitarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CursoPreuniversitario> createCursoPreuniversitario(@RequestBody CursoPreuniversitario cursoPreuniversitario) {
        CursoPreuniversitario savedCursoPreuniversitario = cursoPreuniversitarioService.saveCursoPreuniversitario(cursoPreuniversitario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCursoPreuniversitario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoPreuniversitario> updateCursoPreuniversitario(@PathVariable Long id, @RequestBody CursoPreuniversitario cursoPreuniversitario) {
        return cursoPreuniversitarioService.findCursoPreuniversitarioById(id)
                .map(existingCursoPreuniversitario -> {
                    cursoPreuniversitario.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(cursoPreuniversitarioService.saveCursoPreuniversitario(cursoPreuniversitario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCursoPreuniversitario(@PathVariable Long id) {
        if (cursoPreuniversitarioService.findCursoPreuniversitarioById(id).isPresent()) {
            cursoPreuniversitarioService.deleteCursoPreuniversitario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
