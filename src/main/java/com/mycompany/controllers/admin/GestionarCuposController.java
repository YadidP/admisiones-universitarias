package com.mycompany.controllers.admin;


import com.mycompany.model.CupoCarrera;
import com.mycompany.services.CuposService;
import com.mycompany.services.NotificationService;
import com.mycompany.services.PostulanteService;
import com.mycompany.util.AlertUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.converter.IntegerStringConverter;

import java.util.HashMap;
import java.util.Map;

public class GestionarCuposController {

    @FXML
    private TableView<CupoCarrera> cuposTableView;
    @FXML
    private TableColumn<CupoCarrera, String> carreraCol;
    @FXML
    private TableColumn<CupoCarrera, Integer> cuposCol;
    // Removed accionCol

    private final CuposService cuposService = new CuposService();
    private final NotificationService notificationService = new NotificationService(); // New instance
    private final PostulanteService postulanteService = new PostulanteService(); // New instance
    private ObservableList<CupoCarrera> cuposList = FXCollections.observableArrayList();

    // Map to track changes before saving
    private final Map<CupoCarrera, Integer> cambiosPendientes = new HashMap<>();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadCupos();
    }

    private void setupTableColumns() {
        carreraCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCarrera().getNombre()));
        cuposCol.setCellValueFactory(new PropertyValueFactory<>("cantidadCupos"));

        // Make cuposCol editable
        cuposTableView.setEditable(true); // Enable table editing
        cuposCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cuposCol.setOnEditCommit(event -> {
            CupoCarrera cupo = event.getRowValue();
            int newCantidad = event.getNewValue();
            if (newCantidad >= 0) {
                cambiosPendientes.put(cupo, newCantidad);
                cupo.setCantidadCupos(newCantidad); // Update model immediately for UI
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Entrada", null, "La cantidad de cupos no puede ser negativa.");
                cuposTableView.refresh(); // Revert UI change
            }
        });

        // Removed accionCol and its cellFactory
    }

    private void loadCupos() {
        cuposList.setAll(cuposService.getCuposPorCarrera());
        cuposTableView.setItems(cuposList);
    }

    @FXML
    void handleSaveChanges(ActionEvent event) {
        if (cambiosPendientes.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Sin Cambios", null, "No hay cambios pendientes para guardar.");
            return;
        }

        boolean allSuccess = true;
        for (Map.Entry<CupoCarrera, Integer> entry : cambiosPendientes.entrySet()) {
            CupoCarrera cupo = entry.getKey();
            Integer newCantidad = entry.getValue();
            if (!cuposService.actualizarCupo(cupo.getCarrera(), newCantidad)) {
                allSuccess = false;
                System.err.println("Failed to update quota for " + cupo.getCarrera().getNombre());
            } else {
                // Check for over-quota after successful update (US5-Jhilmer)
                int enrolledCount = postulanteService.getEnrolledCount(cupo.getCarrera());
                if (enrolledCount > newCantidad) {
                    String message = String.format("¡Advertencia! La carrera '%s' tiene %d inscritos, superando el nuevo cupo de %d.",
                            cupo.getCarrera().getNombre(), enrolledCount, newCantidad);
                    notificationService.sendGeneralNotification(message);
                    AlertUtils.showAlert(Alert.AlertType.WARNING, "Sobrecupo Detectado", null, message + "\nSe ha enviado una notificación.");
                }
            }
        }

        if (allSuccess) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Todos los cupos han sido actualizados correctamente.");
            cambiosPendientes.clear();
            loadCupos(); // Refresh to ensure consistency
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "Ocurrió un error al guardar algunos cupos.");
        }
    }
}