module com.example.ageofempires4ladderstats {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ageofempires4ladderstats to javafx.fxml;
    exports com.example.ageofempires4ladderstats;
}