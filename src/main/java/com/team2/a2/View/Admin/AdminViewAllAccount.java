package com.team2.a2.View.Admin;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.User.Account;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminViewAllAccount implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, Integer> accountId;

    @FXML
    private TableColumn<Account, Date> createdAtColumn;
    @FXML
    private TableColumn<Account, Date> updatedAtColumn;
    @FXML
    private TableColumn<Account, String> username;
    @FXML
    private TableColumn<Account, String> password;
    @FXML
    private TableColumn<Account, AccountType> type;

    private ObservableList<Account> originalAccountList;

    private Account account1;


    public void initData(ObservableList<Account> accounts, Account account) {
        originalAccountList = FXCollections.observableArrayList(accounts);
        accountId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        createdAtColumn.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getCreatedAt");
                Date createAtDate = (Date) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(createAtDate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });
        updatedAtColumn.setCellValueFactory(cellData -> {
            try {
                Method method = Claim.class.getMethod("getUpdatedAt");
                Date updateAtDate = (Date) method.invoke(cellData.getValue());
                return new SimpleObjectProperty<>(updateAtDate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        });
        username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        password.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        type.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getType()));

        accountTable.setItems(originalAccountList);
        account1 = account;
    }



    @Override
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
    }
}
