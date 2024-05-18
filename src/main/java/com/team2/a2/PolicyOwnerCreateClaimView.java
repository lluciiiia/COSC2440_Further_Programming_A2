package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertClaimRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class PolicyOwnerCreateClaimView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button createClaimButton;
    @FXML
    private DatePicker claimDate;
    @FXML
    private DatePicker examDate;
    @FXML
    private TextField claimAmount;

    private AccountController accountController = new AccountController();
    private Account account;
    private ClaimController claimController = new ClaimController();
    private Customer customer1;


    public void initData(Customer customer, PolicyOwner policyOwner) {
        int policyOwnerId = policyOwner.getAccountId();
        account = accountController.getAccountByID(policyOwnerId);
        customer1 = customer;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyOwnerPage.fxml"));
                Parent root = loader.load();
                PolicyOwnerView policyOwnerView = loader.getController();
                policyOwnerView.initData(account);

                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createClaimButton.setOnAction(event -> createClaim());
    }

    private void createClaim() {
        LocalDate claimLocalDate = claimDate.getValue();
        LocalDate examLocalDate = examDate.getValue();
        String amountText = claimAmount.getText();

        if (claimLocalDate == null || examLocalDate == null || amountText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        try {
            Double amount = Double.parseDouble(amountText);
            java.sql.Date claimDate = java.sql.Date.valueOf(claimLocalDate);
            java.sql.Date examDate = java.sql.Date.valueOf(examLocalDate);

            InsertClaimRequest insertClaimRequest = new InsertClaimRequest(customer1.getId(), claimDate, examDate, amount);
            claimController.createClaim(insertClaimRequest, account.getId());

            showAlert(Alert.AlertType.INFORMATION, "Claim Created!", "Claim created successfully");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid amount");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}