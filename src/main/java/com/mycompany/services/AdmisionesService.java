package com.mycompany.services;
import com.mycompany.model.ResultadoExamen;
import java.util.ArrayList;
import java.util.List;
public class AdmisionesService {
    public List<ResultadoExamen> getResultadosFinales() { return new ArrayList<>(); }
    public boolean aprobarListaOficial() { return true; }
    public boolean exportarListaPdf() { return true; }
}