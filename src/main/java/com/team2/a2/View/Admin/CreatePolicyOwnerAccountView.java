package com.team2.a2.View.Admin;

import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreatePolicyOwnerAccountView implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField fullNameTextField;

    @FXML
    private Button createAccountButton;

    private PolicyOwnerController policyOwnerController = new PolicyOwnerController();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/team2/a2/AdminViewPolicyOwnerPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createAccountButton.setOnAction(event -> createAccount());
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void createAccount() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String fullName = fullNameTextField.getText();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        InsertPolicyOwnerRequest insertPolicyOwnerRequest;
        insertPolicyOwnerRequest = new InsertPolicyOwnerRequest(username, password, fullName);
        policyOwnerController.createPolicyOwner(insertPolicyOwnerRequest);
        showAlert(Alert.AlertType.INFORMATION, "Account Created!", "Account created successfully");
    }
}