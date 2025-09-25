package com.mycompany.services;

import com.mycompany.model.CursoPreuniversitario;
import com.mycompany.model.User;

import java.util.ArrayList;
import java.util.List;


public class CursosPreService {

    public List<CursoPreuniversitario> getCursosDisponibles() {
        return new ArrayList<>();
    }

    public boolean inscribirPostulante(User user, CursoPreuniversitario curso) {
        return true;
    }

    public CursoPreuniversitario getCursoActual(User user) {
        return null;
    }
}