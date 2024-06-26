package com.team2.a2.View.Admin;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.InsuranceSurveyorController;
import com.team2.a2.Model.User.Account;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminSurveyorView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button CreateSurveyorButton;
    @FXML
    private Button ViewAllSurveyorButton;

    private InsuranceSurveyorController insuranceSurveyorController = new InsuranceSurveyorController();
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

        CreateSurveyorButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminCreateSurveyorPage.fxml"));
                Parent root = loader.load();
                AdminCreateSurveyorView adminCreateSurveyorView = loader.getController();
                adminCreateSurveyorView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) CreateSurveyorButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewAllSurveyorButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/AdminViewAllSurveyorPage.fxml"));
                Parent root = loader.load();
                AdminAllSurveyorView adminAllSurveyorView = loader.getController();
                Task<List<InsuranceSurveyor>> loadSurveyorTask = new Task<>() {
                    @Override
                    protected List<InsuranceSurveyor> call() throws Exception {
                        return insuranceSurveyorController.getAllInsuranceSurveyors();
                    }
                };

                loadSurveyorTask.setOnSucceeded(workerStateEvent -> {
                    adminAllSurveyorView.initData(FXCollections.observableArrayList(loadSurveyorTask.getValue()), account1);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewAllSurveyorButton.getScene().getWindow();
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
    }
}