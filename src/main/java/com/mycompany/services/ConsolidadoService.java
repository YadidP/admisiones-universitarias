package com.mycompany.services;
import com.mycompany.model.Carrera;
import com.mycompany.model.Facultad;
import com.mycompany.model.Postulante;
import java.util.ArrayList;
import java.util.List;
public class ConsolidadoService {
    public List<Facultad> getConsolidadoInscripciones() { return new ArrayList<>(); }
    public List<Postulante> getPostulantesPorCarrera(Carrera c) { return new ArrayList<>(); }
}