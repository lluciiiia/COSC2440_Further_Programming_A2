package com.team2.a2;

import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PolicyHolderView implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Button viewInfoButton;
    @FXML
    private Button viewDependentInformationButton;

    @FXML
    private Text welcomeText;

    private CustomerController customerController = new CustomerController();
    private Customer customer;

    public void initData(Account account) {
        customer = customerController.getCustomer(account.getId());
        welcomeText.setText("Welcome, " + customer.getName());
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        viewInfoButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderInformationPage.fxml"));
                Parent root = loader.load();
                PolicyHolderInformationView policyHolderInformationView = loader.getController();
                policyHolderInformationView.initData(customer);
                Scene scene = new Scene(root);
                Stage stage = (Stage) viewInfoButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        viewDependentInformationButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderDependentView.fxml"));
                Parent root = loader.load();
                PolicyHolderDependentView policyHolderDependentPage = loader.getController();
                Task<List<Customer>> loadDependentTask = new Task<>() {
                    @Override
                    protected List<Customer> call() throws Exception {
                        return customerController.getDependentsByPolicyHolderAccountId(customer.getAccountId());
                    }
                };

                loadDependentTask.setOnSucceeded(workerStateEvent -> {
                    policyHolderDependentPage.initData(FXCollections.observableArrayList(loadDependentTask.getValue()), customer);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) viewDependentInformationButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadDependentTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadDependentTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
