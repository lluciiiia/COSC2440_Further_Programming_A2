package com.team2.a2.View.Policy;

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
import java.util.Objects;
import java.util.ResourceBundle;

public class OwnerCustomerCardView implements Initializable {

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

    private Account account1;

    public void initData(InsuranceCard insuranceCard, Account account) {
        customerID.setText("CustomerID: " + insuranceCard.getCustomerId());
        cardNumber.setText("Card number: " + insuranceCard.getCardNumber());
        ExpiryDate.setText("Expiry date: " + insuranceCard.getExpiryDate());
        BankName.setText("Bank name: " + insuranceCard.getBankName());
        BankAccountNumber.setText("Bank account number: " + insuranceCard.getAccountNumber());
        account1 = account;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/PolicyHolderPage.fxml"));
                Parent root = loader.load();
                HolderView policyHolderView = loader.getController();
                policyHolderView.initData(account1);

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