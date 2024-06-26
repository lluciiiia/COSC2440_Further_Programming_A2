package com.team2.a2.View.Admin;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.InsuranceObject.Claim;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCustomerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button CreateCustomerAccountButton;
    @FXML
    private Button ViewCustomerInformationButton;
    @FXML
    private Button ViewListOfCustomerClaimButton;

    private CustomerController customerController = new CustomerController();
    private ClaimController claimController = new ClaimController();

    private Account account1;

    public void initData(Account account) {
        account1 = account;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminPage.fxml"));
                Parent root = loader.load();
                AdminView adminView = loader.getController();
                adminView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        CreateCustomerAccountButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminCreateCustomerAccountPage.fxml"));
                Parent root = loader.load();
                AdminCreateCustomerAccountView adminCreateCustomerAccountView = loader.getController();
                adminCreateCustomerAccountView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) CreateCustomerAccountButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewListOfCustomerClaimButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewCustomerLOCPage.fxml"));
                Parent root = loader.load();
                AdminCustomerLOCView adminCustomerLOCView = loader.getController();
                Task<List<Claim>> loadClaimTask = new Task<>() {
                    @Override
                    protected List<Claim> call() throws Exception {
                        return claimController.getAllClaims();
                    }
                };

                loadClaimTask.setOnSucceeded(workerStateEvent -> {
                    adminCustomerLOCView.initData(FXCollections.observableArrayList(loadClaimTask.getValue()), account1);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewListOfCustomerClaimButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadClaimTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadClaimTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewCustomerInformationButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewAllCustomerPage.fxml"));
                Parent root = loader.load();
                AdminSeeCustomerAllCustomerView adminSeeCustomerAllCustomerView = loader.getController();
                Task<List<Customer>> loadCustomersTask = new Task<>() {
                    @Override
                    protected List<Customer> call() throws Exception {
                        return customerController.getAllCustomers();
                    }
                };

                loadCustomersTask.setOnSucceeded(workerStateEvent -> {
                    adminSeeCustomerAllCustomerView.initData(FXCollections.observableArrayList(loadCustomersTask.getValue()), account1);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewCustomerInformationButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadCustomersTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadCustomersTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}