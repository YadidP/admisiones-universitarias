package com.mycompany.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.util.Optional;

public class AlertUtils {

    public static void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        // Optional: Add custom styling or icons
        // alert.getDialogPane().getStylesheets().add(AlertUtils.class.getResource("/com/mycompany/css/styles.css").toExternalForm());
        // alert.getDialogPane().getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public static boolean showConfirmation(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
