package com.mycompany.javafx.backend.controller;

import com.mycompany.javafx.backend.model.ComprobantePago;
import com.mycompany.javafx.backend.model.EstadoPago;
import com.mycompany.javafx.backend.service.ComprobantePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes-pago")
public class ComprobantePagoController {

    @Autowired
    private ComprobantePagoService comprobantePagoService;

    @GetMapping
    public List<ComprobantePago> getAllComprobantesPago() {
        return comprobantePagoService.findAllComprobantesPago();
    }

    @GetMapping("/{codigoTransaccion}")
    public ResponseEntity<ComprobantePago> getComprobantePagoByCodigoTransaccion(@PathVariable String codigoTransaccion) {
        return comprobantePagoService.findComprobantePagoByCodigoTransaccion(codigoTransaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public List<ComprobantePago> getComprobantesPagoByEstado(@PathVariable EstadoPago estado) {
        return comprobantePagoService.findComprobantesPagoByEstado(estado);
    }

    @PostMapping
    public ResponseEntity<ComprobantePago> createComprobantePago(@RequestBody ComprobantePago comprobantePago) {
        ComprobantePago savedComprobantePago = comprobantePagoService.saveComprobantePago(comprobantePago);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComprobantePago);
    }

    @PutMapping("/{codigoTransaccion}")
    public ResponseEntity<ComprobantePago> updateComprobantePago(@PathVariable String codigoTransaccion, @RequestBody ComprobantePago comprobantePago) {
        return comprobantePagoService.findComprobantePagoByCodigoTransaccion(codigoTransaccion)
                .map(existingComprobantePago -> {
                    comprobantePago.setCodigoTransaccion(codigoTransaccion); // Lombok will now generate this setter
                    return ResponseEntity.ok(comprobantePagoService.saveComprobantePago(comprobantePago));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codigoTransaccion}")
    public ResponseEntity<Void> deleteComprobantePago(@PathVariable String codigoTransaccion) {
        if (comprobantePagoService.findComprobantePagoByCodigoTransaccion(codigoTransaccion).isPresent()) {
            comprobantePagoService.deleteComprobantePago(codigoTransaccion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
