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
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CivMapWinRate implements Initializable {

    public TableView tableViewCivVsCiv;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView enemyImage;
    @FXML
    private Button backButton;
    @FXML
    private ImageView mapImage;
    @FXML
    private TableView<MatchObjModel> tableViewCivMap = new TableView<MatchObjModel>();

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
    private String[]civs = {"ABBASID DYNASTY","CHINESE","DELHI SULTANATE","ENGLISH","FRENCH","HOLY ROMAN EMPIRE","MALIANS","MONGOLS","OTTOMANS","RUS",};
    private String[]maps = {"BOULDER BAY","DRY ARABIA","GOLDEN HEIGHTS","HIDEOUT","HIGH VIEW","HILL AND DALE","MARSHLAND","THE PIT","VOLCANIC ISLAND"};
    private ArrayList<String> winMapPer = new ArrayList<>();
    public void switchToMainGUIScene(ActionEvent event) throws IOException {
        sceneController.switchToMainGUIScene(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("aoe4db.db");
        String jdbcUrl = "jdbc:sqlite:" + file.getAbsolutePath();



        for(int i=0;i<maps.length;i++){
            String map = maps[i];
            for(int j=0;j<civs.length;j++){
                String civ = civs[j];
                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl);

                    String sqlWins = "SELECT * FROM MATCH_HISTORY WHERE PLAYERCIV = \""+ civ +"\" AND MAP = \"" + map + "\" AND WIN = 0;";

                    Statement statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery(sqlWins);

                    double rowCountWins=0;
                    while (resultSet.next()){
                        rowCountWins++;
                    }

                    String sqlLosses = "SELECT * FROM MATCH_HISTORY WHERE PLAYERCIV = \""+ civ +"\" AND MAP = \"" + map + "\" AND WIN = 1;";

                    Statement statement2 = connection.createStatement();

                    ResultSet resultSet2 = statement2.executeQuery(sqlLosses);

                    double rowCountLosses=0;

                    while (resultSet2.next()){
                        rowCountLosses++;
                    }

                    double winPercentageOnMapByCiv;
                    String winPercentageText;

                    if(rowCountWins==0){
                        winPercentageText = "0%";
                    }else{

                        winPercentageOnMapByCiv = (rowCountWins/(rowCountWins+rowCountLosses))*100;

                        winPercentageText = (Double.toString(winPercentageOnMapByCiv) + "%");
                    }

                    winMapPer.add(winPercentageText);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        final ObservableList<MatchObjModel> data = FXCollections.observableArrayList(
                        new MatchObjModel(winMapPer.get(0), winMapPer.get(1), winMapPer.get(2),winMapPer.get(3),winMapPer.get(4),winMapPer.get(5),winMapPer.get(6),winMapPer.get(7),winMapPer.get(8),winMapPer.get(9)),
                        new MatchObjModel(winMapPer.get(10), winMapPer.get(11), winMapPer.get(12),winMapPer.get(13),winMapPer.get(14),winMapPer.get(15),winMapPer.get(16),winMapPer.get(17),winMapPer.get(18),winMapPer.get(19)),
                        new MatchObjModel(winMapPer.get(20), winMapPer.get(21), winMapPer.get(22),winMapPer.get(23),winMapPer.get(24),winMapPer.get(25),winMapPer.get(26),winMapPer.get(27),winMapPer.get(28),winMapPer.get(29)),
                        new MatchObjModel(winMapPer.get(30), winMapPer.get(31), winMapPer.get(32),winMapPer.get(33),winMapPer.get(34),winMapPer.get(35),winMapPer.get(36),winMapPer.get(37),winMapPer.get(38),winMapPer.get(39)),
                        new MatchObjModel(winMapPer.get(40), winMapPer.get(41), winMapPer.get(42),winMapPer.get(43),winMapPer.get(44),winMapPer.get(45),winMapPer.get(46),winMapPer.get(47),winMapPer.get(48),winMapPer.get(49)),
                        new MatchObjModel(winMapPer.get(50), winMapPer.get(51), winMapPer.get(52),winMapPer.get(53),winMapPer.get(54),winMapPer.get(55),winMapPer.get(56),winMapPer.get(57),winMapPer.get(58),winMapPer.get(59)),
                        new MatchObjModel(winMapPer.get(60), winMapPer.get(61), winMapPer.get(62),winMapPer.get(63),winMapPer.get(64),winMapPer.get(65),winMapPer.get(66),winMapPer.get(67),winMapPer.get(68),winMapPer.get(69)),
                        new MatchObjModel(winMapPer.get(70), winMapPer.get(71), winMapPer.get(72),winMapPer.get(73),winMapPer.get(74),winMapPer.get(75),winMapPer.get(76),winMapPer.get(77),winMapPer.get(78),winMapPer.get(79)),
                        new MatchObjModel(winMapPer.get(80), winMapPer.get(81), winMapPer.get(82),winMapPer.get(83),winMapPer.get(84),winMapPer.get(85),winMapPer.get(86),winMapPer.get(87),winMapPer.get(88),winMapPer.get(89))
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

        tableViewCivMap.setItems(data);
    }

}
