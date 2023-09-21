package com.example.ageofempires4ladderstats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AOE4Controller implements Initializable {

    @FXML
    private Label usernameLabel,winLossLabel,winPercentageLabel,totalGamesLabel;
    @FXML
    private ImageView rankImage;
    @FXML
    private Button newMatchButton,CivBasedResultsButton,mapBasedResultsButton,changeUsernameButton;
    private SceneController sceneController = new SceneController();
    private int wins = 0;
    private int losses = 0;

    public void switchToAddOrRemoveGamesScene(ActionEvent event) throws IOException {
        sceneController.switchToAddOrRemoveGamesScene(event);
    }
    public void switchToCivMapWinRateScene(ActionEvent event) throws IOException {
        sceneController.switchToCivMapWinRateScene(event);
    }
    public void switchToCivVsCivWinRateScene(ActionEvent event) throws IOException {
        sceneController.switchToCivVsCivWinRateScene(event);
    }

    public void connectToDatabase(){
        File file = new File("aoe4db.db");
        String jdbcUrl = "jdbc:sqlite:" + file.getAbsolutePath();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT WIN FROM MATCH_HISTORY;";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                if(resultSet.getInt("WIN") == 0){
                    wins++;
                }
                else {
                    losses++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        winLossLabel.setText(wins + " wins / " + losses + " losses");
        double winsDecimal = wins;
        double lossesDecimal = losses;
        winPercentageLabel.setText(((winsDecimal/(winsDecimal+lossesDecimal))*100) + "%");
        totalGamesLabel.setText(wins + losses + " Total games");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDatabase();
    }
}