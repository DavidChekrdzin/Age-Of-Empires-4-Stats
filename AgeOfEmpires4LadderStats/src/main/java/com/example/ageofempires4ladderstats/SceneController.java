package com.example.ageofempires4ladderstats;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToMainGUIScene(ActionEvent event) throws IOException {
        File file = new File("src/main/resources/com/example/ageofempires4ladderstats/MainGUI.fxml");
        root = FXMLLoader.load(file.toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        File file2 = new File("src/main/resources/com/example/ageofempires4ladderstats/layout.css");
        scene.getStylesheets().add(String.valueOf(file2.toURL()));
        stage.show();
    }
    public void switchToAddOrRemoveGamesScene(ActionEvent event) throws IOException {
        File file = new File("src/main/resources/com/example/ageofempires4ladderstats/AddOrRemoveGames.fxml");
        root = FXMLLoader.load(file.toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        File file2 = new File("src/main/resources/com/example/ageofempires4ladderstats/layout.css");
        scene.getStylesheets().add(String.valueOf(file2.toURL()));
        stage.show();
    }
    public void switchToCivMapWinRateScene(ActionEvent event) throws IOException {
        File file = new File("src/main/resources/com/example/ageofempires4ladderstats/CivMapWinRate.fxml");
        root = FXMLLoader.load(file.toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        File file2 = new File("src/main/resources/com/example/ageofempires4ladderstats/layout.css");
        scene.getStylesheets().add(String.valueOf(file2.toURL()));
        stage.show();
    }
    public void switchToCivVsCivWinRateScene(ActionEvent event) throws IOException {
        File file = new File("src/main/resources/com/example/ageofempires4ladderstats/CivVSCivResults.fxml");
        root = FXMLLoader.load(file.toURL());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        File file2 = new File("src/main/resources/com/example/ageofempires4ladderstats/layout.css");
        scene.getStylesheets().add(String.valueOf(file2.toURL()));
        stage.show();
    }
}
