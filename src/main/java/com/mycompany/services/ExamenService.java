package com.mycompany.services;

import com.mycompany.model.Examen;
import java.util.ArrayList;
import java.util.List;

public class ExamenService {
    public List<Examen> getExamenesProgramados() {
        return new ArrayList<>();
    }

    public boolean programarExamen(Examen examen) {
        return true;
    }

    public boolean editarExamen(Examen updatedExamen) {
        return true;
    }

    public boolean eliminarExamen(Examen examen) {
        return true;
    }
}