package com.team2.a2.View.PolicyOwner;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.UpdatePasswordRequest;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class PolicyOwnerCustomersView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button viewInsuranceCard;
    @FXML
    private Button deleteButton;

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

    private ObservableList<Customer> originalCustomerList;

    private AccountController accountController = new AccountController();
    private Account account;

    private ClaimController claimController = new ClaimController();
    private PolicyOwner policyOwner1;

    private CustomerController customerController = new CustomerController();
    private InsuranceCardController insuranceCardController = new InsuranceCardController();
    private InsuranceCard insuranceCard;


    public void initData(List<Customer> customers, PolicyOwner policyOwner) {
        originalCustomerList = FXCollections.observableArrayList(customers);
        customerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        type.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));

        customerTable.setItems(originalCustomerList);

        nameText.setText(policyOwner.getName() + "'s beneficiaries");

        int accountID = policyOwner.getAccountId();
        account = accountController.getAccountByID(accountID);
        policyOwner1 = policyOwner;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/PolicyOwnerPage.fxml"));
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
                Customer customer = customerController.getCustomerById(customerID);


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/PolicyOwnerCustomerClaimPage.fxml"));
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
            UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(accountSelected.getId(), password);
            try {
                accountController.updatePassword(updatePasswordRequest, account.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                customerController.updateCustomer(updateCustomerRequest, account.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Customer information updated successfully.");

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
                    customerController.deleteCustomerById(selectedCustomer.getId(), account.getId());
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/PolicyOwnerCustomerCardPage.fxml"));
                Parent root = loader.load();
                PolicyOwnerCustomerCardView policyOwnerCustomerCardView = loader.getController();
                policyOwnerCustomerCardView.initData(insuranceCard, account);
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
        customerTable.refresh();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
