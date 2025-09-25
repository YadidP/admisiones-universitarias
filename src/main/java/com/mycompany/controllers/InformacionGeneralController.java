package com.mycompany.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class InformacionGeneralController {

    @FXML
    private ImageView mapImageView;

    @FXML
    public void initialize() {
        // In a real application, you would load a real map image.
        // For now, we'll load a placeholder image if available, or handle its absence.
        try {
            // Assuming you have a placeholder image in the resources folder
            // To make this work, create a file named 'university_map.png' in 'resources/com/mycompany/'
            InputStream is = getClass().getResourceAsStream("/com/mycompany/university_map.png");
            if (is != null) {
                Image mapImage = new Image(is);
                mapImageView.setImage(mapImage);
            } else {
                System.out.println("Placeholder map image not found. Please create 'university_map.png'.");
            }
        } catch (Exception e) {
            System.err.println("Error loading map image: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
