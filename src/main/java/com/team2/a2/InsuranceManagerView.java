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

public class InsuranceManagerView implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Button ViewCustomerButton;

    @FXML
    private Button ViewSurveyorButton;

    @FXML
    private Button ViewLOCButton;


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

        ViewCustomerButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InsuranceManagerAllCustomerPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewCustomerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewSurveyorButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InsuranceManagerViewSurveyorPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewSurveyorButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewLOCButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InsuranceManagerClaimPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewLOCButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
