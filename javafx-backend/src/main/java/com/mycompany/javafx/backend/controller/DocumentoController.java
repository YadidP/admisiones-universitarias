package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.Documento;
import com.mycompany.javafx.backend.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping
    public List<Documento> getAllDocumentos() {
        return documentoService.findAllDocumentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> getDocumentoById(@PathVariable Long id) {
        return documentoService.findDocumentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Documento> createDocumento(@RequestBody Documento documento) {
        Documento savedDocumento = documentoService.saveDocumento(documento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDocumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> updateDocumento(@PathVariable Long id, @RequestBody Documento documento) {
        return documentoService.findDocumentoById(id)
                .map(existingDocumento -> {
                    documento.setId(id); // Lombok will now generate this setter
                    return ResponseEntity.ok(documentoService.saveDocumento(documento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long id) {
        if (documentoService.findDocumentoById(id).isPresent()) {
            documentoService.deleteDocumento(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
