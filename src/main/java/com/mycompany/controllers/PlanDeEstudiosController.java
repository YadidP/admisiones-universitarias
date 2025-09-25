package com.mycompany.controllers;

import com.mycompany.model.Asignatura;
import com.mycompany.model.Carrera;
import com.mycompany.model.Facultad;
import com.mycompany.model.Semestre;
import com.mycompany.services.PlanDeEstudiosService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.util.List;

public class PlanDeEstudiosController {

    @FXML
    private ComboBox<Facultad> facultadComboBox;

    @FXML
    private ComboBox<Carrera> carreraComboBox;

    @FXML
    private TreeView<String> planTreeView;

    @FXML
    private VBox infoContainer;

    @FXML
    private Label totalAsignaturasLabel;

    @FXML
    private Label modalidadesLabel;

    private final PlanDeEstudiosService planDeEstudiosService = new PlanDeEstudiosService();

    @FXML
    public void initialize() {
        // Load faculties into the first ComboBox
        facultadComboBox.setItems(FXCollections.observableArrayList(planDeEstudiosService.getFacultades()));
        planTreeView.setRoot(new TreeItem<>("Plan de Estudios"));
        planTreeView.setShowRoot(false);
    }

    @FXML
    void onFacultadSelected(ActionEvent event) {
        Facultad selectedFacultad = facultadComboBox.getValue();
        if (selectedFacultad != null) {
            carreraComboBox.setItems(FXCollections.observableArrayList(planDeEstudiosService.getCarrerasPorFacultad(selectedFacultad)));
            carreraComboBox.setDisable(false);
            carreraComboBox.setValue(null); // Reset selection
            planTreeView.getRoot().getChildren().clear();
            infoContainer.setVisible(false);
        }
    }

    @FXML
    void onCarreraSelected(ActionEvent event) {
        Carrera selectedCarrera = carreraComboBox.getValue();
        if (selectedCarrera != null) {
            populatePlanDeEstudios(selectedCarrera);
            updateInfo(selectedCarrera);
            infoContainer.setVisible(true);
        }
    }

    private void populatePlanDeEstudios(Carrera carrera) {
        planTreeView.getRoot().getChildren().clear();
        List<Semestre> plan = planDeEstudiosService.getPlanDeEstudio(carrera);

        if (plan.isEmpty()) {
            planTreeView.getRoot().getChildren().add(new TreeItem<>("Plan de estudios no disponible."));
        } else {
            for (Semestre semestre : plan) {
                TreeItem<String> semestreItem = new TreeItem<>("Semestre " + semestre.getNumero());
                for (Asignatura asignatura : semestre.getAsignaturas()) {
                    semestreItem.getChildren().add(new TreeItem<>(asignatura.toString()));
                }
                planTreeView.getRoot().getChildren().add(semestreItem);
                semestreItem.setExpanded(true);
            }
        }
    }

    private void updateInfo(Carrera carrera) {
        int total = planDeEstudiosService.getTotalAsignaturas(carrera);
        totalAsignaturasLabel.setText("Total de Asignaturas para Aprobar: " + total);

        List<String> modalidades = planDeEstudiosService.getModalidadesTitulacion(carrera);
        modalidadesLabel.setText("Modalidades de Titulación: " + String.join(", ", modalidades));
    }
}
