package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
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

public class InsuranceSurveyorInfoView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TextField name;
    @FXML
    private TextField companyName;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField companyAddress;
    @FXML
    private TextField policyOwnerID;

    private AccountController accountController = new AccountController();
    private Account account;

    public void initData(InsuranceSurveyor insuranceSurveyor) {
        name.setText("Name: " + insuranceSurveyor.getName());
        companyName.setText("Company name: " + insuranceSurveyor.getCompanyName());
        phone.setText("Phone number: " + insuranceSurveyor.getPhoneNumber());
        email.setText("Email: " + insuranceSurveyor.getEmail());
        companyAddress.setText("Company address: " + insuranceSurveyor.getCompanyAddress());
        policyOwnerID.setText("Policy owner ID: " + insuranceSurveyor.getInsuranceManagerId());

        int accountID = insuranceSurveyor.getAccountId();
        account = accountController.getAccountByID(accountID);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InsuranceSurveyorPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorView insuranceSurveyorView = loader.getController();
                insuranceSurveyorView.initData(account);

                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
