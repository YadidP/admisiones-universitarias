package com.mycompany.controllers;


import com.mycompany.model.ResultadoExamen;
import com.mycompany.model.User;
import java.io.File;
import com.mycompany.services.ResultadosService;
import com.mycompany.util.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class VerResultadosController {

    @FXML
    private VBox mainContainer;
    @FXML
    private VBox statusContainer;
    @FXML
    private Label statusLabel;
    @FXML
    private VBox detailsContainer;
    @FXML
    private Label carreraLabel;
    @FXML
    private Label puntajeLabel;
    @FXML
    private Label puntajeMinimoLabel;
    @FXML
    private Button downloadButton;

    private ResultadosService resultadosService = new ResultadosService();
    private User currentUser;

    // This method will be called by MainController
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        loadResultados();
    }

    private void loadResultados() {
        if (currentUser == null) return;
        if (currentUser.getId() == null) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Resultados", null, "Usuario sin ID. Inicie sesión correctamente.");
            return;
        }

        ResultadoExamen resultado = resultadosService.getResultadoPorPostulante(currentUser.getId());
        updateUI(resultado);
    }

    private void updateUI(ResultadoExamen resultado) {
        detailsContainer.setVisible(false);
        downloadButton.setVisible(false);

        if (resultado == null) {
            statusLabel.setText("No hay resultados de examen disponibles.");
            statusLabel.setTextFill(Color.GRAY);
            statusContainer.setStyle("-fx-border-color: gray; -fx-border-radius: 10; -fx-padding: 40;");
            return;
        }

        statusLabel.setText(resultado.getEstado().toString());

        switch (resultado.getEstado()) {
            case ADMITIDO:
                statusLabel.setText("¡FELICIDADES, HAS SIDO ADMITIDO!");
                statusLabel.setTextFill(Color.GREEN);
                statusContainer.setStyle("-fx-border-color: green; -fx-border-radius: 10; -fx-padding: 40;");
                detailsContainer.setVisible(true);
                downloadButton.setVisible(true);
                break;
            case RECHAZADO:
                statusLabel.setText("LO SENTIMOS, NO ALCANZASTE EL PUNTAJE");
                statusLabel.setTextFill(Color.RED);
                statusContainer.setStyle("-fx-border-color: red; -fx-border-radius: 10; -fx-padding: 40;");
                detailsContainer.setVisible(true);
                downloadButton.setVisible(true);
                break;
            case PENDIENTE:
                statusLabel.setText("LOS RESULTADOS AÚN NO ESTÁN DISPONIBLES");
                statusLabel.setTextFill(Color.ORANGE);
                statusContainer.setStyle("-fx-border-color: orange; -fx-border-radius: 10; -fx-padding: 40;");
                break;
        }

        if (detailsContainer.isVisible()) {
            carreraLabel.setText("Carrera: " + resultado.getCarrera());
            puntajeLabel.setText("Puntaje Obtenido: " + resultado.getPuntajeObtenido());
            puntajeMinimoLabel.setText("Puntaje Mínimo de Aprobación: " + resultado.getPuntajeMinimo());
        }
    }

    @FXML
    void handleDownloadAction(ActionEvent event) {
        if (currentUser == null || currentUser.getId() == null) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Descarga de PDF", null, "Usuario no autenticado o sin ID.");
            return;
        }

        String downloads = System.getProperty("user.home") + File.separator + "Downloads";
        String targetPath = downloads + File.separator + "resultados_postulante_" + currentUser.getId() + ".pdf";
        boolean ok = resultadosService.descargarPdf(currentUser.getId(), targetPath);
        if (ok) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Descarga de PDF", null, "El PDF de resultados se guardó en: " + targetPath);
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Descarga de PDF", null, "No se pudo descargar el PDF de resultados.");
        }
        
    }
}