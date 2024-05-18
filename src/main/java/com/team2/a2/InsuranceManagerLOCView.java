package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.ClaimDocumentController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class InsuranceManagerLOCView implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button viewDocuments;
    @FXML
    private TableView<Claim> claimTable;
    @FXML
    private TableColumn<Claim, Integer> claimID;
    @FXML
    private TableColumn<Claim, Date> claimDate;
    @FXML
    private TableColumn<Claim, Date> examDate;
    @FXML
    private TableColumn<Claim, Double> amount;
    @FXML
    private TableColumn<Claim, ClaimStatus> status;
    @FXML
    private TableColumn<Claim, Boolean> isDocumentRequested;
    @FXML
    private TableColumn<Claim, Integer> cusID;

    @FXML
    private Button acceptClaim;
    @FXML
    private Button rejectClaim;

    private AccountController accountController = new AccountController();
    private Account account;
    private ClaimDocumentController claimDocumentController = new ClaimDocumentController();
    private ClaimController claimController = new ClaimController();
    private Claim selectedClaim;
    private ObservableList<Claim> claimsData;

    public void initData(ObservableList<Claim> claims, InsuranceManager insuranceManager) {
        claimID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

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

        amount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
        cusID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomerId()).asObject());
        status.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStatus()));
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

        claimsData = FXCollections.observableArrayList(claims);
        claimTable.setItems(claimsData);

        int accountID = insuranceManager.getAccountId();
        account = accountController.getAccountByID(accountID);

        claimTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedClaim = newSelection;
            }
        });
        viewDocuments.setOnAction(event -> viewDocumentsOfSelectedClaim());
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InsuranceManagerPage.fxml"));
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

        acceptClaim.setOnAction(event -> {
            if (selectedClaim != null) {
                if (selectedClaim.getStatus() == ClaimStatus.PROCESSING) {
                    claimController.updateClaimStatus(selectedClaim.getId(), ClaimStatus.ACCEPTED);
                    refreshTable();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Invalid Status", "Only claims with status 'PROCESSING' can be accepted.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "No selection", "Please select a claim to accept.");
            }
        });

        rejectClaim.setOnAction(event -> {
            if (selectedClaim != null) {
                if (selectedClaim.getStatus() == ClaimStatus.PROCESSING) {
                    claimController.updateClaimStatus(selectedClaim.getId(), ClaimStatus.REJECTED);
                    refreshTable();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Invalid Status", "Only claims with status 'PROCESSING' can be rejected.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "No selection", "Please select a claim to reject.");
            }
        });
    }

    private void viewDocumentsOfSelectedClaim() {
        Claim selectedClaim = claimTable.getSelectionModel().getSelectedItem();
        if (selectedClaim == null) {
            showAlert(Alert.AlertType.ERROR, "Selection Error", "Please select a claim to view documents.");
            return;
        }

        List<String> imageSources = claimDocumentController.getImageSourcesByClaimId(selectedClaim.getId());

        if (imageSources == null || imageSources.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "No Document", "No documents found for the selected claim.");
            return;
        }

        Stage stage = new Stage();
        VBox vbox = new VBox();
        for (String imageSource : imageSources) {
            ImageView imageView = new ImageView(new Image(imageSource));
            imageView.setFitWidth(800);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            vbox.getChildren().add(imageView);
        }

        ScrollPane scrollPane = new ScrollPane(vbox);
        Scene scene = new Scene(scrollPane, 800, 600);

        stage.setTitle("View Documents");
        stage.setScene(scene);
        stage.show();
    }

    private void refreshTable() {
        claimTable.getSelectionModel().clearSelection();
        claimsData.clear();
        claimsData.addAll(claimController.getAllClaims());
        claimTable.refresh();
    }

    private void showAlert(Alert.AlertType error, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
