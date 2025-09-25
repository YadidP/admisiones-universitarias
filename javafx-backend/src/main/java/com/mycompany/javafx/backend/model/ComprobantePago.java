package com.mycompany.javafx.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprobantePago {

    @Id
    private String codigoTransaccion; // Using transaction code as primary key

    @ManyToOne
    @JoinColumn(name = "postulante_id")
    private Postulante postulante;

    private LocalDateTime fechaPago;
    private double monto;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

}
