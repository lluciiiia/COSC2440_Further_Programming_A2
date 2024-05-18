package com.team2.a2.View.Admin;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.InsuranceManagerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Request.UpdateAccountRequest;
import com.team2.a2.Request.UpdateProviderRequest;
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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AllManagerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TableView<InsuranceManager> insuranceManagerTable;

    @FXML
    private TableColumn<InsuranceManager, Integer> insuranceManagerID;
    @FXML
    private TableColumn<InsuranceManager, String> name;
    @FXML
    private TableColumn<InsuranceManager, String> phoneNumber;
    @FXML
    private TableColumn<InsuranceManager, String> companyAddress;
    @FXML
    private TableColumn<InsuranceManager, String> companyName;
    @FXML
    private TableColumn<InsuranceManager, String> email;

    private ObservableList<InsuranceManager> originalInsuranceManagerList;

    @FXML
    private TextField nameEdit;
    @FXML
    private TextField passwordEdit;
    @FXML
    private TextField phoneNumberEdit;
    @FXML
    private TextField emailEdit;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    private AccountController accountController = new AccountController();
    private InsuranceManagerController insuranceManagerController = new InsuranceManagerController();

    public void initData(ObservableList<InsuranceManager> insuranceManagers) {
        originalInsuranceManagerList = FXCollections.observableArrayList(insuranceManagers);

        insuranceManagerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        companyAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompanyAddress()));
        companyName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompanyName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        insuranceManagerTable.setItems(originalInsuranceManagerList);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/team2/a2/AdminViewManagerPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            InsuranceManager selectedManager = insuranceManagerTable.getSelectionModel().getSelectedItem();
            if (selectedManager == null) {
                showAlert("No Selection", "Please select a manager from the table.");
                return;
            }
            Account accountSelected = accountController.getAccountByID(selectedManager.getAccountId());
            String name = nameEdit.getText();
            String password = passwordEdit.getText();
            String phone = phoneNumberEdit.getText();
            String email = emailEdit.getText();

            if (name.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                showAlert("Invalid Input", "All fields must be filled.");
                return;
            }

            UpdateProviderRequest updateManagerRequest = new UpdateProviderRequest(selectedManager.getId(), selectedManager.getCompanyName(), selectedManager.getCompanyAddress(), phone, email, name);
            UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(accountSelected.getId(), accountSelected.getUsername(), password);
            try {
                accountController.updateAccount(updateAccountRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                insuranceManagerController.updateInsuranceManager(updateManagerRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Manager information updated successfully.");

            refreshTable();
        });

        deleteButton.setOnAction(event -> {
            InsuranceManager selectedManager = insuranceManagerTable.getSelectionModel().getSelectedItem();
            if (selectedManager == null) {
                showAlert("No Selection", "Please select a manager from the table.");
                return;
            }

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure to delete this manager?");
            confirmationAlert.setContentText("Manager's account and associated data will be deleted.");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {
                insuranceManagerController.deleteInsuranceManagerById(selectedManager.getId());
                showAlert("Success", "Manager deleted successfully.");
                originalInsuranceManagerList.remove(selectedManager);
                refreshTable();
            }
        });
    }

    private void refreshTable() {
        insuranceManagerTable.refresh();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
