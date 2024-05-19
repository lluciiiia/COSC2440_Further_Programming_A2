package com.team2.a2.View.Admin;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.LogController;
import com.team2.a2.Model.Log;
import com.team2.a2.Model.User.Account;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminView implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Button ViewCustomerButton;

    @FXML
    private Button ViewPolicyOwnerButton;

    @FXML
    private Button ViewInsuranceSurveyorButton;

    @FXML
    private Button ViewInsuranceManagerButton;

    @FXML
    private Button AllAccountButton;

    @FXML
    private Button viewLogHistoryButton;

    private AccountController accountController = new AccountController();
    private LogController logController = new LogController();

    private Account account1;

    public void initData(Account account) {
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

        viewLogHistoryButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewLogHistoryPage.fxml"));
                Parent root = loader.load();
                AdminLogHistoryView adminLogHistoryView = loader.getController();
                Task<List<Log>> loadLogTask = new Task<>() {
                    @Override
                    protected List<Log> call() throws Exception {
                        return logController.getLogsByAccountId(account1.getId());
                    }
                };

                loadLogTask.setOnSucceeded(workerStateEvent -> {
                    adminLogHistoryView.initData(FXCollections.observableArrayList(loadLogTask.getValue()), account1);
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

        ViewCustomerButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewCustomerPage.fxml"));
                Parent root = loader.load();
                AdminCustomerView adminCustomerView = loader.getController();
                adminCustomerView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewCustomerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewPolicyOwnerButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewPolicyOwnerPage.fxml"));
                Parent root = loader.load();
                AdminPolicyOwnerView adminPolicyOwnerView = loader.getController();
                adminPolicyOwnerView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewPolicyOwnerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewInsuranceSurveyorButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewSurveyorPage.fxml"));
                Parent root = loader.load();
                AdminSurveyorView adminSurveyorView = loader.getController();
                adminSurveyorView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewInsuranceSurveyorButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewInsuranceManagerButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewManagerPage.fxml"));
                Parent root = loader.load();
                AdminManagerView adminManagerView = loader.getController();
                adminManagerView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ViewInsuranceManagerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        AllAccountButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewAllAccountPage.fxml"));
                Parent root = loader.load();
                AdminViewAllAccount adminViewAllAccount = loader.getController();
                Task<List<Account>> loadAccountTask = new Task<>() {
                    @Override
                    protected List<Account> call() throws Exception {
                        return accountController.getAllAccounts();
                    }
                };

                loadAccountTask.setOnSucceeded(workerStateEvent -> {
                    adminViewAllAccount.initData(FXCollections.observableArrayList(loadAccountTask.getValue()), account1);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) AllAccountButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadAccountTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadAccountTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

