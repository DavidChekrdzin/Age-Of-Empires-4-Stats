package com.example.ageofempires4ladderstats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class AOE4Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AOE4Application.class.getResource("MainGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Age of Empires IV Ladder Statistics");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        }

}