package com.team2.a2.View.InsuranceSurveyor;

import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.InsuranceSurveyorController;
import com.team2.a2.Controller.LogController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Log;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
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

public class InsuranceSurveyorView implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private Button ViewInsuranceSurveyorButton;
    @FXML
    private Button ViewInsuranceSurveyorCustomerButton;
    @FXML
    private Button ViewInsuranceSurveyorClaimButton;
    @FXML
    private Button viewLogHistoryButton;
    @FXML
    private Text insuranceSurveyorName;

    private InsuranceSurveyorController insuranceSurveyorController = new InsuranceSurveyorController();
    private InsuranceSurveyor insuranceSurveyor;

    private CustomerController customerController = new CustomerController();
    private ClaimController claimController = new ClaimController();
    private LogController logController = new LogController();
    private Account account1;


    public void initData(Account account) {
        insuranceSurveyor = insuranceSurveyorController.getInsuranceSurveyorByAccountId(account.getId());
        insuranceSurveyorName.setText("Welcome, " + insuranceSurveyor.getName());
        account1 = account;

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/team2/a2/LoginPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewInsuranceSurveyorButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceSurveyorInfoPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorInfoView insuranceSurveyorInfoView = loader.getController();
                insuranceSurveyorInfoView.initData(insuranceSurveyor);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewInsuranceSurveyorButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewInsuranceSurveyorCustomerButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceSurveyorAllCustomerPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorAllCustomerView insuranceSurveyorAllCustomerView = loader.getController();
                Task<List<Customer>> loadCustomersTask = new Task<>() {
                    @Override
                    protected List<Customer> call() throws Exception {
                        return customerController.getAllCustomers();
                    }
                };

                loadCustomersTask.setOnSucceeded(workerStateEvent -> {
                    insuranceSurveyorAllCustomerView.initData(FXCollections.observableArrayList(loadCustomersTask.getValue()), insuranceSurveyor);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewInsuranceSurveyorCustomerButton.getScene().getWindow();
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

        ViewInsuranceSurveyorClaimButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceSurveyorLOCPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorLOCView insuranceSurveyorLOCView = loader.getController();
                Task<List<Claim>> loadClaimTask = new Task<>() {
                    @Override
                    protected List<Claim> call() throws Exception {
                        return claimController.getAllClaims();
                    }
                };

                loadClaimTask.setOnSucceeded(workerStateEvent -> {
                    insuranceSurveyorLOCView.initData(FXCollections.observableArrayList(loadClaimTask.getValue()), insuranceSurveyor);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewInsuranceSurveyorClaimButton.getScene().getWindow();
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

        viewLogHistoryButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceSurveyorViewLogHistoryPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorLogHistoryView insuranceSurveyorLogHistoryView = loader.getController();
                Task<List<Log>> loadLogTask = new Task<>() {
                    @Override
                    protected List<Log> call() throws Exception {
                        return logController.getLogsByAccountId(account1.getId());
                    }
                };

                loadLogTask.setOnSucceeded(workerStateEvent -> {
                    insuranceSurveyorLogHistoryView.initData(FXCollections.observableArrayList(loadLogTask.getValue()), account1);
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
