package com.example.ageofempires4ladderstats;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CivMapWinRate {
    public ImageView playerImage;
    public ImageView enemyImage;
    public Button backButton;
    public ImageView mapImage;
    private SceneController sceneController = new SceneController();
    public void switchToMainGUIScene(ActionEvent event) throws IOException {
        sceneController.switchToMainGUIScene(event);
    }
}
