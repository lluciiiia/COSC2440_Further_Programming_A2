package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
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

public class PolicyHolderInformationView implements Initializable {
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
    private TextField policyOwnerIDField;

    private AccountController accountController = new AccountController();
    private Account account;

    public void initData(Customer customer) {
        nameField.setText("Name: " + customer.getName());
        phoneField.setText("Phone: " + customer.getPhoneNumber());
        emailField.setText("Email: " + customer.getEmail());
        addressField.setText("Address: " + customer.getAddress());
        policyOwnerIDField.setText("Policy owner ID: " + customer.getPolicyOwnerId());
        int accountID = customer.getAccountId();
        account = accountController.getAccountByID(accountID);
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderPage.fxml"));
                Parent root = loader.load();
                PolicyHolderView policyHolderView = loader.getController();
                policyHolderView.initData(account);

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
