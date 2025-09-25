package com.mycompany.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.model.Carrera;
import com.mycompany.model.Postulante;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostulanteService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String baseUrl = "http://localhost:8080/api";

    public boolean registrar(Postulante postulante, String password) {
        try {
            // Generar username desde el email
            String username = postulante.getEmail().split("@")[0];
            
            Map<String, Object> dto = new HashMap<>();
            dto.put("ci", postulante.getCi());
            dto.put("nombres", postulante.getNombres());
            dto.put("apellidos", postulante.getApellidos());
            dto.put("email", postulante.getEmail());
            dto.put("carreraId", postulante.getCarrera().getId());
            dto.put("username", username);
            dto.put("password", password);
            
            String body = mapper.writeValueAsString(dto);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/postulantes/register"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Carrera> getCarrerasDisponibles() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/carreras"))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), new TypeReference<List<Carrera>>() {});
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Postulante getPostulanteByCi(String ci) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/postulantes/ci/" + ci))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), Postulante.class);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getEnrolledCount(Carrera carrera) {
        // Simulación por ahora
        return 0;
    }
}