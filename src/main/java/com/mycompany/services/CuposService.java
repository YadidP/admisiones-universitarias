package com.mycompany.services;
import com.mycompany.model.Carrera;
import com.mycompany.model.CupoCarrera;
import java.util.ArrayList;
import java.util.List;
public class CuposService {
    public List<CupoCarrera> getCuposPorCarrera() { return new ArrayList<>(); }
    public boolean actualizarCupo(Carrera c, int n) { return true; }
}