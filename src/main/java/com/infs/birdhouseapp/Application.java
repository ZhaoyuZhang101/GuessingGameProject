package com.infs.birdhouseapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infs.birdhouseapp.JsonClasses.QuizType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Application extends javafx.application.Application {
    public static String currentUserID = "";
    public static String currentUserName = "";

    public static String RESOURCES_PATH = System.getProperty("java.home") + "/res";
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Hello!");
        System.out.println(RESOURCES_PATH);
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}