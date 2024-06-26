package com.team2.a2.View.Admin;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.UpdatePasswordRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;
import javafx.beans.property.SimpleIntegerProperty;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminAllPolicyOwnerView implements Initializable {
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

    private Account account1;

    public void initData(ObservableList<PolicyOwner> policyOwners, Account account) {
        originalPolicyOwnerList = FXCollections.observableArrayList(policyOwners);

        policyOwnerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        policyOwnerTable.setItems(originalPolicyOwnerList);
        account1 = account;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewPolicyOwnerPage.fxml"));
                Parent root = loader.load();
                AdminPolicyOwnerView adminPolicyOwnerView = loader.getController();
                adminPolicyOwnerView.initData(account1);
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
            UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(accountSelected.getId(), password);
            try {
                accountController.updatePassword(updatePasswordRequest, account1.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                policyOwnerController.updatePolicyOwner(updatePolicyOwnerRequest, account1.getId());
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
                    policyOwnerController.deletePolicyOwnerById(selectedPolicyOwner.getId(), account1.getId());
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
