package com.team2.a2.View.Dependent;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ClaimView implements Initializable {
    @FXML
    private Button returnButton;

    @FXML
    private Text customerNameText;

    @FXML
    private TableView<Claim> claimTable;
    @FXML
    private TableColumn<Claim, String> claimID;
    @FXML
    private TableColumn<Claim, Date> claimDate;
    @FXML
    private TableColumn<Claim, Date> examDate;
    @FXML
    private TableColumn<Claim, Double> claimAmount;
    @FXML
    private TableColumn<Claim, Integer> customerID;
    @FXML
    private TableColumn<Claim, Boolean> isDocumentRequested;

    @FXML
    private TableColumn<Claim, String> status;

    private AccountController accountController = new AccountController();
    private Account account;

    public void initData(ObservableList<Claim> claims, Customer customer) {
        claimID.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getId");
                return new SimpleStringProperty(String.valueOf(method.invoke(cellData.getValue())));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        claimDate.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getClaimDate");
                Date claimDate = (Date) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(claimDate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        examDate.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getExamDate");
                Date examDate = (Date) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(examDate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        claimAmount
                .setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
        customerID.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomerId()).asObject());
        status.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getStatus");
                return new SimpleStringProperty(String.valueOf(method.invoke(cellData.getValue())));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        isDocumentRequested.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getDocumentRequested");
                Boolean documentRequested = (Boolean) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(documentRequested);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });

        ObservableList<Claim> claimsData = FXCollections.observableArrayList(claims);
        claimTable.setItems(claimsData);

        customerNameText.setText(customer.getName() + "'s claims");

        int accountID = customer.getAccountId();
        account = accountController.getAccountByID(accountID);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/DependentPage.fxml"));
                Parent root = loader.load();
                View dependentView = loader.getController();
                dependentView.initData(account);
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
