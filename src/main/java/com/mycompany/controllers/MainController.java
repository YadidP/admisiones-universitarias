package com.mycompany.controllers;

import com.mycompany.App;

import com.mycompany.model.Role;
import com.mycompany.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainController {

    @FXML
    private TabPane tabPane;
    @FXML
    private Label welcomeUserLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Label statusBarLabel;

    private User currentUser;

    public void initialize(User user) {
        this.currentUser = user;
        this.welcomeUserLabel.setText("Usuario: " + user.getNombreCompleto() + " (" + user.getRole() + ")");
        System.out.println("Main view initialized for user: " + currentUser.getUsername() + " with role: " + currentUser.getRole());
        setupTabs();
        updateStatusBar();
    }

    @FXML
    void handleLogoutAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Sistema de Admisión - Inicio de Sesión");
    }

    private void setupTabs() {
        tabPane.getTabs().clear();

        Tab welcomeTab = new Tab("Bienvenido");
        welcomeTab.setContent(new Label("Bienvenido, " + currentUser.getNombreCompleto() + "!\nSeleccione una pestaña para comenzar."));
        tabPane.getTabs().add(welcomeTab);

        if (currentUser.getRole() == Role.ADMIN) {
            loadAdminTabs();
        } else if (currentUser.getRole() == Role.POSTULANTE) {
            loadPostulanteTabs();
        }
    }

    private void loadAdminTabs() {
        loadTab("Validar Pagos", "fxml/admin/ValidarPagos.fxml", false);
        loadTab("Registrar Postulante", "fxml/admin/RegistrarPostulante.fxml", false);
        loadTab("Consolidar Inscripciones", "fxml/admin/ConsolidarInscripciones.fxml", false);
        loadTab("Asignar Aulas", "fxml/admin/AsignarAulas.fxml", false);
        loadTab("Gestionar Cupos", "fxml/admin/GestionarCupos.fxml", false);
        loadTab("Validar Documentos", "fxml/admin/ValidarDocumentos.fxml", false);
        loadTab("Gestionar Exámenes", "fxml/admin/GestionarExamenes.fxml", false);
        loadTab("Seguimiento de Documentos Físicos", "fxml/admin/SeguimientoDocumentos.fxml", false);
        loadTab("Aprobar Listas de Admisión", "fxml/admin/AprobarAdmisiones.fxml", false);
        loadTab("Gestionar Notificaciones", "fxml/admin/GestionarNotificaciones.fxml", false);
        loadTab("Generar Reportes", "fxml/admin/GenerarReportes.fxml", false);
    }

    private void loadPostulanteTabs() {
        loadTab("Información General", "fxml/InformacionGeneral.fxml", false);
        loadTab("Mis Documentos", "fxml/MisDocumentos.fxml", true);
        loadTab("Ver Resultados", "fxml/VerResultados.fxml", true);
        loadTab("Ver Plan de Estudios", "fxml/PlanDeEstudios.fxml", false);
        loadTab("Cursos Preuniversitarios", "fxml/CursosPreuniversitarios.fxml", true);
        loadTab("Solicitar Corrección de Datos", "fxml/SolicitarCorreccion.fxml", true);
    }

    private void loadTab(String title, String fxmlPath, boolean needsUser) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlPath));
            Parent content = loader.load();

            if (needsUser) {
                Object controller = loader.getController();
                if (controller instanceof MisDocumentosController) {
                    ((MisDocumentosController) controller).setCurrentUser(currentUser);
                } else if (controller instanceof VerResultadosController) {
                    ((VerResultadosController) controller).setCurrentUser(currentUser);
                } else if (controller instanceof CursosPreuniversitariosController) {
                    ((CursosPreuniversitariosController) controller).setCurrentUser(currentUser);
                } else if (controller instanceof SolicitarCorreccionController) {
                    ((SolicitarCorreccionController) controller).setCurrentUser(currentUser);
                }
            }

            Tab tab = new Tab(title);
            tab.setContent(content);
            tabPane.getTabs().add(tab);
        } catch (IOException e) {
            System.err.println("Failed to load FXML for tab: " + title);
            e.printStackTrace();
            tabPane.getTabs().add(createDummyTab(title + " (Error)"));
        }
    }

    private Tab createDummyTab(String title) {
        Tab tab = new Tab(title);
        AnchorPane content = new AnchorPane();
        Label label = new Label("Contenido para: " + title + "\n(En desarrollo)");
        AnchorPane.setTopAnchor(label, 20.0);
        AnchorPane.setLeftAnchor(label, 20.0);
        content.getChildren().add(label);
        tab.setContent(content);
        return tab;
    }

    private void updateStatusBar() {
        // Simulate checking the status of the admission period
        LocalDate today = LocalDate.now();
        LocalDate closeDate = LocalDate.parse(App.ADMISSION_CLOSE_DATE); // Use configurable close date
        LocalDate notificationDate = closeDate.minusDays(App.ADMISSION_NOTIFICATION_OFFSET_DAYS); // Use configurable offset

        if (today.isAfter(closeDate)) {
            statusBarLabel.setText("Estado del Sistema: Convocatoria CERRADA.");
            statusBarLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else if (today.isAfter(notificationDate) && today.isBefore(closeDate.plusDays(1))) {
            statusBarLabel.setText("Estado del Sistema: ¡ATENCIÓN! Cierre de convocatoria próximo: " + closeDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ".");
            statusBarLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
        } else {
            statusBarLabel.setText("Estado del Sistema: Convocatoria ABIERTA. Fecha de cierre: " + closeDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ".");
            statusBarLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }
    }
}