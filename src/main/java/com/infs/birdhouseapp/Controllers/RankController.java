package com.infs.birdhouseapp.Controllers;


import com.infs.birdhouseapp.Application;
import com.infs.birdhouseapp.JsonClasses.RankType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import static com.infs.birdhouseapp.JsonClasses.CommonFunctions.readRank;

public class RankController implements Initializable {
    @FXML
    public ListView<String> listView;
    public ScrollPane window;
    public Button BackButton;
    public StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        window.setBackground(Background.EMPTY);
        listView.prefWidthProperty().bind(window.widthProperty());
        StackPane.setAlignment(BackButton, Pos.TOP_LEFT);
        BackButton.setTranslateY(-80);
        BackButton.setTranslateX(-80);
        RankType rankType;
        System.out.println("加載頁面");
        try {
            rankType = readRank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<RankType.UserType> users = rankType.getRank();

        users.sort(Comparator.comparingInt(o -> -o.getScore()));
        int rank = 0;
        int currentScore = 9999;
        ObservableList<String> scores = FXCollections.observableArrayList();
        for (RankType.UserType u: users) {
            if ( u.getScore() < currentScore) {
                currentScore = u.getScore();
                rank ++;
            }
            String content = rank +". " + u.getName() + "/ Score: " + u.getScore();
            scores.add(content);
        }
        listView.setItems(scores);
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/main-view.fxml"));
        Stage stage = (Stage) window.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }
}