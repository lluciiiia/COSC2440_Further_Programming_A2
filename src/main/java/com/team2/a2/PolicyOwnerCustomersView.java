package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.UpdateAccountRequest;
import com.team2.a2.Request.UpdateCustomerRequest;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PolicyOwnerCustomersView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button viewInsuranceCard;

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
    @FXML
    private Button viewCustomerClaimButton;

    @FXML
    private Text nameText;

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

    private AccountController accountController = new AccountController();
    private Account account;

    private ClaimController claimController = new ClaimController();
    private PolicyOwner policyOwner1;

    private CustomerController customerController = new CustomerController();


    public void initData(List<Customer> customers, PolicyOwner policyOwner) {
        customerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        type.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));

        ObservableList<Customer> customerList = FXCollections.observableArrayList(customers);
        customerTable.setItems(customerList);

        nameText.setText(policyOwner.getName() + "'s beneficiaries");

        int accountID = policyOwner.getAccountId();
        account = accountController.getAccountByID(accountID);
        policyOwner1 = policyOwner;
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

        viewCustomerClaimButton.setOnAction(event -> {
            try {
                Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
                if (selectedCustomer == null) {
                    showAlert("No Selection", "Please select a dependent from the table.");
                    return;
                }
                int customerID = selectedCustomer.getId();
                Customer customer = customerController.getCustomerByCustomerId(customerID);


                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyOwnerCustomerClaimPage.fxml"));
                Parent root = loader.load();
                PolicyOwnerCustomerClaimView policyOwnerCustomerClaimView = loader.getController();
                Task<List<Claim>> loadClaimsTask = new Task<>() {
                    @Override
                    protected List<Claim> call() throws Exception {
                        return claimController.getClaimsByCustomerId(customerID);
                    }
                };


                loadClaimsTask.setOnSucceeded(workerStateEvent -> {
                    policyOwnerCustomerClaimView.initData(FXCollections.observableArrayList(loadClaimsTask.getValue()), policyOwner1, customer);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) viewCustomerClaimButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadClaimsTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadClaimsTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                showAlert("No Selection", "Please select a dependent from the table.");
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

            UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest(selectedCustomer.getId(),name, address, phone, email);
            UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(accountSelected.getId(), accountSelected.getUsername(), password);
            accountController.updateAccount(updateAccountRequest);
            customerController.updateCustomer(updateCustomerRequest);
            showAlert("Success", "Customer information updated successfully.");

        });

//        viewInsuranceCard.setOnAction(event -> {
//            try {
//                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PolicyOwnerCustomerCardPage.fxml")));
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) viewInsuranceCard.getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
