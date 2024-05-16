package com.team2.a2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminPolicyOwnerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button CreatePolicyOwnerAccount;
    @FXML
    private Button ViewAllPolicyOwnerButton;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        CreatePolicyOwnerAccount.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminCreatePolicyOwnerAccountPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) CreatePolicyOwnerAccount.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewAllPolicyOwnerButton.setOnAction(event -> {
            try {
<<<<<<< HEAD
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminSeePOwnerAllCustomerPage.fxml")));
=======
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminViewAllPolicyOwnerPage.fxml")));
>>>>>>> parent of 0ac46fa (Revert "feat: add combo boxes, fix dependent, surveyors, managers, button handler UI page")
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewAllPolicyOwnerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}