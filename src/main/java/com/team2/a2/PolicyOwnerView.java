package com.team2.a2;

import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PolicyOwnerView implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Button ViewBeneficiaries;

    @FXML
    private Button CreateClaimButton;

    @FXML
    private Button CreateCusAccountButton;

    @FXML
    private Text nameText;

    private PolicyOwnerController policyOwnerController = new PolicyOwnerController();
    private CustomerController customerController = new CustomerController();

    private PolicyOwner policyOwner;

    public void initData(Account account) {
        policyOwner = policyOwnerController.getPolicyOwnerByAccountId(account.getId());
        nameText.setText("Welcome, " + policyOwner.getName());
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

//        CreateCusAccountButton.setOnAction(event -> {
//            try {
//                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PolicyOwnerCreateAccount.fxml")));
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) CreateCusAccountButton.getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        ViewBeneficiaries.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("PolicyOwnerCustomersPage.fxml")));
                Parent root = loader.load();
                PolicyOwnerCustomersView policyOwnerCustomersView = loader.getController();
                Task<List<Customer>> loadCustomersTask = new Task<>() {
                    @Override
                    protected List<Customer> call() throws Exception {
                        return customerController.getCustomersByPolicyOwnerId(policyOwner.getId());
                    }
                };

                loadCustomersTask.setOnSucceeded(workerStateEvent -> {
                    policyOwnerCustomersView.initData(FXCollections.observableArrayList(loadCustomersTask.getValue()), policyOwner);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ViewBeneficiaries.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                });

                loadCustomersTask.setOnFailed(workerStateEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to load claims");
                    alert.setContentText("Please try again later.");
                    alert.showAndWait();
                });

                new Thread(loadCustomersTask).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        CreateClaimButton.setOnAction(event -> {
//            try {
//                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PolicyOwnerCreateClaim.fxml")));
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) CreateClaimButton.getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

    }

}
