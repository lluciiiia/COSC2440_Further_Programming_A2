package com.team2.a2;

import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
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

public class PolicyHolderView implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Button viewInfoButton;

    @FXML
    private TextField accountID;

    private CustomerController customerController = new CustomerController();
    private Customer customer;

    public void initData(Account account) {
        accountID.setText(String.valueOf(account.getId()));
        int accountIDValue = Integer.parseInt(accountID.getText());
        customer = customerController.getCustomer(accountIDValue);
    }

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

        viewInfoButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PolicyHolderInformationPage.fxml"));
                Parent root = loader.load();
                PolicyHolderInformationView policyHolderInformationView = loader.getController();
                policyHolderInformationView.initData(customer);
                Scene scene = new Scene(root);
                Stage stage = (Stage) viewInfoButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
