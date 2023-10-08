package com.project.guessingbirdgame.Controllers;

import com.project.guessingbirdgame.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{

    public HBox hBox;
    public Label learningText;
    public Label examText;
    public Label rankingText;
    public VBox rankingCard;
    public VBox learningCard;
    public VBox examCard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void LearningPage(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/learn-view.fxml"));
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }

    public void ExamPage(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/exam-view.fxml"));
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }

    public void RankPage(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/main-view.fxml"));
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }
}