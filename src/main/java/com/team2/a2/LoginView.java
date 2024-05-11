package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.Enum.AccountType;
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
import java.util.Objects;

public class LoginView {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private AccountController accountController;

    //constructor
    public LoginView() {
        this.accountController = new AccountController();
    }

    @FXML
    protected void initialize() {
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (isValidCredentials(username, password)) {
                AccountType accountType = login(username, password);
                if (accountType != null) {
                    redirectToHomePage(accountType);
                } else {
                    showAlert("Error", "User type not recognized");
                }
            } else {
                showAlert("Error", "Invalid username or password");
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    private AccountType login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        boolean loggedIn = accountController.login(loginRequest);
        if (loggedIn) {
            return accountController.getAccountType(username, password);
        }
        return null;
    }

    private void redirectToHomePage(AccountType accountType) {
        switch (accountType) {
            case POLICY_HOLDER:
                loadPage("PolicyHolderPage.fxml");
                break;
            case DEPENDENT:
                loadPage("DependentPage.fxml");
                break;
            case POLICY_OWNER:
                loadPage("PolicyOwnerPage.fxml");
                break;
            case INSURANCE_SURVEYOR:
                loadPage("InsuranceSurveyorPage.fxml");
                break;
            case INSURANCE_MANAGER:
                loadPage("InsuranceManagerPage.fxml");
                break;
            case ADMIN:
                loadPage("AdminPage.fxml");
                break;
            default:
                showAlert("Error", "Wrong username or password !!!!");
                break;
        }
    }

    private void loadPage(String pageName) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(pageName)));
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the page.");
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