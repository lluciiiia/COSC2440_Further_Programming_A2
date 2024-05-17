package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.UpdateClaimRequest;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class PolicyOwnerCustomerClaimView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button editClaimButton;
    @FXML
    private DatePicker claimDatePicker;
    @FXML
    private DatePicker examDatePicker;
    @FXML
    private TextField amountField;
    @FXML
    private Button deleteClaimButton;
    @FXML
    private Button createClaimButton;

    @FXML
    private TableView<Claim> claimTable;
    @FXML
    private TableColumn<Claim, String> claimID;
    @FXML
    private TableColumn<Claim, Date> claimDate;
    @FXML
    private TableColumn<Claim, Date> examDate;
    @FXML
    private TableColumn<Claim, Double> claimAmount;
    @FXML
    private TableColumn<Claim, Integer> customerID;
    @FXML
    private TableColumn<Claim, String> status;
    @FXML
    private TableColumn<Claim, Boolean> isDocumentRequested;
    private ObservableList<Claim> originalClaimList;

    private ClaimController claimController = new ClaimController();
    private CustomerController customerController= new CustomerController();
    private AccountController accountController = new AccountController();
    private Account account;
    private PolicyOwner policyOwner1;
    private Customer customer1;

    public void initData(ObservableList<Claim> claims, PolicyOwner policyOwner, Customer customer) {
        originalClaimList = FXCollections.observableArrayList(claims);
        claimID.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getId");
                return new SimpleStringProperty(String.valueOf(method.invoke(cellData.getValue())));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        claimDate.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getClaimDate");
                Date claimDate = (Date) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(claimDate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        examDate.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getExamDate");
                Date examDate = (Date) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(examDate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        claimAmount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
        customerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomerId()).asObject());
        status.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getStatus");
                return new SimpleStringProperty(String.valueOf(method.invoke(cellData.getValue())));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });
        isDocumentRequested.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getDocumentRequested");
                Boolean documentRequested = (Boolean) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(documentRequested);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        claimTable.setItems(originalClaimList);

        int accountID = policyOwner.getAccountId();
        account = accountController.getAccountByID(accountID);

        policyOwner1 = policyOwner;
        customer1 = customer;
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

        createClaimButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyOwnerCreateClaim.fxml"));
                Parent root = loader.load();

                PolicyOwnerCreateClaimView policyOwnerCreateClaimView = loader.getController();
                policyOwnerCreateClaimView.initData(customer1, policyOwner1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) createClaimButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editClaimButton.setOnAction(event -> editSelectedClaim());
        deleteClaimButton.setOnAction(event -> deleteSelectedClaim());
    }

    private void editSelectedClaim() {
        Claim selectedClaim = claimTable.getSelectionModel().getSelectedItem();
        if (selectedClaim == null) {
            showAlert(Alert.AlertType.ERROR, "Selection Error", "Please select a claim to edit.");
            return;
        }

        LocalDate claimLocalDate = claimDatePicker.getValue();
        LocalDate examLocalDate = examDatePicker.getValue();
        String amountText = amountField.getText();

        if (claimLocalDate == null || examLocalDate == null || amountText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please fill in all fields.");
            return;
        }

        try {
            Double amount = Double.parseDouble(amountText);
            java.sql.Date claimDate = java.sql.Date.valueOf(claimLocalDate);
            java.sql.Date examDate = java.sql.Date.valueOf(examLocalDate);

            UpdateClaimRequest updateClaimRequest = new UpdateClaimRequest(selectedClaim.getId(), claimDate, examDate, amount);
            claimController.updateClaim(updateClaimRequest);

            showAlert(Alert.AlertType.INFORMATION, "Update Successful", "Claim updated successfully.");
            refreshClaimTable();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please enter a valid amount.");
        }
    }

    private void deleteSelectedClaim() {
        Claim selectedClaim = claimTable.getSelectionModel().getSelectedItem();
        if (selectedClaim == null) {
            showAlert(Alert.AlertType.ERROR, "Selection Error", "Please select a claim to delete.");
            return;
        }

        int claimId = selectedClaim.getId();
        claimController.deleteClaimById(claimId);

        originalClaimList.remove(selectedClaim);
        refreshClaimTable();

        showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "Claim deleted successfully.");
    }

    private void refreshClaimTable() {
        claimTable.refresh();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
