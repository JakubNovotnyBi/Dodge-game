module com.example.pong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dodge_game to javafx.fxml;
    exports com.example.dodge_game;
}