package com.team2.a2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    @FXML
    private Button loginButton;

    @FXML
    protected void initialize() {
        loginButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("NewPage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}