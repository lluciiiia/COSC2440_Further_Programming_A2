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

public class AdminCreateCustomerAccountView implements Initializable {
    @FXML
    private Button nextToInsuranceCardButton;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextToInsuranceCardButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminCreateInsuranceCardPage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) nextToInsuranceCardButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}