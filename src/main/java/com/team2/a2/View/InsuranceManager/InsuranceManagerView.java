package com.team2.a2.View.InsuranceManager;

import com.team2.a2.Controller.*;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Log;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Provider.InsuranceManager;
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

public class InsuranceManagerView implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Button ViewMyButton;

    @FXML
    private Button ViewCustomerButton;

    @FXML
    private Button ViewLOCButton;

    @FXML
    private Button ViewInsuranceSurveyorButton;

    @FXML
    private Button viewLogHistoryButton;
    @FXML
    private Text myNameText;

    private InsuranceManagerController insuranceManagerController = new InsuranceManagerController();
    private InsuranceSurveyorController insuranceSurveyorController = new InsuranceSurveyorController();

    private InsuranceManager insuranceManager;

    private CustomerController customerController = new CustomerController();
    private ClaimController claimController = new ClaimController();
    private LogController logController = new LogController();
    private Account account1;

    public void initData(Account account) {
        insuranceManager = insuranceManagerController.getInsuranceManagerByAccountId(account.getId());
        myNameText.setText("Welcome, " + insuranceManager.getName());
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

        ViewMyButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceManagerInformationPage.fxml"));
                Parent root = loader.load();
                InsuranceManagerInforView insuranceManagerInforView = loader.getController();
                insuranceManagerInforView.initData(insuranceManager);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewMyButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewCustomerButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceManagerAllCustomerPage.fxml"));
                Parent root = loader.load();
                InsuranceManagerAllCustomerView insuranceManagerAllCustomerView = loader.getController();
                Task<List<Customer>> loadCustomersTask = new Task<>() {
                    @Override
                    protected List<Customer> call() throws Exception {
                        return customerController.getAllCustomers();
                    }
                };

                loadCustomersTask.setOnSucceeded(workerStateEvent -> {
                    insuranceManagerAllCustomerView.initData(FXCollections.observableArrayList(loadCustomersTask.getValue()), insuranceManager);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewCustomerButton.getScene().getWindow();
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

        ViewInsuranceSurveyorButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceManagerViewSurveyorPage.fxml"));
                Parent root = loader.load();
                InsuranceManagerSurveyorView insuranceManagerSurveyorView = loader.getController();
                Task<List<InsuranceSurveyor>> loadSurveyorTask = new Task<>() {
                    @Override
                    protected List<InsuranceSurveyor> call() throws Exception {
                        return insuranceSurveyorController.getInsuranceSurveyorsByManagerId(insuranceManager.getId());
                    }
                };

                loadSurveyorTask.setOnSucceeded(workerStateEvent -> {
                    insuranceManagerSurveyorView.initData(FXCollections.observableArrayList(loadSurveyorTask.getValue()), insuranceManager);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewInsuranceSurveyorButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadSurveyorTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadSurveyorTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewLOCButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceManagerLOCPage.fxml"));
                Parent root = loader.load();
                InsuranceManagerLOCView insuranceManagerLOCView = loader.getController();
                Task<List<Claim>> loadClaimTask = new Task<>() {
                    @Override
                    protected List<Claim> call() throws Exception {
                        return claimController.getAllClaims();
                    }
                };

                loadClaimTask.setOnSucceeded(workerStateEvent -> {
                    insuranceManagerLOCView.initData(FXCollections.observableArrayList(loadClaimTask.getValue()), insuranceManager);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewLOCButton.getScene().getWindow();
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceManagerViewLogHistoryPage.fxml"));
                Parent root = loader.load();
                InsuranceManagerLogHistoryView insuranceManagerLogHistoryView = loader.getController();
                Task<List<Log>> loadLogTask = new Task<>() {
                    @Override
                    protected List<Log> call() throws Exception {
                        return logController.getLogsByAccountId(account1.getId());
                    }
                };

                loadLogTask.setOnSucceeded(workerStateEvent -> {
                    insuranceManagerLogHistoryView.initData(FXCollections.observableArrayList(loadLogTask.getValue()), account1);
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
