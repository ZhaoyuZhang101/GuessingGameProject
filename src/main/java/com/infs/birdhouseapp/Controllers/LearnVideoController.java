package com.infs.birdhouseapp.Controllers;


import com.infs.birdhouseapp.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LearnVideoController implements Initializable {
    public VBox VideoPage;
    public Button BackButton;
    public MediaView mediaView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void loadVideo(File file) throws IOException {
        Media media = new Media((new File(Application.RESOURCES_PATH+"/LearnVideos/"+file.getName())).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setAutoPlay(true);
        player.setMute(false);
        player.setOnEndOfMedia(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/learn-view.fxml"));
            Stage stage = (Stage) VideoPage.getScene().getWindow();
            try {
                stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        mediaView.setFitWidth(VideoPage.getWidth());
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/learn-view.fxml"));
        Stage stage = (Stage) VideoPage.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }
}
