package com.team2.a2.View.Insurance;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
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
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SurveyorAllCustomerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button viewInsuranceCard;
    @FXML
    private ComboBox<String> customerTypeComboBox;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> cusID;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> phoneNumber;
    @FXML
    private TableColumn<Customer, String> homeAddress;
    @FXML
    private TableColumn<Customer, String> email;
    @FXML
    private TableColumn<Customer, CustomerType> type;
    @FXML
    private TableColumn<Customer, Integer> policyOwnerID;

    private ObservableList<Customer> originalCustomerList;

    private AccountController accountController = new AccountController();
    private Account account;
    private InsuranceCardController insuranceCardController = new InsuranceCardController();
    private InsuranceCard insuranceCard;

    public void initData(ObservableList<Customer> customers, InsuranceSurveyor insuranceSurveyor) {
        originalCustomerList = FXCollections.observableArrayList(customers);
        cusID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        homeAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        type.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));
        policyOwnerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPolicyOwnerId()).asObject());

        customerTable.setItems(originalCustomerList);

        int accountID = insuranceSurveyor.getAccountId();
        account = accountController.getAccountByID(accountID);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceSurveyorPage.fxml"));
                Parent root = loader.load();
                SurveyorView insuranceSurveyorView = loader.getController();
                insuranceSurveyorView.initData(account);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InsuranceSurveyorViewCardPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorViewCard insuranceSurveyorViewCard = loader.getController();
                insuranceSurveyorViewCard.initData(insuranceCard, account);
                Scene scene = new Scene(root);
                Stage stage = (Stage) viewInsuranceCard.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
