package com.team2.a2;

import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Controller.LogController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.Log;
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
    private Button viewInsuranceButton;

    @FXML
    private Button viewInfoButton;
    @FXML
    private Button viewDependentInformationButton;
    @FXML
    private Button viewClaimInformationButton;
    @FXML
    private Button createClaim;
    @FXML
    private Button viewLogHistoryButton;

    @FXML
    private Text welcomeText;

    private CustomerController customerController = new CustomerController();
    private Customer customer;

    private final ClaimController claimController = new ClaimController();
    private InsuranceCardController insuranceCardController = new InsuranceCardController();
    private InsuranceCard insuranceCard;
    private Account account1;
    private LogController logController = new LogController();

    public void initData(Account account) {
        customer = customerController.getCustomerByAccountId(account.getId());
        welcomeText.setText("Welcome, " + customer.getName());
        insuranceCard = insuranceCardController.getInsuranceCardByCustomerID(customer.getId());
        account1 = account;
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderDependentPage.fxml"));
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

        viewClaimInformationButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderClaimPage.fxml"));
                Parent root = loader.load();
                PolicyHolderClaimView policyHolderClaimView = loader.getController();
                Task<List<Claim>> loadClaimsTask = new Task<>() {
                    @Override
                    protected List<Claim> call() throws Exception {
                        return claimController.getClaimsByCustomerId(customer.getId());
                    }
                };

                loadClaimsTask.setOnSucceeded(workerStateEvent -> {
                    policyHolderClaimView.initData(FXCollections.observableArrayList(loadClaimsTask.getValue()), customer);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) viewClaimInformationButton.getScene().getWindow();
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

        createClaim.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderCreateClaimPage.fxml"));
                Parent root = loader.load();
                PolicyHolderCreateClaimView policyHolderCreateClaimView = loader.getController();
                policyHolderCreateClaimView.initData(customer);
                Scene scene = new Scene(root);
                Stage stage = (Stage) createClaim.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        viewInsuranceButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderInsuranceCardPage.fxml"));
                Parent root = loader.load();
                PolicyHolderCardView policyHolderCardView = loader.getController();
                policyHolderCardView.initData(insuranceCard, customer);
                Scene scene = new Scene(root);
                Stage stage = (Stage) viewInsuranceButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        viewLogHistoryButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderViewLogHistoryPage.fxml"));
                Parent root = loader.load();
                PolicyHolderLogHistoryView policyHolderLogHistoryView = loader.getController();
                Task<List<Log>> loadLogTask = new Task<>() {
                    @Override
                    protected List<Log> call() throws Exception {
                        return logController.getLogsByAccountId(account1.getId());
                    }
                };

                loadLogTask.setOnSucceeded(workerStateEvent -> {
                    policyHolderLogHistoryView.initData(FXCollections.observableArrayList(loadLogTask.getValue()), account1);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) viewLogHistoryButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadLogTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadLogTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
