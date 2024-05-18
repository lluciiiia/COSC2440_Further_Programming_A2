package com.team2.a2;

import com.team2.a2.Controller.InsuranceManagerController;
import com.team2.a2.Controller.InsuranceSurveyorController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.InsertInsuranceSurveyorRequest;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminCreateSurveyorView implements Initializable {

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
    @FXML
    private ComboBox<Integer> insuranceManagerComboBox;

    private InsuranceManagerController insuranceManagerController = new InsuranceManagerController();
    private InsuranceSurveyorController insuranceSurveyorController = new InsuranceSurveyorController();

    private Account account1;

    public void initData(Account account) {
        account1 = account;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminViewSurveyorPage.fxml"));
                Parent root = loader.load();
                AdminSurveyorView adminSurveyorView = loader.getController();
                adminSurveyorView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        insuranceManagerComboBox.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object != null ? object.toString() : "";
            }

            @Override
            public Integer fromString(String string) {
                return string != null && !string.isEmpty() ? Integer.valueOf(string) : null;
            }
        });
        updateInsuranceManagerComboBox();
        createAccountButton.setOnAction(event -> {
            try {
                createAccount();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void updateInsuranceManagerComboBox() {
        List<InsuranceManager> managers = insuranceManagerController.getAllInsuranceManagers();
        List<Integer> managersIds = managers.stream()
                .map(InsuranceManager::getId)
                .collect(Collectors.toList());
        insuranceManagerComboBox.setItems(FXCollections.observableArrayList(managersIds));
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
        int insuranceManagerId = insuranceManagerComboBox.getValue() != null ? insuranceManagerComboBox.getValue() : -1;
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || companyName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }

        InsertInsuranceSurveyorRequest insertInsuranceSurveyorRequest;
        insertInsuranceSurveyorRequest = new InsertInsuranceSurveyorRequest(username, password, companyName, address, phone, email, fullName, insuranceManagerId);
        insuranceSurveyorController.createInsuranceSurveyor(insertInsuranceSurveyorRequest, account1.getId());
        showAlert(Alert.AlertType.INFORMATION, "Account Created!", "Account created successfully");
    }
}