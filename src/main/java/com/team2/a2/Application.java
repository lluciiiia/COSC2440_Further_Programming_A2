package com.team2.a2;

/**
 * @author <Team 2>
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Establish database connection
        ConnectionManager.initConnection();

        /// Load the FXML file and create the scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        Scene scene = new Scene(root);

        // Set up the stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
