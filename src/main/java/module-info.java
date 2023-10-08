module com.project.guessingbirdgame {
    requires org.json;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;


    opens com.project.guessingbirdgame to javafx.fxml;
    exports com.project.guessingbirdgame;
    exports com.project.guessingbirdgame.Controllers;
    opens com.project.guessingbirdgame.Controllers to javafx.fxml;
}