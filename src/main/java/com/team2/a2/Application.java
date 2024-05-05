package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Request.LoginRequest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Establish database connection
        ConnectionManager.initConnection();

        // Load the FXML file and create the scene
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        AccountController accountController = new AccountController();
        accountController.login(new LoginRequest("s3939114@rmit.edu.vn", "12345"));

        // Set up the stage
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
