package com.team2.a2.View.Policy;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertCustomerRequest;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.stream.Collectors;

public class OwnerCreateAccountView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField homeAddressTextField;
    @FXML
    private ComboBox<CustomerType> customerTypeComboBox;

    @FXML
    private TextField cardTextField;
    @FXML
    private TextField bankNameTextField;
    @FXML
    private TextField accountNumberTextField;
    @FXML
    private DatePicker expiryDate;

    @FXML
    private ComboBox<Integer> policyHolder;

    @FXML
    private Button createAccountButton;

    private AccountController accountController = new AccountController();
    private CustomerController customerController = new CustomerController();
    private Account account;

    public void initData(PolicyOwner policyOwner) {
        int accountID = policyOwner.getAccountId();
        account = accountController.getAccountByID(accountID);
        updatePolicyHolderComboBox(policyOwner.getId());
    }

    private void updatePolicyHolderComboBox(int policyOwnerId) {
        List<Customer> policyHolders = customerController.getAllPolicyHoldersByPolicyOwnerId(policyOwnerId);
        List<Integer> customerIds = policyHolders.stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
        policyHolder.setItems(FXCollections.observableArrayList(customerIds));
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/team2/a2/PolicyOwnerPage.fxml"));
                Parent root = fxmlLoader.load();
                OwnerView policyOwnerView = fxmlLoader.getController();
                policyOwnerView.initData(account);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        customerTypeComboBox.setItems(FXCollections.observableArrayList(CustomerType.values()));
        customerTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == CustomerType.DEPENDENT) {
                policyHolder.setVisible(true);
            } else {
                policyHolder.setVisible(false);
            }
        });

        policyHolder.setVisible(false);

        policyHolder.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object != null ? object.toString() : "";
            }

            @Override
            public Integer fromString(String string) {
                return string != null && !string.isEmpty() ? Integer.valueOf(string) : null;
            }
        });

        createAccountButton.setOnAction(event -> {
            try {
                createAccount();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createAccount() throws Exception {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String fullName = fullNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String address = homeAddressTextField.getText();
        String card = cardTextField.getText();
        String bankName = bankNameTextField.getText();
        String accountNumber = accountNumberTextField.getText();
        LocalDate expiry = expiryDate.getValue();
        java.sql.Date expriryDate = java.sql.Date.valueOf(expiry);
        CustomerType customerType = customerTypeComboBox.getValue();
        int policyOwnerAccountId = account.getId();
        int policyHolderId = policyHolder.getValue() != null ? policyHolder.getValue() : -1;

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || customerType == null) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        InsertCustomerRequest customerRequest;
        if (customerType == CustomerType.DEPENDENT) {
            customerRequest = new InsertCustomerRequest(username, password, policyOwnerAccountId, policyHolderId, fullName,
                    address, phone, email, customerType, card, expriryDate, bankName, accountNumber);
        } else {
            customerRequest = new InsertCustomerRequest(username, password, policyOwnerAccountId, fullName,
                    address, phone, email, customerType, card, expriryDate, bankName, accountNumber);
        }

        customerController.createCustomer(customerRequest);
        showAlert(Alert.AlertType.INFORMATION, "Account Created!", "Account created successfully");

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

