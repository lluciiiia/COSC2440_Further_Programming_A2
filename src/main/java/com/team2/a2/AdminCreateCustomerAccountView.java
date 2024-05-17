package com.team2.a2;

import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertCustomerRequest;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminCreateCustomerAccountView implements Initializable {
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
    private ComboBox<Integer> policyHolder;
    @FXML
    private Button createAccountButton;

    private CustomerController customerController = new CustomerController();
    private PolicyOwnerController policyOwnerController = new PolicyOwnerController();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminViewCustomerPage.fxml")));
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
                updatePolicyHolderComboBox();
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

        createAccountButton.setOnAction(event -> createAccount());
    }

    private void updatePolicyHolderComboBox() {
        List<Customer> policyHolders = customerController.getAllPolicyHolders();
        List<Integer> customerIds = policyHolders.stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
        policyHolder.setItems(FXCollections.observableArrayList(customerIds));
    }

    private void createAccount() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String fullName = fullNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String address = homeAddressTextField.getText();
        CustomerType customerType = customerTypeComboBox.getValue();
        int policyHolderId = policyHolder.getValue() != null ? policyHolder.getValue() : -1;
        Customer customer = customerController.getCustomerByCustomerId(policyHolderId);
        int policyOwnerId = customer.getPolicyOwnerId();
        PolicyOwner policyOwner = policyOwnerController.getPolicyOwnerById(policyOwnerId );
        int policyOwnerAccountId = policyOwner.getAccountId();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || customerType == null) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        InsertCustomerRequest customerRequest;
        if (customerType == CustomerType.DEPENDENT) {
            customerRequest = new InsertCustomerRequest(username, password, policyOwnerAccountId, policyHolderId, fullName, address, phone, email, customerType);
        } else {
            customerRequest = new InsertCustomerRequest(username, password, policyOwnerAccountId, fullName, address, phone, email, customerType);
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
