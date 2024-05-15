package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PolicyHolderDependentView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button dependentClaimView;

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

    private AccountController accountController = new AccountController();
    private ClaimController claimController = new ClaimController();
    private CustomerController customerController = new CustomerController();
    private Account account;
    private Customer customer1;

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
        account = accountController.getAccountByCustomerID(accountID);
        customer1 = customer;
    }

    @Override
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

        dependentClaimView.setOnAction(event -> {
            try {
                Customer selectedDependent = dependentTable.getSelectionModel().getSelectedItem();
                Dependent dependent = customerController.getDependentByCustomerId(selectedDependent.getId());
                int dependentID = selectedDependent.getId();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderDependentClaimPage.fxml"));
                Parent root = loader.load();
                PolicyHolderDependentClaimView policyHolderDependentClaimView = loader.getController();
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

    }
}
