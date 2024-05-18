package com.team2.a2.View;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    protected void initialize() {
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (isValidCredentials(username, password)) {
                try {
                    redirectToHomePage(username, password);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                showAlert("Error", "Invalid username or password");
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    private void redirectToHomePage(String username, String password) throws IOException {
        AccountController accountController = new AccountController();
        Account account = accountController.login(new LoginRequest(username, password));

        if (account != null) {
            FXMLLoader loader = new FXMLLoader();
            Parent root = null;

            switch (account.getType()) {
                case POLICY_HOLDER:
                    loader.setLocation(getClass().getResource("PolicyHolderPage.fxml"));
                    root = loader.load();
                    PolicyHolderView policyHolderView = loader.getController();
                    policyHolderView.initData(account);
                    break;
                case DEPENDENT:
                    loader.setLocation(getClass().getResource("DependentPage.fxml"));
                    root = loader.load();
                    DependentView dependentView = loader.getController();
                    dependentView.initData(account);
                    break;
                case POLICY_OWNER:
                    loader.setLocation(getClass().getResource("PolicyOwnerPage.fxml"));
                    root = loader.load();
                    PolicyOwnerView policyOwnerView = loader.getController();
                    policyOwnerView.initData(account);
                    break;
                case ADMIN:
                    loader.setLocation(getClass().getResource("AdminPage.fxml"));
                    root = loader.load();
                    break;
                case INSURANCE_MANAGER:
                    loader.setLocation(getClass().getResource("InsuranceManagerPage.fxml"));
                    root = loader.load();
                    InsuranceManagerView insuranceManagerView = loader.getController();
                    insuranceManagerView.initData(account);
                    break;
                case INSURANCE_SURVEYOR:
                    loader.setLocation(getClass().getResource("InsuranceSurveyorPage.fxml"));
                    root = loader.load();
                    InsuranceSurveyorView insuranceSurveyorView = loader.getController();
                    insuranceSurveyorView.initData(account);
                    break;
                default:
                    showAlert("Error", "Invalid account type");
            }

            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            showAlert("Error", "Invalid username or password");
        }


    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}