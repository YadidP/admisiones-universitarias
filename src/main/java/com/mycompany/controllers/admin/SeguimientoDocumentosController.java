package com.mycompany.controllers.admin;

import com.mycompany.model.Postulante;
import com.mycompany.services.SeguimientoDocumentosService;
import com.mycompany.util.AlertUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.util.Callback;

public class SeguimientoDocumentosController {

    @FXML
    private TableView<Postulante> postulantesTableView;
    @FXML
    private TableColumn<Postulante, String> nombreCol;
    @FXML
    private TableColumn<Postulante, String> ciCol;
    @FXML
    private TableColumn<Postulante, String> documentosFaltantesCol;
    @FXML
    private TableColumn<Postulante, Void> accionCol;

    private final SeguimientoDocumentosService seguimientoService = new SeguimientoDocumentosService();
    private ObservableList<Postulante> postulantesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadPostulantes();
    }

    private void setupTableColumns() {
        nombreCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombreCompleto()));
        ciCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCi()));
        documentosFaltantesCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(seguimientoService.getDocumentosFaltantes(cellData.getValue())));

        // Custom cell for action button
        Callback<TableColumn<Postulante, Void>, TableCell<Postulante, Void>> cellFactory = column -> {
            return new TableCell<>() {
                private final Button notifyBtn = new Button("Notificar");
                {
                    notifyBtn.setOnAction(event -> {
                        Postulante postulante = getTableView().getItems().get(getIndex());
                        if (seguimientoService.enviarNotificacion(postulante)) {
                            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Notificación Enviada", null, "Notificación enviada a " + postulante.getNombreCompleto());
                        } else {
                            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo enviar la notificación.");
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(notifyBtn);
                    }
                }
            };
        };
        accionCol.setCellFactory(cellFactory);
    }

    private void loadPostulantes() {
        postulantesList.setAll(seguimientoService.getPostulantesConDocumentosFisicosPendientes());
        postulantesTableView.setItems(postulantesList);
    }
}
