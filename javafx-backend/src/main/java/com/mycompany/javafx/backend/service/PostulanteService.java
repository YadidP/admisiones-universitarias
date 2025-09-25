package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.dto.PostulanteRegistrationDTO;
import com.mycompany.javafx.backend.model.Carrera;
import com.mycompany.javafx.backend.model.Postulante;
import com.mycompany.javafx.backend.model.Role;
import com.mycompany.javafx.backend.model.User;
import com.mycompany.javafx.backend.repository.CarreraRepository;
import com.mycompany.javafx.backend.repository.PostulanteRepository;
import com.mycompany.javafx.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostulanteService {

    @Autowired
    private PostulanteRepository postulanteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Postulante> findAllPostulantes() {
        return postulanteRepository.findAll();
    }

    public Optional<Postulante> findPostulanteById(Long id) {
        return postulanteRepository.findById(id);
    }

    public Optional<Postulante> findPostulanteByCi(String ci) {
        return postulanteRepository.findByCi(ci);
    }

    public Postulante savePostulante(Postulante postulante) {
        return postulanteRepository.save(postulante);
    }

    public Postulante registerPostulante(PostulanteRegistrationDTO registrationDTO) {
        // 1. Create and save User
        User newUser = new User();
        newUser.setUsername(registrationDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        newUser.setFullName(registrationDTO.getNombres() + " " + registrationDTO.getApellidos());
        newUser.setRole(Role.POSTULANTE); // Default role for new registrations
        User savedUser = userRepository.save(newUser);

        // 2. Find Carrera
        Carrera carrera = carreraRepository.findById(registrationDTO.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera not found with ID: " + registrationDTO.getCarreraId()));

        // 3. Create and save Postulante
        Postulante newPostulante = new Postulante();
        newPostulante.setNombres(registrationDTO.getNombres());
        newPostulante.setApellidos(registrationDTO.getApellidos());
        newPostulante.setCi(registrationDTO.getCi());
        newPostulante.setEmail(registrationDTO.getEmail());
        newPostulante.setCarrera(carrera);
        newPostulante.setUser(savedUser); // Link Postulante to User

        return postulanteRepository.save(newPostulante);
    }

    public void deletePostulante(Long id) {
        postulanteRepository.deleteById(id);
    }
}