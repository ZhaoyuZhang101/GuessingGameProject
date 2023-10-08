package com.project.guessingbirdgame.Controllers;

import com.project.guessingbirdgame.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

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

        JSONObject rankJson;
        try {
            rankJson = readJson("src/main/resources/com/project/guessingbirdgame/QuizJsons/Rank.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<JSONObject> users = new ArrayList<>();
        JSONArray usersInfo = rankJson.getJSONArray("Rank");
        for (Object o: usersInfo) {
            JSONObject oj = new JSONObject(o.toString());
            users.add(oj);
        }
        users.sort(Comparator.comparingInt(o -> -o.getInt("Score")));
        int rank = 0;
        int currentScore = 9999;
        ObservableList<String> scores = FXCollections.observableArrayList();
        for (JSONObject jo: users) {
            if ( jo.getInt("Score") < currentScore) {
                currentScore = jo.getInt("Score");
                rank ++;
            }
            String content = rank +". " + jo.getString("name") + "/ Score: " + jo.getInt("Score");
            scores.add(content);
        }
        listView.setItems(scores);
    }

    public JSONObject readJson(String address) throws IOException {
        char[] line = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(address));
        int len =input.read(line);
        String text =new String(line,0,len);
        return new JSONObject(text);
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/main-view.fxml"));
        Stage stage = (Stage) window.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }
}
