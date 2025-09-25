package com.mycompany.controllers;

import com.mycompany.model.Postulante;
import com.mycompany.model.SolicitudCorreccion;
import com.mycompany.model.User;
import com.mycompany.services.CorreccionService;
import com.mycompany.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SolicitarCorreccionController {

    @FXML
    private Label currentFullNameLabel;
    @FXML
    private Label currentCiLabel;
    @FXML
    private Label currentEmailLabel;
    @FXML
    private TextField campoAfectadoField;
    @FXML
    private TextField nuevoValorField;
    @FXML
    private TextArea motivoTextArea;

    private final CorreccionService correccionService = new CorreccionService();
    private User currentUser;
    private Postulante currentPostulanteData;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        loadCurrentUserData();
    }

    private void loadCurrentUserData() {
        if (currentUser == null) return;

        currentPostulanteData = correccionService.getDatosPersonales(currentUser);
        if (currentPostulanteData != null) {
            // **LA CORRECCIÓN ESTÁ AQUÍ** (usaba 'postulante' en lugar de 'currentPostulanteData')
            currentFullNameLabel.setText(currentPostulanteData.getNombreCompleto());
            currentCiLabel.setText(currentPostulanteData.getCi());
            currentEmailLabel.setText(currentPostulanteData.getEmail());
        }
    }

    @FXML
    void handleSubmitRequest(ActionEvent event) {
        String campoAfectado = campoAfectadoField.getText();
        String nuevoValor = nuevoValorField.getText();
        String motivo = motivoTextArea.getText();

        if (campoAfectado.isEmpty() || nuevoValor.isEmpty() || motivo.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Validación", null, "Todos los campos son obligatorios.");
            return;
        }

        if (currentPostulanteData == null) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudieron cargar sus datos personales. Intente de nuevo.");
            return;
        }

        SolicitudCorreccion solicitud = new SolicitudCorreccion(
                currentPostulanteData,
                campoAfectado,
                getOldValueForField(campoAfectado),
                nuevoValor,
                motivo
        );

        boolean success = correccionService.enviarSolicitud(solicitud);

        if (success) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Solicitud Enviada", null, "Su solicitud de corrección ha sido enviada exitosamente.");
            clearForm();
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo enviar la solicitud de corrección.");
        }
    }

    private String getOldValueForField(String fieldName) {
        if (currentPostulanteData == null) return "";
        switch (fieldName.toLowerCase()) {
            case "nombre completo":
            case "nombre":
                return currentPostulanteData.getNombreCompleto();
            case "ci":
            case "cédula de identidad":
                return currentPostulanteData.getCi();
            case "correo":
            case "correo electrónico":
                return currentPostulanteData.getEmail();
            default:
                return "Desconocido";
        }
    }

    private void clearForm() {
        campoAfectadoField.clear();
        nuevoValorField.clear();
        motivoTextArea.clear();
    }
}