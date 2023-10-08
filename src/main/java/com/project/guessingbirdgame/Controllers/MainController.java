package com.project.guessingbirdgame.Controllers;

import com.project.guessingbirdgame.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    public VBox ExitCard;
    public Label ExitText;
    public Label UserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Application.currentUserID);
        System.out.println(Application.currentUserName);
        UserName.setScaleX(2);
        UserName.setScaleY(2);
        UserName.setTranslateY(20);
        setUserName("Hello! " + Application.currentUserName + "(" + Application.currentUserID+ ")");
    }

    public void setUserName(String Content) {
        UserName.setText(Content);
        StackPane.setAlignment(UserName, Pos.TOP_CENTER);
    }

    public void LearningPage(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/learn-view.fxml"));
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }

    public void ExamPage(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/exam-view.fxml"));
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }

    public void RankPage(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/rank-view.fxml"));
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }

    public void ExitPage(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/login-view.fxml"));
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }
}