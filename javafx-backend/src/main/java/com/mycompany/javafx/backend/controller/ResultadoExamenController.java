package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Postulante;
import com.mycompany.javafx.backend.model.ResultadoExamen;
import com.mycompany.javafx.backend.service.PostulanteService; // Import PostulanteService
import com.mycompany.javafx.backend.service.ResultadoExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultados-examen")
public class ResultadoExamenController {

    @Autowired
    private ResultadoExamenService resultadoExamenService;

    @Autowired
    private PostulanteService postulanteService; // Inject PostulanteService

    @GetMapping
    public List<ResultadoExamen> getAllResultadosExamen() {
        return resultadoExamenService.findAllResultadosExamen();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoExamen> getResultadoExamenById(@PathVariable Long id) {
        return resultadoExamenService.findResultadoExamenById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/postulante/{postulanteId}")
    public ResponseEntity<ResultadoExamen> getResultadoExamenByPostulanteId(@PathVariable Long postulanteId) {
        Optional<Postulante> postulanteOptional = postulanteService.findPostulanteById(postulanteId);
        if (postulanteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Postulante postulante = postulanteOptional.get();
        return resultadoExamenService.findResultadoExamenByPostulante(postulante)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResultadoExamen> createResultadoExamen(@RequestBody ResultadoExamen resultadoExamen) {
        ResultadoExamen savedResultadoExamen = resultadoExamenService.saveResultadoExamen(resultadoExamen);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedResultadoExamen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultadoExamen> updateResultadoExamen(@PathVariable Long id, @RequestBody ResultadoExamen resultadoExamen) {
        return resultadoExamenService.findResultadoExamenById(id)
                .map(existingResultadoExamen -> {
                    resultadoExamen.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(resultadoExamenService.saveResultadoExamen(resultadoExamen));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultadoExamen(@PathVariable Long id) {
        if (resultadoExamenService.findResultadoExamenById(id).isPresent()) {
            resultadoExamenService.deleteResultadoExamen(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
