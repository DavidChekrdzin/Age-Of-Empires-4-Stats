package com.example.ageofempires4ladderstats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CivMapWinRate implements Initializable {

    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView enemyImage;
    @FXML
    private Button backButton;
    @FXML
    private ImageView mapImage;
    @FXML
    private TableView tableViewCivMap;
    @FXML
    private TableColumn abbasidColumn;
    @FXML
    private TableColumn chineseColumn;
    @FXML
    private TableColumn delhiColumn;
    @FXML
    private TableColumn englishColumn;
    @FXML
    private TableColumn frenchColumn;
    @FXML
    private TableColumn hreColumn;
    @FXML
    private TableColumn maliansColumn;
    @FXML
    private TableColumn mongolsColumn;
    @FXML
    private TableColumn ottomansColumn;
    @FXML
    private TableColumn rusColumn;
    private SceneController sceneController = new SceneController();
    public void switchToMainGUIScene(ActionEvent event) throws IOException {
        sceneController.switchToMainGUIScene(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        abbasidColumn.setCellValueFactory(cellDataFeatures -> "50%");
    }
}
