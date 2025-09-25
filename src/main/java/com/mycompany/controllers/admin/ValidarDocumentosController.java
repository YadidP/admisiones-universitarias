package com.mycompany.controllers.admin;

import com.mycompany.model.Documento;
import com.mycompany.model.EstadoDocumento;
import com.mycompany.model.Postulante;
import com.mycompany.services.DocumentacionService;
import com.mycompany.util.AlertUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;


import java.util.Optional;

public class ValidarDocumentosController {

    @FXML
    private TableView<Postulante> postulantesTableView;
    @FXML
    private TableColumn<Postulante, String> postulanteNombreCol;
    @FXML
    private TableColumn<Postulante, String> postulanteCiCol;
    @FXML
    private TableColumn<Postulante, String> postulanteCarreraCol;

    @FXML
    private Label documentosTitleLabel;
    @FXML
    private TableView<Documento> documentosTableView;
    @FXML
    private TableColumn<Documento, String> documentoNombreCol;
    @FXML
    private TableColumn<Documento, EstadoDocumento> documentoEstadoCol;
    @FXML
    private TableColumn<Documento, Void> documentoAccionCol;

    private final DocumentacionService documentacionService = new DocumentacionService();
    private ObservableList<Postulante> postulantesList = FXCollections.observableArrayList();
    private ObservableList<Documento> documentosList = FXCollections.observableArrayList();

    private Postulante selectedPostulante;

    @FXML
    public void initialize() {
        setupPostulantesTable();
        setupDocumentosTable();
        loadPostulantes();

        postulantesTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newSelection) -> {
                    if (newSelection != null) {
                        selectedPostulante = newSelection;
                        documentosTitleLabel.setText("Documentos de: " + selectedPostulante.getNombreCompleto());
                        loadDocumentosForSelectedPostulante(selectedPostulante);
                    } else {
                        selectedPostulante = null;
                        documentosTitleLabel.setText("Documentos de: (Seleccione un postulante)");
                        documentosList.clear();
                    }
                });
    }

    private void setupPostulantesTable() {
        postulanteNombreCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombreCompleto()));
        postulanteCiCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCi()));
        postulanteCarreraCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCarrera().getNombre()));
    }

    private void setupDocumentosTable() {
        documentoNombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        documentoEstadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));

        documentoEstadoCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(EstadoDocumento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.toString());
                    switch (item) {
                        case PENDIENTE:
                            setTextFill(Color.ORANGE);
                            break;
                        case SUBIDO:
                            setTextFill(Color.BLUE);
                            break;
                        case VALIDADO:
                            setTextFill(Color.GREEN);
                            break;
                        case OBSERVADO:
                            setTextFill(Color.RED);
                            break;
                    }
                    setStyle("-fx-font-weight: bold;");
                }
            }
        });

        Callback<TableColumn<Documento, Void>, TableCell<Documento, Void>> cellFactory = column -> {
            return new TableCell<>() {
                private final Button validarBtn = new Button("Validar");
                private final Button rechazarBtn = new Button("Rechazar");
                private final HBox pane = new HBox(validarBtn, rechazarBtn);

                {
                    pane.setSpacing(5);
                    validarBtn.setOnAction(event -> {
                        Documento doc = getTableView().getItems().get(getIndex());
                        handleValidarDocumento(doc);
                    });
                    rechazarBtn.setOnAction(event -> {
                        Documento doc = getTableView().getItems().get(getIndex());
                        handleRechazarDocumento(doc);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    }
                    else {
                        Documento doc = getTableView().getItems().get(getIndex());
                        validarBtn.setDisable(doc.getEstado() == EstadoDocumento.VALIDADO);
                        rechazarBtn.setDisable(doc.getEstado() == EstadoDocumento.OBSERVADO);
                        setGraphic(pane);
                    }
                }
            };
        };
        documentoAccionCol.setCellFactory(cellFactory);
    }

    private void loadPostulantes() {
        postulantesList.setAll(documentacionService.getPostulantesConDocumentosPendientes());
        postulantesTableView.setItems(postulantesList);
    }

    private void loadDocumentosForSelectedPostulante(Postulante postulante) {
        documentosList.setAll(documentacionService.getDocumentosPorPostulante(postulante));
        documentosTableView.setItems(documentosList);
    }

    private void handleValidarDocumento(Documento documento) {
        if (selectedPostulante != null) {
            boolean success = documentacionService.validarDocumento(selectedPostulante, documento);
            if (success) {
                AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Documento validado correctamente.");
                loadDocumentosForSelectedPostulante(selectedPostulante); // Refresh documents
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo validar el documento.");
            }
        }
    }

    private void handleRechazarDocumento(Documento documento) {
        if (selectedPostulante != null) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Rechazar Documento");
            dialog.setHeaderText("Ingrese el motivo del rechazo para el documento: " + documento.getNombre());
            dialog.setContentText("Motivo:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(motivo -> {
                boolean success = documentacionService.rechazarDocumento(selectedPostulante, documento, motivo);
                if (success) {
                    AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Documento rechazado correctamente.");
                    loadDocumentosForSelectedPostulante(selectedPostulante); // Refresh documents
                } else {
                    AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo rechazar el documento.");
                }
            });
        }
    }
}