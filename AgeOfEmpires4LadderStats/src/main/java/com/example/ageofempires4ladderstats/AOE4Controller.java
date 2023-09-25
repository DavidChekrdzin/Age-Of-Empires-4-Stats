package com.example.ageofempires4ladderstats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;

public class AOE4Controller implements Initializable {
    @FXML
    private TextField userNameTextBox;
    @FXML
    private ComboBox changeRankComboBox;
    @FXML
    private Label usernameLabel,winLossLabel,winPercentageLabel,totalGamesLabel;
    @FXML
    private ImageView rankImage;
    @FXML
    private Button newMatchButton,CivBasedResultsButton,mapBasedResultsButton,changeUsernameButton;
    private SceneController sceneController = new SceneController();
    private int wins = 0;
    private int losses = 0;
    private String[] ranks = {"Bronze","Silver","Gold","Platinum","Diamond","Conqueror"};
    private String username;
    private File usernameFilePath = new File(String.valueOf("name.txt"));
    private File rankFilePath = new File(String.valueOf("rank.txt"));

    private int rankNum;

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
    public void changeRank(ActionEvent event) throws FileNotFoundException {
        int i = changeRankComboBox.getSelectionModel().getSelectedIndex();

        if(changeRankComboBox.getSelectionModel().getSelectedIndex() == i){
            InputStream stream = new FileInputStream("pics/Ranks/" + i + ".jpg");
            Image image = new Image(stream);
            rankImage.setImage(image);
            rankNum = i;
            PrintWriter writer = new PrintWriter(rankFilePath);
            writer.print(rankNum);
            writer.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDatabase();

        InputStream buttonPicStream = null;
        try {
            buttonPicStream = new FileInputStream("pics/UI/EDIT.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image buttonImg = new Image(buttonPicStream);
        ImageView buttonImgView = new ImageView(buttonImg);
        buttonImgView.setPreserveRatio(true);
        buttonImgView.setFitWidth(38);
        changeUsernameButton.setGraphic(buttonImgView);



        changeRankComboBox.getItems().addAll(ranks);

        String rank = null;
        try {
            rank = new String(Files.readAllBytes(Paths.get(rankFilePath.toURI())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        rankNum = Integer.parseInt(rank);
        changeRankComboBox.getSelectionModel().select(ranks[rankNum]);
        InputStream stream = null;
        try {
            stream = new FileInputStream("pics/Ranks/" + rankNum + ".jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);
        rankImage.setImage(image);

        String text = null;
        try {
            text = new String(Files.readAllBytes(Paths.get(usernameFilePath.toURI())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userNameTextBox.setText(text);
    }
    public void changeName(ActionEvent event) throws FileNotFoundException {
        userNameTextBox.setEditable(true);
        userNameTextBox.requestFocus();
    }
    public void enterPressed(KeyEvent keyEvent) throws FileNotFoundException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            userNameTextBox.setEditable(false);
            username = userNameTextBox.getText();

            PrintWriter writer = new PrintWriter(usernameFilePath);
            writer.print(username);
            writer.close();
        }
    }
}