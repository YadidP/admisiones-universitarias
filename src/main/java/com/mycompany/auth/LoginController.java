package com.mycompany.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.App;
import com.mycompany.controllers.MainController;
import com.mycompany.model.Role;
import com.mycompany.model.User;
import com.mycompany.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = authenticate(username, password);

        if (user != null) {
            System.out.println("Login successful for user: " + user.getUsername() + " with role: " + user.getRole());
            loadMainApplication(user);
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Inicio de Sesión", null, "Credenciales inválidas.");
        }
    }

    private User authenticate(String username, String password) {
        try {
            String body = mapper.writeValueAsString(Map.of("username", username, "password", password));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(App.BASE_URL + "/api/auth/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return mapper.readValue(response.body(), User.class);
            } else {
                // Handle non-200 responses, e.g., server error or invalid credentials
                System.err.println("Authentication failed with status code: " + response.statusCode());
                System.err.println("Response body: " + response.body());
                AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Autenticación", null, "Error al comunicarse con el servidor de autenticación. Código: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error de Conexión", null, "No se pudo conectar con el servidor de autenticación. Verifique que el backend esté en ejecución.");
        }
        return null;
    }

    private void loadMainApplication(User user) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/main-view.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();
        mainController.initialize(user);

        Scene scene = new Scene(root, 1366, 768); // Set a larger size for the main view
        stage.setScene(scene);
        stage.setTitle("Sistema de Admisión Universitaria");
        stage.show();
    }

    @FXML
    void handleRegisterLinkAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/register.fxml"));
        Parent root = fxmlLoader.load();
        
        RegisterController registerController = fxmlLoader.getController();
        registerController.initialize(); // Llama a initialize explícitamente
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sistema de Admisión - Registro");
    }
}
