package com.project.guessingbirdgame.Controllers;

import com.project.guessingbirdgame.Application;
import com.project.guessingbirdgame.TimeLineController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class ExamController implements Initializable {
    public StackPane VideoPage;
    public Button BackButton;
    public VBox QuestionsPart;
    public Label question;
    public HBox answersSpace;
    public Label Score;
    private TimeLineController timeLineController;
    private Label title;
    @FXML
    private MediaView mediaView;
    private JSONArray Quizs;
    private int quizIndex = -1;
    private int ShowTitleCount = 0;
    private int ShowTitleCountDefault = 90;

    private int currentScore = 0;

    private boolean isPlay = false;


    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/main-view.fxml"));
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
        // read quiz information from json.
        Quizs = quizJson.getJSONArray("Quiz");
        // set title of quiz
        title = new Label();
        title.scaleXProperty().bind(VideoPage.widthProperty().multiply(0.01));
        title.scaleYProperty().bind(VideoPage.widthProperty().multiply(0.01));
        Score.setScaleX(3);
        Score.setScaleY(3);
        question.setScaleX(1.2);
        question.setScaleY(1.2);

        VideoPage.getChildren().add(title);
        // adjust the position of button and title
        StackPane.setAlignment(title, Pos.CENTER);
        StackPane.setAlignment(BackButton, Pos.TOP_LEFT);
        StackPane.setAlignment(Score, Pos.TOP_CENTER);
        Score.setTranslateY(10);


        //adjust the media style, position and property
        StackPane.setAlignment(mediaView, Pos.CENTER);
        mediaView.fitWidthProperty().bind(VideoPage.widthProperty());

        QuestionsPart.setPadding(new Insets(20, 20, 20, 20));
        QuestionsPart.setMaxSize(700, 400);
        QuestionsPart.setFillWidth(true);
        QuestionsPart.setVisible(false);

        showTitle();
    }
    void ticks() {
        if (ShowTitleCount>=0) {
            ShowTitleCount--;
            if (ShowTitleCount==0) {
                generateVideo();

            } else {
                if (!title.isVisible() && ShowTitleCount > 0) {
                    title.setVisible(true);
                }
            }
        }
    }

    public void generateVideo() {
        isPlay = true;
        title.setVisible(false);
        String fileName = ((JSONObject) Quizs.get(quizIndex)).getString("Video");
        Media media = new Media(String.valueOf(Application.class.getResource("ExamVideos/"+fileName)));
        MediaPlayer player = new MediaPlayer(media);
        player.setOnReady(() -> {
            mediaView.setMediaPlayer(player);
            player.play();
            player.setMute(false);
        });


        mediaView.setOnMouseClicked(event -> {
            EndVideo(player);
        });
        player.setOnEndOfMedia(() -> {
            EndVideo(player);
        });
    }

    public void EndVideo(MediaPlayer player) {
        player.stop();
        mediaView.setMediaPlayer(null);
        isPlay = false;
        ShowQuestion();
    }
    public void ShowQuestion() {
        QuestionsPart.setVisible(true);
        question.setText(((JSONObject) Quizs.get(quizIndex)).getString("Question"));
        question.setWrapText(true);
        JSONArray answers = ((JSONObject) Quizs.get(quizIndex)).getJSONArray("Answer");
        String correctAnswer = ((JSONObject) Quizs.get(quizIndex)).getString("CorrectAnswer");
        for (Object o : answers) {
            Button button = new Button(o.toString());
            answersSpace.getChildren().add(button);
            button.setId("AnswerButton");
            button.setOnAction(event -> {

                if (quizIndex < Quizs.length()) {
                    if (Objects.equals(o.toString(), correctAnswer)) {
                        System.out.println("正确");
                        currentScore ++;
                        Score.setText("Score: "+currentScore);
                    }
                    if (quizIndex < Quizs.length()-1) {
                        showTitle();
                    } else {
                        ShowFinal();
                    }
                }
            });
        }
    }

    public void ShowFinal() {
        answersSpace.getChildren().clear();
        QuestionsPart.setVisible(false);
        isPlay = false;
        Label label = new Label("Well Done! You have finished All questions\nyour current Score is "+ currentScore + "/" + Quizs.length());
        label.setWrapText(true);
        label.setScaleX(3);
        label.setScaleY(3);
        label.setPrefSize(VideoPage.getPrefWidth(), VideoPage.getPrefHeight());
        VideoPage.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);
    }

    public void showTitle() {
        quizIndex++;
        JSONObject quiz = (JSONObject) Quizs.get(quizIndex);
        isPlay = false;
        // set title of quiz
        System.out.println(quiz.getString("Name"));
        title.setText(quiz.getString("Name"));
        ShowTitleCount = ShowTitleCountDefault;
        answersSpace.getChildren().clear();
        QuestionsPart.setVisible(false);
    }

    public void ShowFinalScore() {

    }

    public JSONObject readJson(String address) throws IOException {
        char[] line = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(address));
        int len =input.read(line);
        String text =new String(line,0,len);
        return new JSONObject(text);
    }

}
