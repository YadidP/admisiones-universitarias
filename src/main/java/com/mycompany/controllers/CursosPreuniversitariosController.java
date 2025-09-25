package com.mycompany.controllers;

import com.mycompany.model.Asignatura;

import com.mycompany.model.CursoPreuniversitario;
import com.mycompany.model.User;
import com.mycompany.services.CursosPreService;
import com.mycompany.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.stream.Collectors;

public class CursosPreuniversitariosController {

    @FXML
    private ComboBox<CursoPreuniversitario> cursoComboBox;
    @FXML
    private Label currentCourseNameLabel;
    @FXML
    private Label currentCourseCareerLabel;
    @FXML
    private Label currentCourseScheduleLabel;
    @FXML
    private Label currentCourseTeacherLabel;
    @FXML
    private Label currentCourseCostLabel;
    @FXML
    private Label currentCourseStatusLabel;
    @FXML
    private Label currentCourseSubjectsLabel;

    private final CursosPreService cursosPreService = new CursosPreService();
    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        loadCurrentCourse();
    }

    @FXML
    public void initialize() {
        cursoComboBox.setItems(FXCollections.observableArrayList(cursosPreService.getCursosDisponibles()));
    }

    @FXML
    void handleEnrollAction(ActionEvent event) {
        CursoPreuniversitario selectedCurso = cursoComboBox.getValue();
        if (selectedCurso == null) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Selección Requerida", null, "Por favor, seleccione un curso para inscribirse.");
            return;
        }

        if (currentUser == null) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", null, "No se ha podido identificar al usuario actual.");
            return;
        }

        boolean success = cursosPreService.inscribirPostulante(currentUser, selectedCurso);
        if (success) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Inscripción Exitosa", null, "Te has inscrito correctamente al curso: " + selectedCurso.getNombre());
            loadCurrentCourse(); // Refresh current course info
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Inscripción", null, "No se pudo completar la inscripción al curso.");
        }
    }

    private void loadCurrentCourse() {
        if (currentUser == null) return;

        CursoPreuniversitario currentCourse = cursosPreService.getCursoActual(currentUser);
        if (currentCourse != null) {
            currentCourseNameLabel.setText("Nombre del Curso: " + currentCourse.getNombre());
            currentCourseCareerLabel.setText("Carrera Asociada: " + currentCourse.getCarrera().getNombre());
            currentCourseScheduleLabel.setText("Horario: " + currentCourse.getHorario());
            currentCourseTeacherLabel.setText("Docente: " + currentCourse.getDocente());
            currentCourseCostLabel.setText("Costo: " + String.format("%.2f", currentCourse.getCosto()) + " Bs.");
            currentCourseStatusLabel.setText("Estado de Inscripción: " + currentCourse.getEstadoInscripcion());
            currentCourseSubjectsLabel.setText("Materias: " + currentCourse.getMaterias().stream().map(Asignatura::getNombre).collect(Collectors.joining(", ")));
        } else {
            currentCourseNameLabel.setText("Nombre del Curso: No inscrito en ningún curso.");
            currentCourseCareerLabel.setText("Carrera Asociada: -");
            currentCourseScheduleLabel.setText("Horario: -");
            currentCourseTeacherLabel.setText("Docente: -");
            currentCourseCostLabel.setText("Costo: -");
            currentCourseStatusLabel.setText("Estado de Inscripción: -");
            currentCourseSubjectsLabel.setText("Materias: -");
        }
    }
}
