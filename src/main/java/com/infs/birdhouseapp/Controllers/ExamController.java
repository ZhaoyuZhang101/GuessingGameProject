package com.infs.birdhouseapp.Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infs.birdhouseapp.Application;
import com.infs.birdhouseapp.JsonClasses.QuizType;
import com.infs.birdhouseapp.JsonClasses.RankType;
import com.infs.birdhouseapp.TimeLineController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.infs.birdhouseapp.JsonClasses.CommonFunctions.readQuiz;
import static com.infs.birdhouseapp.JsonClasses.CommonFunctions.readRank;

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
    private int quizIndex = -1;
    private int ShowTitleCount = 0;
    private int ShowTitleCountDefault = 90;

    private int currentScore = 0;

    private boolean isPlay = false;

    private QuizType quizContent;


    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/main-view.fxml"));
        Stage stage = (Stage) VideoPage.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
        timeLineController.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            quizContent = readQuiz();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        timeLineController = new TimeLineController() {
            @Override
            public void tick() throws IOException {
                super.tick();
                ticks();
            }
        };
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
        String fileName = quizContent.getQuiz().get(quizIndex).getVideo();
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
        question.setText((quizContent.getQuiz().get(quizIndex)).getQuestion());
        question.setWrapText(true);
        String[] answers = (quizContent.getQuiz().get(quizIndex)).getAnswer();
        String correctAnswer = (quizContent.getQuiz().get(quizIndex)).getCorrectAnswer();
        for (Object o : answers) {
            Button button = new Button(o.toString());
            answersSpace.getChildren().add(button);
            button.setId("AnswerButton");
            button.setOnAction(event -> {

                if (quizIndex < quizContent.getQuiz().size()) {
                    if (Objects.equals(o.toString(), correctAnswer)) {
                        System.out.println("正确");
                        currentScore ++;
                        Score.setText("Score: "+currentScore);
                    }
                    if (quizIndex < quizContent.getQuiz().size()-1) {
                        showTitle();
                    } else {
                        try {
                            ShowFinal();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
    }

    public void ShowFinal() throws IOException {
        answersSpace.getChildren().clear();
        QuestionsPart.setVisible(false);
        isPlay = false;
        Label label = new Label("Well Done! You have finished All questions\nyour current Score is "+ currentScore + "/" + quizContent.getQuiz().size());
        label.setWrapText(true);
        label.setScaleX(3);
        label.setScaleY(3);
        label.setPrefSize(VideoPage.getPrefWidth(), VideoPage.getPrefHeight());
        VideoPage.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);

        RankType.UserType user = new RankType.UserType(Application.currentUserName, currentScore, Application.currentUserID );


        RankType ranktype = readRank();
        List<RankType.UserType> userTypeList = ranktype.getRank();
        userTypeList.add(user);
        try {
            Gson gson = new GsonBuilder().create();
            Writer writer = new FileWriter(Objects.requireNonNull(Objects.requireNonNull(Application.class.getResource("QuizJsons/Rank.json")).getPath()));
            gson.toJson(ranktype, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("problem happens when writing data to json file");
        }
    }

    public void showTitle() {
        quizIndex++;
        QuizType.QuestionType questionType = quizContent.getQuiz().get(quizIndex);
        isPlay = false;
        // set title of quiz
        System.out.println(questionType.getName());
        title.setText(questionType.getName());
        ShowTitleCount = ShowTitleCountDefault;
        answersSpace.getChildren().clear();
        QuestionsPart.setVisible(false);
    }

}
