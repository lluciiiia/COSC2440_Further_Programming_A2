package com.team2.a2;

import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminSeeCustomerAllCustomerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private ComboBox<String> customerTypeComboBox;

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

    private ObservableList<Customer> originalCustomerList;

    public void initData(ObservableList<Customer> customers) {
        originalCustomerList = FXCollections.observableArrayList(customers);

        customerID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        type.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));

        customerTable.setItems(originalCustomerList);
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
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminViewCustomerPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
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
}
