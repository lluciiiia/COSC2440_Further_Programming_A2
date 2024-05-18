package com.team2.a2;

import com.team2.a2.Controller.InsuranceManagerController;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminCreateManagerAccountView implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField companyNameTextField;
    @FXML
    private Button createAccountButton;


    private Account account1;
    public void initData(Account account) {
        account1 = account;
    }

    private InsuranceManagerController insuranceManagerController = new InsuranceManagerController();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewManagerPage.fxml"));
                Parent root = loader.load();
                AdminManagerView adminManagerView = loader.getController();
                adminManagerView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        createAccountButton.setOnAction(event -> {
            try {
                createAccount();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void createAccount() throws Exception {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String fullName = fullNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String address = addressTextField.getText();
        String companyName = companyNameTextField.getText();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || companyName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        InsertInsuranceManagerRequest insertInsuranceManagerRequest;
        insertInsuranceManagerRequest = new InsertInsuranceManagerRequest(username, password, companyName, address, phone, email, fullName);
        insuranceManagerController.createInsuranceManager(insertInsuranceManagerRequest, account1.getId());
        showAlert(Alert.AlertType.INFORMATION, "Account Created!", "Account created successfully");
    }
}