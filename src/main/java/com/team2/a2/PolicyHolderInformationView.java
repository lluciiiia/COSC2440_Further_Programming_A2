package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Request.UpdatePasswordRequest;
import com.team2.a2.Request.UpdateCustomerRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    @FXML
    private TextField nameEdit;
    @FXML
    private TextField phoneEdit;
    @FXML
    private TextField emailEdit;
    @FXML
    private TextField addressEdit;
    @FXML
    private TextField passwordEdit;
    @FXML
    private Button editButton;

    private CustomerController customerController = new CustomerController();
    private AccountController accountController = new AccountController();
    private Account account;

    Customer customer1;

    public void initData(Customer customer) {
        nameField.setText("Name: " + customer.getName());
        phoneField.setText("Phone: " + customer.getPhoneNumber());
        emailField.setText("Email: " + customer.getEmail());
        addressField.setText("Address: " + customer.getAddress());
        policyOwnerIDField.setText("Policy owner ID: " + customer.getPolicyOwnerId());
        int accountID = customer.getAccountId();
        account = accountController.getAccountByID(accountID);
        customer1 = customer;
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

        editButton.setOnAction(event -> {
            String name = nameEdit.getText();
            String phone = phoneEdit.getText();
            String email = emailEdit.getText();
            String address = addressEdit.getText();
            String password = passwordEdit.getText();
            try {
                editInformation(name, phone, email, address, password);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void editInformation(String name, String phone, String email, String address, String password) throws Exception {
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
            showAlert("Invalid Input", "All fields must be filled.");
            return;
        }
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(account.getId(), password);
        UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest(customer1.getId(),name, address, phone, email);
        Account updatedAccount = accountController.updatePassword(updatePasswordRequest, account.getId());
        Customer updatedCustomer = customerController.updateCustomer(updateCustomerRequest, account.getId());

        if (updatedCustomer != null) {
            customer1 = updatedCustomer;
            nameField.setText("Name: " + updatedCustomer.getName());
            phoneField.setText("Phone: " + updatedCustomer.getPhoneNumber());
            emailField.setText("Email: " + updatedCustomer.getEmail());
            addressField.setText("Address: " + updatedCustomer.getAddress());
            showAlert("Success", "Customer information updated successfully.");
        } else {
            showAlert("Update Failed", "Failed to update customer information.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
