package com.infs.birdhouseapp.Controllers;


import com.infs.birdhouseapp.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LearnController implements Initializable {

    public FlowPane Content;
    public ScrollPane Window;
    public Button BackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Content.getChildren().clear();
        Content.setHgap(30);
        Content.setVgap(30);
        Window.setBackground(Background.EMPTY);
        Content.prefWidthProperty().bind(Window.widthProperty());
        File learnVideoFolder = new File(Application.RESOURCES_PATH + "/LearnVideos");
        File[] files = learnVideoFolder.listFiles();
        assert files != null;
        for (File f : files) {
            VBox VideoSpace = GenerateVideoShow(f);
            Content.getChildren().add(VideoSpace);
        }

    }

    public VBox GenerateVideoShow(File file) {

        VBox VideoSpace = new VBox();

        MediaView mediaView = getMediaView(file);
        VideoSpace.getChildren().add(mediaView);
        Label label = new Label(file.getName());
        label.setTextFill(Color.WHITE);
        VideoSpace.getChildren().add(label);
        System.out.println(mediaView.getMediaPlayer());
        return VideoSpace;
    }

    private MediaView getMediaView(File file) {

        Media media = new Media((new File(Application.RESOURCES_PATH + "/LearnVideos/"+ file.getName())).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(false);
        MediaView mediaView = new MediaView();

        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setOnMouseClicked(event -> {
            System.out.println("点击");
            VideoShow(file);
        });
        mediaView.setFitWidth(200);
        mediaView.setFitHeight(100);
        mediaView.setVisible(true);
        return mediaView;
    }

    public void VideoShow(File file) {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/learn-video-view.fxml"));
        Stage stage = (Stage) Window.getScene().getWindow();
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
            LearnVideoController controller = fxmlLoader.getController();
            controller.loadVideo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/main-view.fxml"));
        Stage stage = (Stage) Window.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }
}
