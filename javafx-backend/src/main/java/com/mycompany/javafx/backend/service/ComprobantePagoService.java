package com.mycompany.javafx.backend.service;

import com.mycompany.javafx.backend.model.ComprobantePago;
import com.mycompany.javafx.backend.model.EstadoPago;
import com.mycompany.javafx.backend.repository.ComprobantePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprobantePagoService {

    @Autowired
    private ComprobantePagoRepository comprobantePagoRepository;

    public List<ComprobantePago> findAllComprobantesPago() {
        return comprobantePagoRepository.findAll();
    }

    public Optional<ComprobantePago> findComprobantePagoByCodigoTransaccion(String codigoTransaccion) {
        return comprobantePagoRepository.findByCodigoTransaccion(codigoTransaccion);
    }

    public List<ComprobantePago> findComprobantesPagoByEstado(EstadoPago estado) {
        return comprobantePagoRepository.findByEstado(estado);
    }

    public ComprobantePago saveComprobantePago(ComprobantePago comprobantePago) {
        return comprobantePagoRepository.save(comprobantePago);
    }

    public void deleteComprobantePago(String codigoTransaccion) {
        comprobantePagoRepository.deleteById(codigoTransaccion);
    }
}
