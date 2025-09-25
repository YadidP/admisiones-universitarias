package com.mycompany.controllers.admin;

import com.mycompany.model.Aula;
import com.mycompany.model.Postulante;
import com.mycompany.services.AsignacionService;
import com.mycompany.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsignarAulasController {

    @FXML
    private TableView<Postulante> postulantesTableView;
    @FXML
    private TableColumn<Postulante, String> nombreCol;
    @FXML
    private TableColumn<Postulante, String> ciCol;
    @FXML
    private TableColumn<Postulante, String> carreraCol;
    @FXML
    private TableColumn<Postulante, Aula> aulaCol;

    private final AsignacionService asignacionService = new AsignacionService();
    private ObservableList<Postulante> postulantesList = FXCollections.observableArrayList();
    private List<Aula> aulasDisponibles;

    // Map to store current assignments before saving
    private final Map<Postulante, Aula> asignacionesPendientes = new HashMap<>();

    @FXML
    public void initialize() {
        aulasDisponibles = asignacionService.getAulasDisponibles();
        setupTableColumns();
        loadPostulantes();
    }

    private void setupTableColumns() {
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        ciCol.setCellValueFactory(new PropertyValueFactory<>("ci"));
        carreraCol.setCellValueFactory(new PropertyValueFactory<>("carrera"));

        // Custom cell for Aula assignment using ComboBox (con lambda en lugar de Callback explícito)
        aulaCol.setCellFactory(param -> new TableCell<>() {
            private final ComboBox<Aula> comboBox = new ComboBox<>();

            {
                comboBox.setItems(FXCollections.observableArrayList(aulasDisponibles));
                comboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        Postulante p = getTableView().getItems().get(getIndex());
                        asignacionesPendientes.put(p, newVal);
                    }
                });
            }

            @Override
            protected void updateItem(Aula item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Postulante p = getTableView().getItems().get(getIndex());
                    Aula assignedAula = asignacionesPendientes.getOrDefault(p, item);
                    comboBox.setValue(assignedAula);
                    setGraphic(comboBox);
                }
            }
        });
    }

    private void loadPostulantes() {
        postulantesList.setAll(asignacionService.getPostulantesParaAsignar());
        postulantesTableView.setItems(postulantesList);
    }

    @FXML
    void handleSaveAssignments(ActionEvent event) {
        if (asignacionesPendientes.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Sin Cambios", null, "No hay asignaciones pendientes para guardar.");
            return;
        }

        boolean allSuccess = true;
        for (Map.Entry<Postulante, Aula> entry : asignacionesPendientes.entrySet()) {
            Postulante p = entry.getKey();
            Aula a = entry.getValue();
            if (!asignacionService.asignarAula(p, a)) {
                allSuccess = false;
                System.err.println("Failed to assign " + p.getNombreCompleto() + " to " + a.getNombre());
            }
        }

        if (allSuccess) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Todas las asignaciones han sido guardadas correctamente.");
            asignacionesPendientes.clear(); // Clear pending assignments after saving
            loadPostulantes(); // Refresh table to show updated status
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "Ocurrió un error al guardar algunas asignaciones.");
        }
    }
}
