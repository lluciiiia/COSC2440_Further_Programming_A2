package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.InsuranceSurveyorController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
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

public class AdminAllSurveyorView implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private TableView<InsuranceSurveyor> insuranceSurveyorTable;

    @FXML
    private TableColumn<InsuranceSurveyor, Integer> insuranceSurveyorID;
    @FXML
    private TableColumn<InsuranceSurveyor, String> name;
    @FXML
    private TableColumn<InsuranceSurveyor, String> phoneNumber;
    @FXML
    private TableColumn<InsuranceSurveyor, String> companyAddress;
    @FXML
    private TableColumn<InsuranceSurveyor, String> companyName;
    @FXML
    private TableColumn<InsuranceSurveyor, String> email;
    @FXML
    private TableColumn<InsuranceSurveyor, Integer> managerId;

    private ObservableList<InsuranceSurveyor> originalInsuranceSurveyorList;

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
    private InsuranceSurveyorController insuranceSurveyorController = new InsuranceSurveyorController();

    void initData(ObservableList<InsuranceSurveyor> insuranceSurveyors) {
        originalInsuranceSurveyorList = FXCollections.observableArrayList(insuranceSurveyors);
        insuranceSurveyorID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        companyAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompanyAddress()));
        companyName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompanyName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        managerId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getInsuranceManagerId()).asObject());

        insuranceSurveyorTable.setItems(originalInsuranceSurveyorList);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminViewSurveyorPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            InsuranceSurveyor selectedSurveyor = insuranceSurveyorTable.getSelectionModel().getSelectedItem();
            if (selectedSurveyor == null) {
                showAlert("No Selection", "Please select a surveyor from the table.");
                return;
            }

            Account accountSelected = accountController.getAccountByID(selectedSurveyor.getAccountId());
            String name = nameEdit.getText();
            String password = passwordEdit.getText();
            String phone = phoneNumberEdit.getText();
            String email = emailEdit.getText();

            if (name.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                showAlert("Invalid Input", "All fields must be filled.");
                return;
            }
            int managerID = Integer.parseInt(managerId.getText());
            UpdateProviderRequest updateSurveyorRequest = new UpdateProviderRequest(selectedSurveyor.getId(), selectedSurveyor.getCompanyName(), selectedSurveyor.getCompanyAddress(), phone, email, name, managerID);
            UpdateAccountRequest updateAccountRequest = new UpdateAccountRequest(accountSelected.getId(), accountSelected.getUsername(), password);
            try {
                accountController.updateAccount(updateAccountRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                insuranceSurveyorController.updateInsuranceSurveyor(updateSurveyorRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Surveyor information updated successfully.");

            refreshTable();
        });

        deleteButton.setOnAction(event -> {
            InsuranceSurveyor selectedSurveyor = insuranceSurveyorTable.getSelectionModel().getSelectedItem();
            if (selectedSurveyor == null) {
                showAlert("No Selection", "Please select a surveyor from the table.");
                return;
            }

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure to delete this surveyor?");
            confirmationAlert.setContentText("Surveyor's account and associated data will be deleted.");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {
                insuranceSurveyorController.deleteInsuranceSurveyorById(selectedSurveyor.getId());
                showAlert("Success", "Manager deleted successfully.");
                originalInsuranceSurveyorList.remove(selectedSurveyor);
                refreshTable();
            }
        });
    }

    private void refreshTable() {
        insuranceSurveyorTable.refresh();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}