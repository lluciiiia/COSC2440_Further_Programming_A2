package com.team2.a2.View.Dependent;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
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

public class DependentInsuranceCardView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TextField customerID;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField ExpiryDate;
    @FXML
    private TextField BankName;
    @FXML
    private TextField BankAccountNumber;

    private AccountController accountController = new AccountController();
    private Account account;

    public void initData(InsuranceCard insuranceCard, Customer customer) {
        customerID.setText("CustomerID: " + insuranceCard.getCustomerId());
        cardNumber.setText("Card number: " + insuranceCard.getCardNumber());
        ExpiryDate.setText("Expiry date: " + insuranceCard.getExpiryDate());
        BankName.setText("Bank name: " + insuranceCard.getBankName());
        BankAccountNumber.setText("Bank account number: " + insuranceCard.getAccountNumber());
        int accountID = customer.getAccountId();
        account = accountController.getAccountByID(accountID);
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/DependentPage.fxml"));
                Parent root = loader.load();
                DependentView dependentView = loader.getController();
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
