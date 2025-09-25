package com.mycompany.controllers.admin;

import com.mycompany.model.Carrera;
import com.mycompany.model.Examen;
import com.mycompany.services.ExamenService;
import com.mycompany.services.PlanDeEstudiosService;
import com.mycompany.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.stream.Collectors;

public class GestionarExamenesController {

    @FXML
    private DatePicker fechaPicker;
    @FXML
    private TextField horaField;
    @FXML
    private TextField lugarField;
    @FXML
    private ComboBox<String> modalidadComboBox;
    @FXML
    private ComboBox<Carrera> carreraComboBox;

    @FXML
    private TableView<Examen> examenesTableView;
    @FXML
    private TableColumn<Examen, LocalDate> fechaCol;
    @FXML
    private TableColumn<Examen, LocalTime> horaCol;
    @FXML
    private TableColumn<Examen, String> lugarCol;
    @FXML
    private TableColumn<Examen, String> modalidadCol;
    @FXML
    private TableColumn<Examen, Carrera> carreraCol;
    @FXML
    private TableColumn<Examen, Void> accionesCol;

    private final ExamenService examenService = new ExamenService();
    private final PlanDeEstudiosService planDeEstudiosService = new PlanDeEstudiosService();
    private ObservableList<Examen> examenesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        modalidadComboBox.setItems(FXCollections.observableArrayList("Presencial", "En línea"));
        carreraComboBox.setItems(FXCollections.observableArrayList(
                planDeEstudiosService.getFacultades().stream()
                        .flatMap(facultad -> facultad.getCarreras().stream())
                        .collect(Collectors.toList())
        ));
        setupTableColumns();
        loadExamenes();
    }

    private void setupTableColumns() {
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaCol.setCellValueFactory(new PropertyValueFactory<>("hora"));
        lugarCol.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        modalidadCol.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        carreraCol.setCellValueFactory(new PropertyValueFactory<>("carrera"));

        // Custom cell for actions (Edit/Delete buttons)
        Callback<TableColumn<Examen, Void>, TableCell<Examen, Void>> cellFactory = column -> {
            return new TableCell<>() {
                private final Button editBtn = new Button("Editar");
                private final Button deleteBtn = new Button("Eliminar");
                private final HBox pane = new HBox(editBtn, deleteBtn);

                {
                    pane.setSpacing(5);
                    editBtn.setOnAction(event -> {
                        Examen examen = getTableView().getItems().get(getIndex());
                        // Implement edit logic: populate form with exam data
                        AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Editar", null, "Editar examen: " + examen.getCarrera().getNombre());
                    });
                    deleteBtn.setOnAction(event -> {
                        Examen examen = getTableView().getItems().get(getIndex());
                        if (AlertUtils.showConfirmation("Confirmar Eliminación", null, "¿Está seguro de que desea eliminar este examen?")) {
                            if (examenService.eliminarExamen(examen)) {
                                AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Eliminado", null, "Examen eliminado correctamente.");
                                loadExamenes(); // Refresh table
                            } else {
                                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo eliminar el examen.");
                            }
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(pane);
                    }
                }
            };
        };
        accionesCol.setCellFactory(cellFactory);
    }

    private void loadExamenes() {
        examenesList.setAll(examenService.getExamenesProgramados());
        examenesTableView.setItems(examenesList);
    }

    @FXML
    void handleProgramarExamen(ActionEvent event) {
        LocalDate fecha = fechaPicker.getValue();
        String horaText = horaField.getText();
        String lugar = lugarField.getText();
        String modalidad = modalidadComboBox.getValue();
        Carrera carrera = carreraComboBox.getValue();

        if (fecha == null || horaText.isEmpty() || lugar.isEmpty() || modalidad == null || carrera == null) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Validación", null, "Todos los campos son obligatorios.");
            return;
        }

        LocalTime hora;
        try {
            hora = LocalTime.parse(horaText);
        } catch (DateTimeParseException e) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Formato de Hora Inválido", null, "Por favor, ingrese la hora en formato HH:mm (ej: 09:00).");
            return;
        }

        Examen nuevoExamen = new Examen(0, fecha, hora, lugar, modalidad, carrera);
        if (examenService.programarExamen(nuevoExamen)) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", null, "Examen programado correctamente.");
            clearForm();
            loadExamenes(); // Refresh table
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se pudo programar el examen.");
        }
    }

    private void clearForm() {
        fechaPicker.setValue(null);
        horaField.clear();
        lugarField.clear();
        modalidadComboBox.setValue(null);
        carreraComboBox.setValue(null);
    }
}
