package com.mycompany.auth;

import com.mycompany.App;
import com.mycompany.model.Carrera;
import com.mycompany.model.Postulante;
import com.mycompany.services.PostulanteService;
import com.mycompany.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RegisterController {

    @FXML
    private TextField nombresField;

    @FXML
    private TextField apellidosField;

    @FXML
    private TextField ciField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<Carrera> carreraComboBox;

    @FXML
    private PasswordField passwordField;

    private final PostulanteService postulanteService = new PostulanteService();

    @FXML
    public void initialize() {
        List<Carrera> carreras = postulanteService.getCarrerasDisponibles();
        carreraComboBox.setItems(FXCollections.observableArrayList(carreras));
    }

    @FXML
    void handleRegisterButtonAction(ActionEvent event) throws IOException {
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String ci = ciField.getText();
        String email = emailField.getText();
        Carrera carrera = carreraComboBox.getValue();
        String password = passwordField.getText();

        if (nombres.isEmpty() || apellidos.isEmpty() || ci.isEmpty() || email.isEmpty() || carrera == null || password.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Registro", null, "Todos los campos son obligatorios.");
            return;
        }

        // Basic email format validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Registro", null, "Formato de correo electrónico inválido.");
            return;
        }

        Postulante postulante = new Postulante(nombres, apellidos, ci, email, carrera, null);
        boolean success = postulanteService.registrar(postulante, password);

        if (success) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Registro Exitoso", null, "Postulante registrado correctamente.");
            handleBackToLoginAction(event);
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Registro", null, "No se pudo registrar el postulante. Verifica si el email o CI ya existe.");
        }
    }

    @FXML
    void handleBackToLoginAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) nombresField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sistema de Admisión - Inicio de Sesión");
    }
}
