package com.mycompany.javafx.backend.repository;

import com.mycompany.javafx.backend.model.ComprobantePago;
import com.mycompany.javafx.backend.model.EstadoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComprobantePagoRepository extends JpaRepository<ComprobantePago, String> {
    Optional<ComprobantePago> findByCodigoTransaccion(String codigoTransaccion);
    List<ComprobantePago> findByEstado(EstadoPago estado);
}
