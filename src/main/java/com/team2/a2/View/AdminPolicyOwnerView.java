package com.team2.a2.View;

import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Customer.PolicyOwner;
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

public class AdminPolicyOwnerView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button CreatePolicyOwnerAccount;
    @FXML
    private Button ViewAllPolicyOwnerButton;

    private PolicyOwnerController policyOwnerController = new PolicyOwnerController();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        CreatePolicyOwnerAccount.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminCreatePolicyOwnerAccountPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) CreatePolicyOwnerAccount.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewAllPolicyOwnerButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewAllPolicyOwnerPage.fxml"));
                Parent root = loader.load();
                AdminAllPolicyOwnerView adminAllPolicyOwnerView = loader.getController();
                Task<List<PolicyOwner>> loadPolicyOwnerTask = new Task<>() {
                    @Override
                    protected List<PolicyOwner> call() throws Exception {
                        return policyOwnerController.getAllPolicyOwners();
                    }
                };

                loadPolicyOwnerTask.setOnSucceeded(workerStateEvent -> {
                    adminAllPolicyOwnerView.initData(FXCollections.observableArrayList(loadPolicyOwnerTask.getValue()));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewAllPolicyOwnerButton.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadPolicyOwnerTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadPolicyOwnerTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}