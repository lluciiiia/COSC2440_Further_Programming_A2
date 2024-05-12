package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.FacadeImpl.AccountFacadeImpl;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DependentInformationView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    public void initData(Customer customer) {
        nameField.setText(customer.getName());
        phoneField.setText(customer.getPhoneNumber());
        emailField.setText(customer.getEmail());
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
         returnButton.setOnAction(event -> {
             try {
                 Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DependentPage.fxml")));
                 Scene scene = new Scene(root);
                 Stage stage = (Stage) returnButton.getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         });

//         loadData();
    }

//    void loadData() {
//
//        nameField.setText(customer.getName());
//        phoneField.setText(customer.getPhoneNumber());
//        emailField.setText(customer.getEmail());
//    }

}
