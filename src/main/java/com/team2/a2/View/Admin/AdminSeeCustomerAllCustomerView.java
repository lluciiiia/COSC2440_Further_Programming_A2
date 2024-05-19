package com.team2.a2.View.Admin;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Request.UpdatePasswordRequest;
import com.team2.a2.Request.UpdateCustomerRequest;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminSeeCustomerAllCustomerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button viewInsuranceCard;
    @FXML
    private ComboBox<String> customerTypeComboBox;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Integer> customerID;
    @FXML
    private TableColumn<Customer, String> address;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> email;
    @FXML
    private TableColumn<Customer, String> phoneNumber;
    @FXML
    private TableColumn<Customer, CustomerType> type;

    private ObservableList<Customer> originalCustomerList;

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
    @FXML
    private Button deleteButton;

    private AccountController accountController = new AccountController();
    private CustomerController customerController = new CustomerController();
    private InsuranceCardController insuranceCardController = new InsuranceCardController();
    private InsuranceCard insuranceCard;

    private Account account1;
    public void initData(ObservableList<Customer> customers, Account account) {
        originalCustomerList = FXCollections.observableArrayList(customers);

        customerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        type.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));

        customerTable.setItems(originalCustomerList);
        account1 = account;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTypeComboBox.setItems(FXCollections.observableArrayList("All", "DEPENDENT", "POLICY_HOLDER"));
        customerTypeComboBox.getSelectionModel().select("All");

        customerTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            filterCustomerTable(newVal);
        });

        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewCustomerPage.fxml"));
                Parent root = loader.load();
                AdminCustomerView adminCustomerView = loader.getController();
                adminCustomerView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                showAlert("No Selection", "Please select a customer from the table.");
                return;
            }
            Account accountSelected = accountController.getAccountByID(selectedCustomer.getAccountId());
            String name = nameEdit.getText();
            String phone = phoneEdit.getText();
            String email = emailEdit.getText();
            String address = addressEdit.getText();
            String password = passwordEdit.getText();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
                showAlert("Invalid Input", "All fields must be filled.");
                return;
            }

            UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest(selectedCustomer.getId(), name, address, phone, email);
            UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(accountSelected.getId(), password);
            try {
                accountController.updatePassword(updatePasswordRequest, account1.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                customerController.updateCustomer(updateCustomerRequest, account1.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Customer information updated successfully.");

            refreshTable();
        });

        deleteButton.setOnAction(event -> {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                showAlert("No Selection", "Please select a customer from the table.");
                return;
            }

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure to delete this customer?");
            confirmationAlert.setContentText("Customer's card, claims, and account will be deleted.");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {
                try {
                    customerController.deleteCustomerById(selectedCustomer.getId(), account1.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                showAlert("Success", "Customer deleted successfully.");
                originalCustomerList.remove(selectedCustomer);
                refreshTable();
            }
        });

        viewInsuranceCard.setOnAction(event -> {
            try {
                Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
                if (selectedCustomer == null) {
                    showAlert("No Selection", "Please select a dependent from the table.");
                    return;
                }
                insuranceCard = insuranceCardController.getInsuranceCardByCustomerID(selectedCustomer.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewCustomerCardPage.fxml"));
                Parent root = loader.load();
                AdminCardView adminCardView = loader.getController();
                adminCardView.initData(insuranceCard, account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) viewInsuranceCard.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void refreshTable() {
        String selectedCustomerType = customerTypeComboBox.getValue();
        filterCustomerTable(selectedCustomerType);
        customerTable.refresh();
    }

    private void filterCustomerTable(String customerType) {
        if ("All".equals(customerType)) {
            customerTable.setItems(originalCustomerList);
        } else {
            CustomerType type = CustomerType.valueOf(customerType);
            List<Customer> filteredList = originalCustomerList.stream()
                    .filter(customer -> customer.getType() == type)
                    .collect(Collectors.toList());
            customerTable.setItems(FXCollections.observableArrayList(filteredList));
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
