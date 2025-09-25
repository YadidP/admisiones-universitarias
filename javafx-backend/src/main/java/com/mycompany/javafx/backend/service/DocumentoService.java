package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.Documento;
import com.mycompany.javafx.backend.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> findAllDocumentos() {
        return documentoRepository.findAll();
    }

    public Optional<Documento> findDocumentoById(Long id) {
        return documentoRepository.findById(id);
    }

    public Documento saveDocumento(Documento documento) {
        return documentoRepository.save(documento);
    }

    public void deleteDocumento(Long id) {
        documentoRepository.deleteById(id);
    }
}
