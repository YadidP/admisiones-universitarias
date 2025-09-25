package com.mycompany.javafx.backend.config;

import com.mycompany.javafx.backend.model.Carrera;
import com.mycompany.javafx.backend.model.Role;
import com.mycompany.javafx.backend.model.User;
import com.mycompany.javafx.backend.repository.CarreraRepository;
import com.mycompany.javafx.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CarreraRepository carreraRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DataSeeder(CarreraRepository carreraRepository, UserRepository userRepository) {
        this.carreraRepository = carreraRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (carreraRepository.count() == 0) {
            carreraRepository.save(new Carrera("Ingeniería de Sistemas"));
            carreraRepository.save(new Carrera("Medicina"));
            carreraRepository.save(new Carrera("Derecho"));
            carreraRepository.save(new Carrera("Arquitectura"));
            System.out.println("Carreras seed creadas.");
        } else {
            System.out.println("La base de datos de carreras ya contiene datos.");
        }

        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", "Admin User", Role.ADMIN, passwordEncoder.encode("admin123")));
            userRepository.save(new User("postulante", "Postulante Test", Role.POSTULANTE, passwordEncoder.encode("test123")));
            System.out.println("Usuarios seed creados con passwords hashed.");
        } else {
            System.out.println("La base de datos de users ya contiene datos.");
        }
    }
}