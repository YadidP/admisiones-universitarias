package com.mycompany.services;
import com.mycompany.model.Aula;
import com.mycompany.model.Postulante;
import java.util.ArrayList;
import java.util.List;
public class AsignacionService {
    public List<Postulante> getPostulantesParaAsignar() { return new ArrayList<>(); }
    public List<Aula> getAulasDisponibles() { return new ArrayList<>(); }
    public boolean asignarAula(Postulante p, Aula a) { return true; }
}