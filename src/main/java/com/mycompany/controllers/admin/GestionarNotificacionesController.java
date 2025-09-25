package com.mycompany.controllers.admin;

import com.mycompany.model.Notificacion;
import com.mycompany.model.Postulante;
import com.mycompany.services.NotificationService;
import com.mycompany.services.PostulanteService;
import com.mycompany.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GestionarNotificacionesController {

    @FXML
    private TextField destinatarioField;
    @FXML
    private TextArea mensajeTextArea;
    @FXML
    private TableView<Notificacion> notificacionesTableView;
    @FXML
    private TableColumn<Notificacion, String> fechaCol;
    @FXML
    private TableColumn<Notificacion, String> mensajeCol;
    @FXML
    private TableColumn<Notificacion, Boolean> leidaCol;

    private final NotificationService notificationService = new NotificationService();
    private final PostulanteService postulanteService = new PostulanteService(); // To get postulante by CI
    private ObservableList<Notificacion> notificacionesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadNotifications();
    }

    private void setupTableColumns() {
        fechaCol.setCellValueFactory(cellData -> {
            LocalDateTime fecha = cellData.getValue().getFecha();
            return new javafx.beans.property.ReadOnlyStringWrapper(fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        });
        mensajeCol.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        leidaCol.setCellValueFactory(new PropertyValueFactory<>("leida"));
    }

    private void loadNotifications() {
        notificacionesList.setAll(notificationService.getAllNotifications());
        notificacionesTableView.setItems(notificacionesList);
    }

    @FXML
    void handleSendNotification(ActionEvent event) {
        String destinatario = destinatarioField.getText().trim();
        String mensaje = mensajeTextArea.getText().trim();

        if (mensaje.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Validación", null, "El mensaje de la notificación no puede estar vacío.");
            return;
        }

        boolean success = false;
        if (destinatario.equalsIgnoreCase("GENERAL")) {
            success = notificationService.sendGeneralNotification(mensaje);
        } else if (!destinatario.isEmpty()) {
            // Simulate finding a postulante by CI
            Postulante targetPostulante = postulanteService.getPostulanteByCi(destinatario); // Need to add this method to PostulanteService
            if (targetPostulante != null) {
                success = notificationService.sendNotificationToPostulante(targetPostulante, mensaje);
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Destinatario Inválido", null, "No se encontró un postulante con el CI proporcionado.");
                return;
            }
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Validación", null, "Debe especificar un destinatario (CI o 'GENERAL').");
            return;
        }

        if (success) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Notificación enviada correctamente.");
            mensajeTextArea.clear();
            destinatarioField.clear();
            loadNotifications(); // Refresh table
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo enviar la notificación.");
        }
    }
}