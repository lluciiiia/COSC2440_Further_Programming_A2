package com.team2.a2.View.Dependent;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
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

public class InformationView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField policyHolderIDField;
    @FXML
    private TextField policyOwnerIDField;

    private AccountController accountController = new AccountController();
    private Account account;

    public void initData(Customer customer, Dependent dependent) {
        nameField.setText("Name: " + customer.getName());
        phoneField.setText("Phone: " + customer.getPhoneNumber());
        emailField.setText("Email: " + customer.getEmail());
        addressField.setText("Address: " + customer.getAddress());
        policyOwnerIDField.setText("Policy owner ID: " + customer.getPolicyOwnerId());
        policyHolderIDField.setText("Policy holder ID: " + dependent.getId());
        int accountID = customer.getAccountId();
        account = accountController.getAccountByID(accountID);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/DependentPage.fxml"));
                Parent root = loader.load();
                View dependentView = loader.getController();
                dependentView.initData(account);

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
