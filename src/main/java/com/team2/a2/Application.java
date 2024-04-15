package com.team2.a2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Establish database connection
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnection();
            System.out.println("Connected to the database!");
        } catch (SQLException | IOException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing database connection: " + e.getMessage());
                }
            }
        }

        // Load the FXML file and create the scene
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Set up the stage
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
