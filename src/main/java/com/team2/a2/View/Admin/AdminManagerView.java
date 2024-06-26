package com.team2.a2.View.Admin;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.InsuranceManagerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
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

public class AdminManagerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button CreateManagerAccountButton;
    @FXML
    private Button ViewAllManager;

    private InsuranceManagerController insuranceManagerController = new InsuranceManagerController();
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

        CreateManagerAccountButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminCreateManagerPage.fxml"));
                Parent root = loader.load();
                AdminCreateManagerAccountView adminCreateManagerAccountView = loader.getController();
                adminCreateManagerAccountView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) CreateManagerAccountButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewAllManager.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewAllManagerPage.fxml"));
                Parent root = loader.load();
                AdminAllManagerView adminAllManagerView = loader.getController();
                Task<List<InsuranceManager>> loadManagerTask = new Task<>() {
                    @Override
                    protected List<InsuranceManager> call() throws Exception {
                        return insuranceManagerController.getAllInsuranceManagers();
                    }
                };

                loadManagerTask.setOnSucceeded(workerStateEvent -> {
                    adminAllManagerView.initData(FXCollections.observableArrayList(loadManagerTask.getValue()), account1);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewAllManager.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadManagerTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadManagerTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}