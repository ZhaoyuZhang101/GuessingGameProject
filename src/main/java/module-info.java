module com.project.guessingbirdgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.json;


    opens com.project.guessingbirdgame to javafx.fxml;
    exports com.project.guessingbirdgame;
    exports com.project.guessingbirdgame.Controllers;
    opens com.project.guessingbirdgame.Controllers to javafx.fxml;
}