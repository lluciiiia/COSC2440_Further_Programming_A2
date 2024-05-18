package com.team2.a2;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.ClaimDocumentController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
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

public class InsuranceSurveyorLOCView implements Initializable {
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
    private TableColumn<Claim, Integer> cusID;
    @FXML
    private TableColumn<Claim, Boolean> isDocumentRequested;

    @FXML
    private Button requireMoreDocuments;
    @FXML
    private Button sendToManager;

    private AccountController accountController = new AccountController();
    private Account account;
    private ClaimDocumentController claimDocumentController = new ClaimDocumentController();
    private ClaimController claimController = new ClaimController();
    private Claim selectedClaim;
    private ObservableList<Claim> claimsData;

    public void initData(ObservableList<Claim> claims, InsuranceSurveyor insuranceSurveyor) {
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

        int accountID = insuranceSurveyor.getAccountId();
        account = accountController.getAccountByID(accountID);

        claimTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedClaim = newSelection;
            }
        });
        viewDocuments.setOnAction(event -> {
            try {
                viewDocumentsOfSelectedClaim();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InsuranceSurveyorPage.fxml"));
                Parent root = loader.load();
                InsuranceSurveyorView insuranceSurveyorView = loader.getController();
                insuranceSurveyorView.initData(account);
                Scene scene = new Scene(root);
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sendToManager.setOnAction(event -> {
            if (selectedClaim != null) {
                if (selectedClaim.getStatus() == ClaimStatus.NEW) {
                    try {
                        claimController.updateClaimStatus(selectedClaim.getId(), ClaimStatus.PROCESSING);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    refreshTable();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Invalid Status", "Only claims with status 'NEW' can be sent to the manager.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "No selection", "Please select a claim to send to the manager.");
            }
        });

        requireMoreDocuments.setOnAction(event -> {
            if (selectedClaim != null) {
                if (selectedClaim.getStatus() == ClaimStatus.NEW) {
                    if (!selectedClaim.getDocumentRequested()) {
                        try {
                            claimController.updateClaimDocumentRequested(selectedClaim.getId(), true);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        refreshTable();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Document Requested", "Documents have already been requested for this claim.");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Invalid Status", "Only claims with status 'NEW' can request more documents.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "No selection", "Please select a claim to request more documents.");
            }
        });
    }

    private void viewDocumentsOfSelectedClaim() throws Exception {
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
