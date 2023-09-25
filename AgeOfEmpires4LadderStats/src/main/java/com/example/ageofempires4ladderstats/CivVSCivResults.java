package com.example.ageofempires4ladderstats;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CivVSCivResults implements Initializable {
    public TableView tableViewCivVsCiv;
    public Button backButton;
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
    private String[]civs = {"ABBASID DYNASTY","CHINESE","DELHI SULTANATE","ENGLISH","FRENCH","HOLY ROMAN EMPIRE","MALIANS","MONGOLS","OTTOMANS","RUS",};
    private ArrayList<String> winCivPer = new ArrayList<>();

    private SceneController sceneController = new SceneController();
    public void switchToMainGUIScene(ActionEvent event) throws IOException {
        sceneController.switchToMainGUIScene(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InputStream buttonPicStream = null;
        try {
            buttonPicStream = new FileInputStream("pics/UI/backArrow.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image buttonImg = new Image(buttonPicStream);
        ImageView buttonImgView = new ImageView(buttonImg);
        buttonImgView.setPreserveRatio(true);
        buttonImgView.setFitWidth(34);
        backButton.setGraphic(buttonImgView);

        File file = new File("aoe4db.db");
        String jdbcUrl = "jdbc:sqlite:" + file.getAbsolutePath();



        for(int i=0;i<civs.length;i++){
            String civColumn = civs[i];
            for(int j=0;j<civs.length;j++){
                String civRow = civs[j];
                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl);

                    String sqlWins = "SELECT * FROM MATCH_HISTORY WHERE PLAYERCIV = \""+ civRow +"\" AND ENEMYCIV = \"" + civColumn + "\" AND WIN = 0;";

                    Statement statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery(sqlWins);

                    double rowCountWins=0;
                    while (resultSet.next()){
                        rowCountWins++;
                    }

                    String sqlLosses = "SELECT * FROM MATCH_HISTORY WHERE PLAYERCIV = \""+ civRow +"\" AND ENEMYCIV = \"" + civColumn + "\" AND WIN = 1;";

                    Statement statement2 = connection.createStatement();

                    ResultSet resultSet2 = statement2.executeQuery(sqlLosses);

                    double rowCountLosses=0;

                    while (resultSet2.next()){
                        rowCountLosses++;
                    }

                    double winPercentageCivVsCiv;
                    String winPercentageText;

                    if(rowCountWins==0){
                        winPercentageText = "0%";
                    }else{

                        winPercentageCivVsCiv = (rowCountWins/(rowCountWins+rowCountLosses))*100;

                        winPercentageText = (Double.toString(winPercentageCivVsCiv) + "%");
                    }

                    winCivPer.add(winPercentageText);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        final ObservableList<MatchObjModel> data = FXCollections.observableArrayList(
                new MatchObjModel(winCivPer.get(0), winCivPer.get(1), winCivPer.get(2),winCivPer.get(3),winCivPer.get(4),winCivPer.get(5),winCivPer.get(6),winCivPer.get(7),winCivPer.get(8),winCivPer.get(9)),
                new MatchObjModel(winCivPer.get(10), winCivPer.get(11), winCivPer.get(12),winCivPer.get(13),winCivPer.get(14),winCivPer.get(15),winCivPer.get(16),winCivPer.get(17),winCivPer.get(18),winCivPer.get(19)),
                new MatchObjModel(winCivPer.get(20), winCivPer.get(21), winCivPer.get(22),winCivPer.get(23),winCivPer.get(24),winCivPer.get(25),winCivPer.get(26),winCivPer.get(27),winCivPer.get(28),winCivPer.get(29)),
                new MatchObjModel(winCivPer.get(30), winCivPer.get(31), winCivPer.get(32),winCivPer.get(33),winCivPer.get(34),winCivPer.get(35),winCivPer.get(36),winCivPer.get(37),winCivPer.get(38),winCivPer.get(39)),
                new MatchObjModel(winCivPer.get(40), winCivPer.get(41), winCivPer.get(42),winCivPer.get(43),winCivPer.get(44),winCivPer.get(45),winCivPer.get(46),winCivPer.get(47),winCivPer.get(48),winCivPer.get(49)),
                new MatchObjModel(winCivPer.get(50), winCivPer.get(51), winCivPer.get(52),winCivPer.get(53),winCivPer.get(54),winCivPer.get(55),winCivPer.get(56),winCivPer.get(57),winCivPer.get(58),winCivPer.get(59)),
                new MatchObjModel(winCivPer.get(60), winCivPer.get(61), winCivPer.get(62),winCivPer.get(63),winCivPer.get(64),winCivPer.get(65),winCivPer.get(66),winCivPer.get(67),winCivPer.get(68),winCivPer.get(69)),
                new MatchObjModel(winCivPer.get(70), winCivPer.get(71), winCivPer.get(72),winCivPer.get(73),winCivPer.get(74),winCivPer.get(75),winCivPer.get(76),winCivPer.get(77),winCivPer.get(78),winCivPer.get(79)),
                new MatchObjModel(winCivPer.get(80), winCivPer.get(81), winCivPer.get(82),winCivPer.get(83),winCivPer.get(84),winCivPer.get(85),winCivPer.get(86),winCivPer.get(87),winCivPer.get(88),winCivPer.get(89)),
                new MatchObjModel(winCivPer.get(90), winCivPer.get(91), winCivPer.get(92),winCivPer.get(93),winCivPer.get(94),winCivPer.get(95),winCivPer.get(96),winCivPer.get(97),winCivPer.get(98),winCivPer.get(99))
        );

        abbasidColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("abbasidVs"));
        chineseColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("chineseVs"));
        delhiColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("delhiVs"));
        englishColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("englishVs"));
        frenchColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("frenchVs"));
        hreColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("hreVs"));
        maliansColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("maliansVs"));
        mongolsColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("mongolsVs"));
        ottomansColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("ottomansVs"));
        rusColumn.setCellValueFactory(new PropertyValueFactory<MatchObjModel, String>("rusVs"));

        tableViewCivVsCiv.setItems(data);
    }
}
