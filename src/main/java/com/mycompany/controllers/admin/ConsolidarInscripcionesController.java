package com.mycompany.controllers.admin;

import com.mycompany.model.Carrera;
import com.mycompany.model.Facultad;
import com.mycompany.model.Postulante;
import com.mycompany.services.ConsolidadoService;
import com.mycompany.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.List;

public class ConsolidarInscripcionesController {

    @FXML
    private TreeView<String> consolidadoTreeView;

    private final ConsolidadoService consolidadoService = new ConsolidadoService();

    @FXML
    public void initialize() {
        loadConsolidado();
    }

    private void loadConsolidado() {
        List<Facultad> facultades = consolidadoService.getConsolidadoInscripciones();

        TreeItem<String> rootItem = new TreeItem<>("Inscripciones por Facultad");
        rootItem.setExpanded(true);
        consolidadoTreeView.setRoot(rootItem);
        consolidadoTreeView.setShowRoot(false);

        for (Facultad facultad : facultades) {
            TreeItem<String> facultadItem = new TreeItem<>(facultad.getNombre());
            facultadItem.setExpanded(true);
            rootItem.getChildren().add(facultadItem);

            for (Carrera carrera : facultad.getCarreras()) {
                List<Postulante> postulantes = consolidadoService.getPostulantesPorCarrera(carrera);
                String carreraNodeText = String.format("%s (%d postulantes)", carrera.getNombre(), postulantes.size());
                TreeItem<String> carreraItem = new TreeItem<>(carreraNodeText);
                carreraItem.setExpanded(true);
                facultadItem.getChildren().add(carreraItem);

                for (Postulante postulante : postulantes) {
                    String postulanteNodeText = String.format("%s (CI: %s)", postulante.getNombreCompleto(), postulante.getCi());
                    carreraItem.getChildren().add(new TreeItem<>(postulanteNodeText));
                }
            }
        }
    }

    @FXML
    void handlePrintReport(ActionEvent event) {
        // In a real application, this would generate a PDF or print the report.
        System.out.println("--- SIMULATING REPORT PRINTING ---");
        AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Reporte Generado", null, "El reporte consolidado ha sido enviado a la impresora (simulación).");
    }
}