package com.team2.a2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class LoginPageView {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private HashMap<String, String> userCredentials;

    public LoginPageView() {
        userCredentials = new HashMap<>();
        userCredentials.put("s1", "1");
        userCredentials.put("s2", "2");
        userCredentials.put("s3", "3");
    }

    @FXML
    protected void initialize() {
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (isValidCredentials(username, password)) {
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PolicyOwnerPage.fxml")));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        if (userCredentials.containsKey(username)) {
            return userCredentials.get(username).equals(password);
        }
        return false;
    }
}