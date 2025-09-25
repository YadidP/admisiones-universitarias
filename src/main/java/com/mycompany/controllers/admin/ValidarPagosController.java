package com.mycompany.controllers.admin;

import com.mycompany.model.ComprobantePago;
import com.mycompany.model.EstadoPago;
import com.mycompany.model.Postulante;
import com.mycompany.services.NotificationService;
import com.mycompany.services.PagoService;

import com.mycompany.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ValidarPagosController {

    @FXML
    private TextField searchField;
    @FXML
    private VBox resultContainer;
    @FXML
    private Label statusMessageLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label ciLabel;
    @FXML
    private Label carreraLabel;
    @FXML
    private Label fechaPagoLabel;
    @FXML
    private Label estadoActualLabel;
    @FXML
    private HBox actionButtonsContainer;
    @FXML
    private Button validarButton;
    @FXML
    private Button rechazarButton;
    @FXML
    private Button notificarPendienteButton;

    private final PagoService pagoService = new PagoService();
    private final NotificationService notificationService = new NotificationService();
    
    private ComprobantePago currentComprobante;

    @FXML
    void handleSearchAction(ActionEvent event) {
        String codigo = searchField.getText().trim();
        if (codigo.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Campo Vacío", null, "Por favor, ingrese un código de transacción.");
            return;
        }

        currentComprobante = pagoService.buscarComprobante(codigo);
        resultContainer.setVisible(true);

        if (currentComprobante == null) {
            showNotFound();
        } else {
            displayComprobante(currentComprobante);
        }
    }

    @FXML
    void handleValidarAction(ActionEvent event) {
        boolean success = pagoService.validarPago(currentComprobante.getCodigoTransaccion());
        if (success) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "El pago ha sido validado correctamente.");
            handleSearchAction(null); // Refresh data
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo validar el pago.");
        }
    }

    @FXML
    void handleRechazarAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Rechazar Pago");
        dialog.setHeaderText("Ingrese el motivo del rechazo para el comprobante: " + currentComprobante.getCodigoTransaccion());
        dialog.setContentText("Motivo:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(motivo -> {
            boolean success = pagoService.rechazarPago(currentComprobante.getCodigoTransaccion(), motivo);
            if (success) {
                AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "El pago ha sido rechazado.");
                handleSearchAction(null); // Refresh data
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo rechazar el pago.");
            }
        });
    }

    @FXML
    void handleNotifyPendingPayment(ActionEvent event) {
        if (currentComprobante != null && currentComprobante.getEstado() == EstadoPago.PENDIENTE) {
            Postulante postulante = currentComprobante.getPostulante();
            String message = String.format("Recordatorio: Su pago con código %s por %.2f Bs. aún está pendiente de validación. Por favor, regularice su situación.",
                    currentComprobante.getCodigoTransaccion(), currentComprobante.getMonto());
            if (notificationService.sendNotificationToPostulante(postulante, message)) {
                AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Notificación Enviada", null, "Notificación de pago pendiente enviada a " + postulante.getNombreCompleto() + ".");
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo enviar la notificación.");
            }
        } else {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Acción Inválida", null, "Solo se puede notificar pagos pendientes.");
        }
    }

    private void showNotFound() {
        statusMessageLabel.setText("Comprobante no encontrado.");
        statusMessageLabel.setTextFill(Color.RED);
        nombreLabel.getParent().setVisible(false); // Hide the GridPane
        actionButtonsContainer.setVisible(false);
    }

    private void displayComprobante(ComprobantePago comp) {
        nombreLabel.getParent().setVisible(true); // Show the GridPane
        actionButtonsContainer.setVisible(true);

        nombreLabel.setText(comp.getPostulante().getNombreCompleto());
        ciLabel.setText(comp.getPostulante().getCi());
        carreraLabel.setText(comp.getPostulante().getCarrera().getNombre());
        fechaPagoLabel.setText(comp.getFechaPago().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        estadoActualLabel.setText(comp.getEstado().toString());

        switch (comp.getEstado()) {
            case PENDIENTE:
                statusMessageLabel.setText("Este pago está PENDIENTE de validación.");
                statusMessageLabel.setTextFill(Color.ORANGE);
                validarButton.setDisable(false);
                rechazarButton.setDisable(false);
                notificarPendienteButton.setDisable(false);
                break;
            case VALIDADO:
                statusMessageLabel.setText("Este pago ya fue VALIDADO.");
                statusMessageLabel.setTextFill(Color.GREEN);
                validarButton.setDisable(true);
                rechazarButton.setDisable(false);
                notificarPendienteButton.setDisable(true);
                break;
            case RECHAZADO:
                statusMessageLabel.setText("Este pago fue RECHAZADO.");
                statusMessageLabel.setTextFill(Color.RED);
                validarButton.setDisable(false);
                rechazarButton.setDisable(true);
                notificarPendienteButton.setDisable(true);
                break;
            case DUPLICADO:
                statusMessageLabel.setText("Error: Este pago está marcado como DUPLICADO.");
                statusMessageLabel.setTextFill(Color.PURPLE);
                validarButton.setDisable(true);
                rechazarButton.setDisable(true);
                notificarPendienteButton.setDisable(true);
                break;
        }
    }
}
