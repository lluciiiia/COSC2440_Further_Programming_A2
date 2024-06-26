package com.team2.a2.View.Admin;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.PolicyOwnerController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.control.*;

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
    private ComboBox<Integer> policyOwner;

    @FXML
    private ComboBox<Integer> policyHolder;

    @FXML
    private TextField cardTextField;
    @FXML
    private TextField bankNameTextField;
    @FXML
    private TextField accountNumberTextField;
    @FXML
    private DatePicker expiryDate;

    @FXML
    private Button createAccountButton;

    private CustomerController customerController = new CustomerController();
    private PolicyOwnerController policyOwnerController = new PolicyOwnerController();

    private Account account1;

    public void initData(Account account) {
        account1 = account;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewCustomerPage.fxml"));
                Parent root = loader.load();
                AdminCustomerView adminCustomerView = (AdminCustomerView) loader.getController();
                adminCustomerView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        policyHolder.setVisible(false);
        policyOwner.setVisible(false);

        customerTypeComboBox.setItems(FXCollections.observableArrayList(CustomerType.values()));
        customerTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == CustomerType.DEPENDENT) {
                updatePolicyHolderComboBox();
                policyHolder.setVisible(true);
                policyOwner.setVisible(false);
            } else if (newVal == CustomerType.POLICY_HOLDER) {
                updatePolicyOwnerComboBox();
                policyHolder.setVisible(false);
                policyOwner.setVisible(true);
            } else {
                policyHolder.setVisible(false);
                policyOwner.setVisible(false);
            }
        });


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

        policyOwner.setConverter(new StringConverter<Integer>() {
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

    private void updatePolicyHolderComboBox() {
        List<Customer> policyHolders = customerController.getAllPolicyHolders();
        List<Integer> customerIds = policyHolders.stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
        policyHolder.setItems(FXCollections.observableArrayList(customerIds));
    }

    private void updatePolicyOwnerComboBox() {
        List<PolicyOwner> policyOwners = policyOwnerController.getAllPolicyOwners();
        List<Integer> policyOwnerIds = policyOwners.stream()
                .map(PolicyOwner::getId)
                .collect(Collectors.toList());
        policyOwner.setItems(FXCollections.observableArrayList(policyOwnerIds));

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

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || customerType == null || bankName.isEmpty() || accountNumber.isEmpty() || expiry == null) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        InsertCustomerRequest customerRequest = null;

        if (customerType == CustomerType.DEPENDENT) {
            Integer policyHolderId = policyHolder.getValue();
            if (policyHolderId == null) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please select a policy holder for a dependent customer.");
                return;
            }

            Customer customer = customerController.getCustomerById(policyHolderId);
            int policyOwnerId = customer.getPolicyOwnerId();
            PolicyOwner policyOwner = policyOwnerController.getPolicyOwnerById(policyOwnerId);
            int policyOwnerAccountId = policyOwner.getAccountId();

            customerRequest = new InsertCustomerRequest(username, password, policyOwnerAccountId, policyHolderId, fullName,
                    address, phone, email, customerType, card, expriryDate, bankName, accountNumber);
            customerController.createCustomer(customerRequest, account1.getId());

        } else if (customerType == CustomerType.POLICY_HOLDER) {
            Integer poOwnerId = policyOwner.getValue();
            PolicyOwner poOwner = policyOwnerController.getPolicyOwnerById(poOwnerId);
            int poOwnerAccountId = poOwner.getAccountId();

            customerRequest = new InsertCustomerRequest(username, password, poOwnerAccountId, fullName,
                    address, phone, email, customerType, card, expriryDate, bankName, accountNumber);
            customerController.createCustomer(customerRequest, account1.getId());
        }

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
