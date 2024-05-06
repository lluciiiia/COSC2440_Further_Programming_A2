package com.team2.a2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DependentView implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Button ViewInfoButton;
//    @FXML
//    private TableView<Dependent> dependentTableView;
//    @FXML
//    private TableColumn<Dependent, String> usernameColumn;
//    @FXML
//    private TableColumn<Dependent, String> passwordColumn;
//    @FXML
//    private TableColumn<Dependent, String> cusIDColumn;
//    @FXML
//    private TableColumn<Dependent, String> cusNameColumn;
//    @FXML
//    private TableColumn<Dependent, String> homeAddressColumn;
//    @FXML
//    private TableColumn<Dependent, String> cusEmailColumn;
//    @FXML
//    private TableColumn<Dependent, String> cusPhoneColumn;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ViewInfoButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DependentInformationPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



//        ObservableList<Dependent> dependentList = FXCollections.observableArrayList(
//                new Dependent("s101", "ddd", "s123456", "quang", "123 nguyen van linh", "123456", "cdquang@gmail.com"),
//                new Dependent("s102", "dd", "s123457", "quan", "123 nguyen van linh", "123456", "cdquan@gmail.com")
//        );
//
//        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
//        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
//        cusIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
//        cusNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        homeAddressColumn.setCellValueFactory(new PropertyValueFactory<>("homeAddress"));
//        cusEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
//        cusPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//
//        dependentTableView.setItems(dependentList);
    }

}
