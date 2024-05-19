package com.team2.a2.View.InsuranceSurveyor;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.Log;
import com.team2.a2.Model.User.Account;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsuranceSurveyorLogHistoryView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TableView<Log> tableView;
    @FXML
    private TableColumn<Log, Integer> idColumn;
    @FXML
    private TableColumn<Log, Integer> accountId;
    @FXML
    private TableColumn<Log, String> roleColumn;
    @FXML
    private TableColumn<Log, Date> createAt;

    private ObservableList<Log> logsData;

    private Account account1;

    public void initData(ObservableList<Log> logs, Account account) {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        accountId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAccountId()).asObject());
        roleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getContent()));
        createAt.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreatedAt()));

        logsData = FXCollections.observableArrayList(logs);
        tableView.setItems(logsData);

        account1 = account;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team2/a2/InsuranceSurveyorPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorView insuranceSurveyorView = loader.getController();
                insuranceSurveyorView.initData(account1);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            }
        });
    }
}