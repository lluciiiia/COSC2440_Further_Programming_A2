package com.team2.a2.View.Admin;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.UpdateAccountRequest;
import com.team2.a2.Request.UpdateCustomerRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;
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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AllPolicyOwnerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TableView<PolicyOwner> policyOwnerTable;

    @FXML
    private TableColumn<PolicyOwner, Integer> policyOwnerID;
    @FXML
    private TableColumn<PolicyOwner, String> name;

    private ObservableList<PolicyOwner> originalPolicyOwnerList;

    @FXML
    private TextField nameEdit;
    @FXML
    private TextField passwordEdit;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    private AccountController accountController = new AccountController();
    private PolicyOwnerController policyOwnerController = new PolicyOwnerController();

    public void initData(ObservableList<PolicyOwner> policyOwners) {
        originalPolicyOwnerList = FXCollections.observableArrayList(policyOwners);

        policyOwnerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        policyOwnerTable.setItems(originalPolicyOwnerList);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/team2/a2/AdminViewPolicyOwnerPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            PolicyOwner selectedPolicyOwner = policyOwnerTable.getSelectionModel().getSelectedItem();
            if (selectedPolicyOwner == null) {
                showAlert("No Selection", "Please select a policy owner from the table.");
                return;
            }
            Account accountSelected = accountController.getAccountByID(selectedPolicyOwner.getAccountId());
            String name = nameEdit.getText();
            String password = passwordEdit.getText();

            if (name.isEmpty() || password.isEmpty()) {
                showAlert("Invalid Input", "All fields must be filled.");
                return;
            }

            UpdatePolicyOwnerRequest updatePolicyOwnerRequest = new UpdatePolicyOwnerRequest(selectedPolicyOwner.getId(), name);
            UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(accountSelected.getId(), accountSelected.getUsername(), password);
            try {
                accountController.updateAccount(updateAccountRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                policyOwnerController.updatePolicyOwner(updatePolicyOwnerRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Customer information updated successfully.");

            refreshTable();
        });

        deleteButton.setOnAction(event -> {
            PolicyOwner selectedPolicyOwner = policyOwnerTable.getSelectionModel().getSelectedItem();
            if (selectedPolicyOwner == null) {
                showAlert("No Selection", "Please select a policy owner from the table.");
                return;
            }

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure to delete this customer?");
            confirmationAlert.setContentText("Policy owner's account and surveyors will be deleted.");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {
                try {
                    policyOwnerController.deletePolicyOwnerById(selectedPolicyOwner.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                showAlert("Success", "Customer deleted successfully.");
                originalPolicyOwnerList.remove(selectedPolicyOwner);
                refreshTable();
            }
        });
    }

    private void refreshTable() {
        policyOwnerTable.setItems(FXCollections.observableArrayList(originalPolicyOwnerList));
        policyOwnerTable.refresh();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
