package com.mycompany.services;

import com.mycompany.model.Postulante;

import java.util.ArrayList;
import java.util.List;

public class SeguimientoDocumentosService {

    public List<Postulante> getPostulantesConDocumentosFisicosPendientes() {
        return new ArrayList<>();
    }

    public boolean enviarNotificacion(Postulante postulante) {
        return true;
    }

    public String getDocumentosFaltantes(Postulante postulante) {
        return "";
    }
}