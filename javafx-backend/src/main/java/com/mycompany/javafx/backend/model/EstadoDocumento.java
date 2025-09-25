package com.mycompany.javafx.backend.model;

public enum EstadoDocumento {
    PENDIENTE,      // The user has not uploaded the document yet
    SUBIDO,         // The user has uploaded the document, pending review
    VALIDADO,       // The document has been approved by an admin
    OBSERVADO       // The document was rejected and has an observation
}
