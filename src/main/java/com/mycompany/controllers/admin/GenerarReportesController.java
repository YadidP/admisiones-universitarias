package com.mycompany.controllers.admin;

import com.mycompany.model.Carrera;
import com.mycompany.model.Facultad;
import com.mycompany.services.PlanDeEstudiosService;
import com.mycompany.services.ReportesService;
import com.mycompany.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;


public class GenerarReportesController {

    @FXML
    private ComboBox<String> reportTypeComboBox;
    @FXML
    private VBox filterContainer;
    @FXML
    private HBox dateFilterBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private HBox careerFacultyFilterBox;
    @FXML
    private ComboBox<Facultad> facultyFilterComboBox;
    @FXML
    private ComboBox<Carrera> careerFilterComboBox;
    @FXML
    private TextArea reportOutputArea;

    private final ReportesService reportesService = new ReportesService();
    private final PlanDeEstudiosService planDeEstudiosService = new PlanDeEstudiosService(); // To get faculties/careers

    @FXML
    public void initialize() {
        reportTypeComboBox.setItems(FXCollections.observableArrayList(
                "Reporte de Ingresos Económicos",
                "Reporte de Inscritos",
                "Reporte de Resultados de Examen"
        ));

        facultyFilterComboBox.setItems(FXCollections.observableArrayList(planDeEstudiosService.getFacultades()));
        facultyFilterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                careerFilterComboBox.setItems(FXCollections.observableArrayList(planDeEstudiosService.getCarrerasPorFacultad(newVal)));
                careerFilterComboBox.setDisable(false);
            } else {
                careerFilterComboBox.getItems().clear();
                careerFilterComboBox.setDisable(true);
            }
        });
        careerFilterComboBox.setDisable(true);

        // Hide all filters initially
        dateFilterBox.setVisible(false);
        careerFacultyFilterBox.setVisible(false);
    }

    @FXML
    void onReportTypeSelected(ActionEvent event) {
        String selectedReportType = reportTypeComboBox.getValue();
        // Hide all filters first
        dateFilterBox.setVisible(false);
        careerFacultyFilterBox.setVisible(false);

        if (selectedReportType != null) {
            switch (selectedReportType) {
                case "Reporte de Ingresos Económicos":
                    dateFilterBox.setVisible(true);
                    break;
                case "Reporte de Inscritos":
                case "Reporte de Resultados de Examen":
                    careerFacultyFilterBox.setVisible(true);
                    break;
            }
        }
    }

    @FXML
    void handleGenerateReport(ActionEvent event) {
        String selectedReportType = reportTypeComboBox.getValue();
        if (selectedReportType == null) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Selección Requerida", null, "Por favor, seleccione un tipo de reporte.");
            return;
        }

        String reportContent = "";
        switch (selectedReportType) {
            case "Reporte de Ingresos Económicos":
                LocalDate startDate = startDatePicker.getValue();
                LocalDate endDate = endDatePicker.getValue();
                if (startDate == null || endDate == null) {
                    AlertUtils.showAlert(Alert.AlertType.ERROR, "Filtros Incompletos", null, "Por favor, seleccione un rango de fechas.");
                    return;
                }
                reportContent = reportesService.generarReporteIngresos(startDate, endDate);
                break;
            case "Reporte de Inscritos":
                String selectedCareerInscritos = (careerFilterComboBox.getValue() != null) ? careerFilterComboBox.getValue().getNombre() : null;
                String selectedFacultyInscritos = (facultyFilterComboBox.getValue() != null) ? facultyFilterComboBox.getValue().getNombre() : null;
                reportContent = reportesService.generarReporteInscritos(selectedCareerInscritos, selectedFacultyInscritos, null);
                break;
            case "Reporte de Resultados de Examen":
                String selectedCareerResultados = (careerFilterComboBox.getValue() != null) ? careerFilterComboBox.getValue().getNombre() : null;
                String selectedFacultyResultados = (facultyFilterComboBox.getValue() != null) ? facultyFilterComboBox.getValue().getNombre() : null;
                reportContent = reportesService.generarReporteResultados(selectedCareerResultados, selectedFacultyResultados);
                break;
            default:
                reportContent = "Tipo de reporte no reconocido.";
                break;
        }
        reportOutputArea.setText(reportContent);
    }
}