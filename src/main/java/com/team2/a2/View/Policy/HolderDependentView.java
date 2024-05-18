package com.team2.a2.View.Policy;

import com.team2.a2.Controller.*;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Request.UpdateAccountRequest;
import com.team2.a2.Request.UpdateCustomerRequest;
import javafx.beans.property.SimpleIntegerProperty;
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

public class HolderDependentView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button dependentClaimView;
    @FXML
    private Button ViewDependentCard;

    @FXML
    private Text customerNameText;
    @FXML
    private TableView<Customer> dependentTable;

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
    private TableColumn<Customer, Integer> policyOwnerID;

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
    private ClaimController claimController = new ClaimController();
    private CustomerController customerController = new CustomerController();
    private DependentController dependentController = new DependentController();

    private Account account;
    private Customer customer1;

    private InsuranceCardController insuranceCardController = new InsuranceCardController();
    private InsuranceCard insuranceCard;

    public void initData(ObservableList<Customer> dependents, Customer customer){
        customerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        policyOwnerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPolicyOwnerId()).asObject());

        ObservableList<Customer> dependentList = FXCollections.observableArrayList(dependents);
        dependentTable.setItems(dependentList);
        customerNameText.setText(customer.getName() + "'s dependent list");
        int accountID = customer.getAccountId();
        account = accountController.getAccountByID(accountID);
        customer1 = customer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/PolicyHolderPage.fxml"));
                Parent root = loader.load();
                HolderView policyHolderView = loader.getController();
                policyHolderView.initData(account);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        dependentClaimView.setOnAction(event -> {
            try {
                Customer selectedDependent = dependentTable.getSelectionModel().getSelectedItem();
                if (selectedDependent == null) {
                    showAlert("No Selection", "Please select a dependent from the table.");
                    return;
                }
                Dependent dependent = dependentController.getDependentByCustomerId(selectedDependent.getId());
                int dependentID = selectedDependent.getId();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/PolicyHolderDependentClaimPage.fxml"));
                Parent root = loader.load();
                HolderDependentClaimView policyHolderDependentClaimView = loader.getController();
                Task<List<Claim>> loadClaimsTask = new Task<>() {
                    @Override
                    protected List<Claim> call() throws Exception {
                        return claimController.getClaimsByCustomerId(dependentID);
                    }
                };


                loadClaimsTask.setOnSucceeded(workerStateEvent -> {
                    policyHolderDependentClaimView.initData(FXCollections.observableArrayList(loadClaimsTask.getValue()), customer1, dependent);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) dependentClaimView.getScene().getWindow();
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
            Customer selectedDependent = dependentTable.getSelectionModel().getSelectedItem();
            if (selectedDependent == null) {
                showAlert("No Selection", "Please select a dependent from the table.");
                return;
            }
            Account accountSelected = accountController.getAccountByID(selectedDependent.getAccountId());
            String name = nameEdit.getText();
            String phone = phoneEdit.getText();
            String email = emailEdit.getText();
            String address = addressEdit.getText();
            String password = passwordEdit.getText();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
                showAlert("Invalid Input", "All fields must be filled.");
                return;
            }

            UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest(selectedDependent.getId(),name, address, phone, email);
            UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(accountSelected.getId(), accountSelected.getUsername(), password);
            try {
                accountController.updateAccount(updateAccountRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                customerController.updateCustomer(updateCustomerRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Customer information updated successfully.");

        });

        ViewDependentCard.setOnAction(event -> {
            try {
                Customer selectedDependent = dependentTable.getSelectionModel().getSelectedItem();
                if (selectedDependent == null) {
                    showAlert("No Selection", "Please select a dependent from the table.");
                    return;
                }

                insuranceCard = insuranceCardController.getInsuranceCardByCustomerID(selectedDependent.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderDependentCardPage.fxml"));
                Parent root = loader.load();
                HolderDependentCardView policyHolderDependentCardView = loader.getController();
                policyHolderDependentCardView.initData(insuranceCard, account);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewDependentCard.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
