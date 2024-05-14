package com.team2.a2;

import com.team2.a2.Model.User.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InsuranceSurveyorView implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private Button ViewInsuranceSurveyorButton;
    @FXML
    private Button ViewInsuranceSurveyorCustomerButton;
    @FXML
    private Button ViewInsuranceSurveyorClaimButton;
    @FXML
    private TextField accountID;


    public void initData(Account account) {
        accountID.setText(String.valueOf(account.getId()));
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ViewInsuranceSurveyorButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InsuranceSurveyorInfoPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewInsuranceSurveyorButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewInsuranceSurveyorCustomerButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InsuranceSurveyorAllCustomerPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewInsuranceSurveyorCustomerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewInsuranceSurveyorClaimButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InsuranceSurveyorClaimPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewInsuranceSurveyorClaimButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
