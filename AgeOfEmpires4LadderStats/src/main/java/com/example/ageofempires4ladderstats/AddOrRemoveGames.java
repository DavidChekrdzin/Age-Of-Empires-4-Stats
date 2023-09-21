package com.example.ageofempires4ladderstats;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddOrRemoveGames implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label mapPickLabel,yourCivLabel,enemyCivLabel,resultLabel;
    @FXML
    private ComboBox mapPickComboBox,yourCivComboBox,enemyCivComboBox,resultComboBox;
    @FXML
    private ImageView playerImage,enemyImage,mapImage,swordsImage;
    @FXML
    private Button submitMatchButton,backButton;
    private SceneController sceneController = new SceneController();
    private String[]civs = {"ABBASID DYNASTY","CHINESE","DELHI SULTANATE","ENGLISH","FRENCH","HOLY ROMAN EMPIRE","MALIANS","MONGOLS","OTTOMANS","RUS",};
    private String[]maps = {"BOULDER BAY","DRY ARABIA","GOLDEN HEIGHTS","HIDEOUT","HIGH VIEW","HILL AND DALE","MARSHLAND","THE PIT","VOLCANIC ISLAND"};
    private String[]result = {"WIN","LOSS"};

    public void switchToMainGUIScene(ActionEvent event) throws IOException {
        sceneController.switchToMainGUIScene(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        yourCivComboBox.getItems().addAll(civs);
        yourCivComboBox.getSelectionModel().select(civs[5]);

        enemyCivComboBox.getItems().addAll(civs);
        enemyCivComboBox.getSelectionModel().select(civs[7]);

        mapPickComboBox.getItems().addAll(maps);
        mapPickComboBox.getSelectionModel().select(maps[0]);

        resultComboBox.getItems().addAll(result);
        resultComboBox.getSelectionModel().select(result[0]);
    }
    public void changeYourCiv() throws FileNotFoundException {
        int i = yourCivComboBox.getSelectionModel().getSelectedIndex();

        if(yourCivComboBox.getSelectionModel().getSelectedIndex() == i){
            InputStream stream = new FileInputStream("pics/Civs/" + i +".png");
            Image image = new Image(stream);
            playerImage.setImage(image);
        }
    }
    public void changeEnemyCiv() throws FileNotFoundException {
        int i = enemyCivComboBox.getSelectionModel().getSelectedIndex();

        if(enemyCivComboBox.getSelectionModel().getSelectedIndex() == i){
            InputStream stream = new FileInputStream("pics/Civs/" + i +".png");
            Image image = new Image(stream);
            enemyImage.setImage(image);
        }
    }
    public void changeMap() throws FileNotFoundException {
        int i = mapPickComboBox.getSelectionModel().getSelectedIndex();

        if (mapPickComboBox.getSelectionModel().getSelectedIndex() == i) {
            InputStream stream = new FileInputStream("pics/Maps/" + i + ".png");
            Image image = new Image(stream);
            mapImage.setImage(image);
        }
    }
    public void changeWin(){
        if (resultComboBox.getSelectionModel().getSelectedIndex() == 0) {
        }
    }
    public void submitMatch(ActionEvent event) throws IOException{
        File databaseLocation = new File("aoe4db.db");
        String jdbcUrl = "jdbc:sqlite:" + databaseLocation.getAbsolutePath();

        String yourCivText = yourCivComboBox.getSelectionModel().getSelectedItem().toString();
        String enemyCivText = enemyCivComboBox.getSelectionModel().getSelectedItem().toString();
        String mapText = mapPickComboBox.getSelectionModel().getSelectedItem().toString();
        int matchResult = resultComboBox.getSelectionModel().getSelectedIndex();

        System.out.println(yourCivText);
        System.out.println(enemyCivText);
        System.out.println(mapText);
        System.out.println(matchResult);

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl);
            String sql = "INSERT INTO MATCH_HISTORY(PLAYERCIV,ENEMYCIV,MAP,WIN) values('"+ yourCivText + "','"+ enemyCivText +"','"+ mapText +"'," + matchResult +");";

            Statement statement = connection.createStatement();

            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sceneController.switchToMainGUIScene(event);
    }
}
