package com.infs.birdhouseapp.Controllers;


import com.infs.birdhouseapp.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public VBox hBox;
    public Label Name;
    public TextField NameInput;
    public Label StudentID;
    public TextField StudentIDInput;
    public Button ConfirmButton;

    public void Enter(ActionEvent actionEvent) throws IOException {
        if (Objects.equals(StudentIDInput.getText(), "") || Objects.equals(NameInput.getText(), "")) {
            if (Objects.equals(StudentIDInput.getText(), "")) {
                StudentIDInput.setPromptText("Please fill the StudentID!");
            }
            if (Objects.equals(NameInput.getText(), "")) {
                NameInput.setPromptText("Please fill your name!");
            }
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxmls/main-view.fxml"));
        Application.currentUserID = StudentIDInput.getText();
        Application.currentUserName = NameInput.getText();
        Stage stage = (Stage) hBox.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load(), 1080, 720));

    }
}
