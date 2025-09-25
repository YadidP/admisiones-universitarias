package com.mycompany.controllers;

import com.mycompany.model.Documento;
import com.mycompany.model.EstadoDocumento;
import com.mycompany.model.User;
import com.mycompany.services.DocumentoService;
import com.mycompany.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MisDocumentosController {

    @FXML private TableView<Documento> documentosTableView;
    @FXML private TableColumn<Documento, String> documentoCol;
    @FXML private TableColumn<Documento, EstadoDocumento> estadoCol;
    @FXML private TableColumn<Documento, Void> accionCol;
    @FXML private TextFlow observacionesTextFlow;

    private DocumentoService documentoService = new DocumentoService();
    private ObservableList<Documento> documentosList = FXCollections.observableArrayList();
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        loadDocumentos();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
    }

    private void setupTableColumns() {
        documentoCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));

        estadoCol.setCellFactory(column -> new TableCell<>() { // CAMBIO: _ por column
            @Override
            protected void updateItem(EstadoDocumento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.toString());
                    switch (item) {
                        case PENDIENTE -> setTextFill(Color.ORANGE);
                        case SUBIDO -> setTextFill(Color.BLUE);
                        case VALIDADO -> setTextFill(Color.GREEN);
                        case OBSERVADO -> setTextFill(Color.RED);
                    }
                    setStyle("-fx-font-weight: bold;");
                }
            }
        });

        Callback<TableColumn<Documento, Void>, TableCell<Documento, Void>> cellFactory = param -> { // CAMBIO: _ por param
            return new TableCell<>() {
                private final Button btn = new Button("Subir Archivo");
                {
                    btn.setOnAction(event -> { // CAMBIO: _ por event
                        Documento data = getTableView().getItems().get(getIndex());
                        handleUploadAction(data);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : btn);
                }
            };
        };
        accionCol.setCellFactory(cellFactory);
    }

    private void loadDocumentos() {
        if (currentUser == null || currentUser.getId() == null) {
            System.err.println("Cannot load documents, user is not set or has no id.");
            return;
        }
        List<Documento> documentos = documentoService.getDocumentosPorPostulante(currentUser.getId());
        documentosList.setAll(documentos);
        documentosTableView.setItems(documentosList);
        displayObservaciones(documentos);
    }

    private void handleUploadAction(Documento documento) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar " + documento.getNombre());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) documentosTableView.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            Long docId = documento.getId();
            if (docId == null) {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "Documento no válido para subir.");
                return;
            }
            boolean success = documentoService.subirDocumento(currentUser.getId(), docId, selectedFile);
            if (success) {
                AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Subida Exitosa", null, "El documento '" + documento.getNombre() + "' ha sido subido correctamente.");
                loadDocumentos();
            } else {
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Subida", null, "Ocurrió un error al subir el documento.");
            }
        }
    }
    
    private void displayObservaciones(List<Documento> documentos) {
        observacionesTextFlow.getChildren().clear();
        boolean hasObservaciones = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Documento doc : documentos) {
            if (doc.getEstado() == EstadoDocumento.OBSERVADO && doc.getObservacion() != null) {
                hasObservaciones = true;
                Text docName = new Text(doc.getNombre() + ": ");
                docName.setFont(Font.font("System", FontWeight.BOLD, 14));
                Text observation = new Text(doc.getObservacion() + " ");
                observation.setFont(Font.font("System", FontWeight.NORMAL, 14));
                observacionesTextFlow.getChildren().addAll(docName, observation);

                if (doc.getFechaLimite() != null) {
                    Text deadlineLabel = new Text("Fecha Límite: ");
                    deadlineLabel.setFill(Color.RED);
                    Text deadline = new Text(doc.getFechaLimite().format(formatter) + "\n\n");
                    deadline.setFill(Color.RED);
                    observacionesTextFlow.getChildren().addAll(deadlineLabel, deadline);
                }
            }
        }

        if (!hasObservaciones) {
            observacionesTextFlow.getChildren().add(new Text("No tienes ninguna observación en tus documentos."));
        }
    }
}