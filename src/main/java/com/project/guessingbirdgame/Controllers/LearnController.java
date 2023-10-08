package com.project.guessingbirdgame.Controllers;

import com.project.guessingbirdgame.HelloApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LearnController implements Initializable {

    public VBox Content;
    public ScrollPane Window;
    public Button BackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Window.setBackground(Background.EMPTY);
        Content.prefWidthProperty().bind(Window.widthProperty());
        File learnVideoFolder = new File(Objects.requireNonNull(HelloApplication.class.getResource("LearnVideos")).getPath());
        File[] files = learnVideoFolder.listFiles();
        int index = 0;
        for (int x=0; x<=10; x++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(20);
            for (int y=0; y<=3; y++) {
                assert files != null;
                if (index < files.length) {
                    VBox VideoSpace = GenerateVideoShow(files[index]);

                    hBox.getChildren().add(VideoSpace);
                    index++;
                }

            }
            Content.getChildren().add(hBox);
        }
    }

    public VBox GenerateVideoShow(File file) {
        VBox VideoSpace = new VBox();

        AnchorPane videoContent = new AnchorPane();
        videoContent.setId("VideoCard");
        Media media = new Media(String.valueOf(HelloApplication.class.getResource("LearnVideos/"+file.getName())));
        videoContent.getStylesheets().add(String.valueOf(HelloApplication.class.getResource("fxmls/css/videoStyle.css")));
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(false);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setId("Video");
        mediaView.fitWidthProperty().bind(videoContent.widthProperty());
        mediaView.setOnMouseClicked(event -> {
            System.out.println("点击");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/learn-video-view.fxml"));
            Stage stage = (Stage) Window.getScene().getWindow();
            try {
                stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
                LearnVideoController controller = fxmlLoader.getController();
                controller.loadVideo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        videoContent.getChildren().add(mediaView);
        VideoSpace.getChildren().add(videoContent);
        Label label = new Label(file.getName());
        VideoSpace.getChildren().add(label);

        return VideoSpace;
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/main-view.fxml"));
        Stage stage = (Stage) Window.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));
    }
}
