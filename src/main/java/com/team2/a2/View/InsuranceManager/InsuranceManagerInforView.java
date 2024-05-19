package com.team2.a2.View.InsuranceManager;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
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
import java.util.ResourceBundle;

public class InsuranceManagerInforView implements Initializable {
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

    private AccountController accountController = new AccountController();
    private Account account;

    public void initData(InsuranceManager insuranceManager) {
        name.setText("Name: " + insuranceManager.getName());
        companyName.setText("Company name: " + insuranceManager.getCompanyName());
        phone.setText("Phone number: " + insuranceManager.getPhoneNumber());
        email.setText("Email: " + insuranceManager.getEmail());
        companyAddress.setText("Company address: " + insuranceManager.getCompanyAddress());

        int accountID = insuranceManager.getAccountId();
        account = accountController.getAccountByID(accountID);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceManagerPage.fxml"));
                Parent root = loader.load();
                InsuranceManagerView insuranceManagerView = loader.getController();
                insuranceManagerView.initData(account);

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
