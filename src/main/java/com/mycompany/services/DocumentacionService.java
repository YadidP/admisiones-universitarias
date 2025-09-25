package com.mycompany.services;

import com.mycompany.model.Documento;
import com.mycompany.model.Postulante;

import java.util.ArrayList;
import java.util.List;

public class DocumentacionService {

    public List<Postulante> getPostulantesConDocumentosPendientes() {
        return new ArrayList<>();
    }

    public List<Documento> getDocumentosPorPostulante(Postulante postulante) {
        return new ArrayList<>();
    }

    public boolean validarDocumento(Postulante postulante, Documento documento) {
        return true;
    }

    public boolean rechazarDocumento(Postulante postulante, Documento documento, String observacion) {
        return true;
    }
}