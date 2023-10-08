package com.project.guessingbirdgame.Controllers;

import com.project.guessingbirdgame.HelloApplication;
import com.project.guessingbirdgame.TimeLineController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class ExamController implements Initializable {
    public StackPane VideoPage;
    public Button BackButton;
    private TimeLineController timeLineController;
    private Label title;
    private MediaView mediaView = new MediaView();
    private JSONArray Quizs;
    private int quizIndex = 0;
    private int ShowTitleCount = 0;
    private int ShowTitleCountDefault = 90;

    private StackPane QuetionsPart = new StackPane();

    private boolean isPlay = false;


    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/main-view.fxml"));
        Stage stage = (Stage) VideoPage.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
        timeLineController.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         timeLineController = new TimeLineController() {
            @Override
            public void tick() throws IOException {
                super.tick();
                ticks();
            }
        };
        JSONObject quizJson;
        try {
            quizJson = readJson("src/main/resources/com/project/guessingbirdgame/QuizJsons/Quiz.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Quizs = quizJson.getJSONArray("Quiz");
        JSONObject quiz = (JSONObject) Quizs.get(quizIndex);
        title = new Label(quiz.getString("Name"));
        title.scaleXProperty().bind(VideoPage.widthProperty().multiply(0.01));
        title.scaleYProperty().bind(VideoPage.widthProperty().multiply(0.01));
        VideoPage.getChildren().add(title);
        StackPane.setAlignment(title, Pos.CENTER);
        StackPane.setAlignment(BackButton, Pos.TOP_LEFT);
        ShowTitleCount = ShowTitleCountDefault;
        VideoPage.getChildren().add(mediaView);
        StackPane.setAlignment(mediaView, Pos.CENTER);
        mediaView.setFitWidth(VideoPage.getWidth());
        mediaView.setFitHeight(VideoPage.getHeight());
        VideoPage.getChildren().add(QuetionsPart);
        QuetionsPart.getStylesheets().add(String.valueOf(HelloApplication.class.getResource("fxmls/css/videoStyle.css")));
        QuetionsPart.setId("QuestionPart");
        StackPane.setAlignment(QuetionsPart, Pos.CENTER);
        isPlay = false;

    }
    void ticks() {
        if (ShowTitleCount>0) {
            ShowTitleCount--;
            title.setVisible(true);
        } else if (ShowTitleCount==0) {
            if (!isPlay) {
                title.setVisible(false);
                generateVideo();
                isPlay = true;
            }
        }
    }

    public void generateVideo() {
        String fileName = ((JSONObject) Quizs.get(quizIndex)).getString("Video");
        Media media = new Media(String.valueOf(HelloApplication.class.getResource("LearnVideos/"+fileName)));
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.play();
        player.setOnEndOfMedia(() -> {
            player.stop();
            mediaView.setMediaPlayer(null);

        });
    }

    public JSONObject readJson(String address) throws IOException {
        char[] line = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(address));
        int len =input.read(line);
        String text =new String(line,0,len);
        return new JSONObject(text);
    }

}
