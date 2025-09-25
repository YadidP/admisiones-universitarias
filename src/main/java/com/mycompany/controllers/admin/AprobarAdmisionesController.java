package com.mycompany.controllers.admin;

import com.mycompany.model.EstadoAdmision;
import com.mycompany.model.ResultadoExamen;
import com.mycompany.services.AdmisionesService;
import com.mycompany.util.AlertUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class AprobarAdmisionesController {

    @FXML private TableView<ResultadoExamen> resultadosTableView;
    @FXML private TableColumn<ResultadoExamen, String> carreraCol;
    @FXML private TableColumn<ResultadoExamen, Double> puntajeCol;
    @FXML private TableColumn<ResultadoExamen, EstadoAdmision> estadoCol;
    @FXML private TableColumn<ResultadoExamen, String> nombrePostulanteCol;
    @FXML private TableColumn<ResultadoExamen, String> ciPostulanteCol;

    private final AdmisionesService admisionesService = new AdmisionesService();
    private ObservableList<ResultadoExamen> resultadosList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadResultados();
    }

    private void setupTableColumns() {
        carreraCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCarrera()));
        puntajeCol.setCellValueFactory(new PropertyValueFactory<>("puntajeObtenido"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));

        estadoCol.setCellFactory(column -> new TableCell<>() { // CAMBIO: _ por column
            @Override
            protected void updateItem(EstadoAdmision item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.toString());
                    if (item == EstadoAdmision.ADMITIDO) setTextFill(Color.GREEN);
                    else if (item == EstadoAdmision.RECHAZADO) setTextFill(Color.RED);
                    else setTextFill(Color.ORANGE);
                    setStyle("-fx-font-weight: bold;");
                }
            }
        });

        nombrePostulanteCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPostulante().getNombreCompleto()));
        ciPostulanteCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPostulante().getCi()));
    }

    private void loadResultados() {
        resultadosList.setAll(admisionesService.getResultadosFinales());
        resultadosTableView.setItems(resultadosList);
    }

    @FXML
    void handleAprobarLista(ActionEvent event) {
        if (AlertUtils.showConfirmation("Confirmar Aprobación", null, "¿Está seguro de que desea aprobar la lista oficial de admitidos?")) {
            if (admisionesService.aprobarListaOficial()) {
                AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Lista oficial de admitidos aprobada correctamente.");
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo aprobar la lista oficial.");
            }
        }
    }

    @FXML
    void handleExportarPdf(ActionEvent event) {
        if (admisionesService.exportarListaPdf()) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Lista de admitidos exportada a PDF (simulación).");
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo exportar la lista a PDF.");
        }
    }
}