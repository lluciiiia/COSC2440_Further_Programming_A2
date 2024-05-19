package com.team2.a2.View.InsuranceManager;

/**
 * @author <Team 2>
 */

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InsuranceManagerSurveyorView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TableView<InsuranceSurveyor> insuranceSurveyorTable;
    @FXML
    private TableColumn<InsuranceSurveyor, Integer> surveyorID;
    @FXML
    private TableColumn<InsuranceSurveyor, String> name;
    @FXML
    private TableColumn<InsuranceSurveyor, String> companyName;
    @FXML
    private TableColumn<InsuranceSurveyor, String> address;
    @FXML
    private TableColumn<InsuranceSurveyor, String> email;
    @FXML
    private TableColumn<InsuranceSurveyor, String> phoneNumber;

    private AccountController accountController = new AccountController();
    private Account account;

    public void initData(ObservableList<InsuranceSurveyor> surveyors, InsuranceManager insuranceManager) {
        surveyorID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompanyAddress()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        companyName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompanyName()));

        ObservableList<InsuranceSurveyor> surveyorData = FXCollections.observableArrayList(surveyors);
        insuranceSurveyorTable.setItems(surveyorData);

        int accountID = insuranceManager.getAccountId();
        account = accountController.getAccountByID(accountID);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceManagerPage.fxml"));
                Parent root = loader.load();
                InsuranceManagerView insuranceManagerView = loader.getController();
                insuranceManagerView.initData(account);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}