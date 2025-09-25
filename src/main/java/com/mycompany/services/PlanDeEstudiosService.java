package com.mycompany.services;

import com.mycompany.model.Carrera;
import com.mycompany.model.Facultad;
import com.mycompany.model.Semestre;

import java.util.ArrayList;
import java.util.List;

public class PlanDeEstudiosService {

    public PlanDeEstudiosService() {
    }

    public List<Facultad> getFacultades() {
        return new ArrayList<>();
    }

    public List<Carrera> getCarrerasPorFacultad(Facultad facultad) {
        return new ArrayList<>();
    }

    public List<Semestre> getPlanDeEstudio(Carrera carrera) {
        return new ArrayList<>();
    }

    public int getTotalAsignaturas(Carrera carrera) {
        return 0;
    }

    public List<String> getModalidadesTitulacion(Carrera carrera) {
        return new ArrayList<>();
    }
}