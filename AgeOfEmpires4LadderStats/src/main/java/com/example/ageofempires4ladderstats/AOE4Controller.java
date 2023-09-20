package com.example.ageofempires4ladderstats;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AOE4Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}