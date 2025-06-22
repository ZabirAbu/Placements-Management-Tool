package com.example.plcmttrkr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable; //add comments here and placement controller
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProviderFormController extends LoginController implements Initializable {
    public List<String> listOfStudentNumbers = new ArrayList<String>();

    private String[] choice1 = {"Yes", "No"};

    //for the Placement Provider Details section
    private String[] choice2 = {"Yes", "No", "Unsure"};

    //for the Work factors section
    private String[] choice3 = {"Yes", "No (if No, please continue to section 6.)"};

    private String[] choice4 = {"Permanently", "Hybrid working e.g. two days in office, three days working from home", "Fully Remote"};

    // for the Policies and Insurance (UK Providers Only) section a) of the provider form
    private String[] choice5 = {"Yes", "No", "N/A (Crown/NHS Indemnity)"};

    // for the Policies and Insurance (UK Providers Only) section c) of the provider form
    private String[] choice6 = {"Yes", "No, but the placement student will provide advice, designs, or " +
            "professional services to people outside the business.",
            "No, this insurance is not applicable to the role/company."};

    //for the Policies and Insurance (Overseas Employers Only) section of the provider form
    private String[] choice7 = {"Yes", "No, but the student will be providing external advice or professional services in their role.",
            "No, but the student will not be providing any external advice or professional services."};

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private ScrollPane placementTeamScrollPane;
    @FXML
    private AnchorPane dragBar;
    @FXML
    private Button minimiseButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button providerFormButton;
    @FXML
    private Button helpbutton;
    @FXML
    private Label userTypeLabel;
    @FXML
    private TextField organisationNameTextField;
    @FXML
    private TextField placementAddressTextField;
    @FXML
    private TextField postcodeTextField;
    @FXML
    private TextField webAddressTextField;
    @FXML
    private TextField organisationActivitiesTextField;
    @FXML
    private TextField placementStudentNameTextField;
    @FXML
    private TextField nameOfMainContactTextField;
    @FXML
    private TextField contactJobTitleTextField;
    @FXML
    private TextField contactEmailField;
    @FXML
    private TextField contactTelNumberTextField;
    @FXML
    private TextField jobTitleTextField;
    @FXML
    private DatePicker roleStartDateDatePicker;
    @FXML
    private DatePicker roleEndDateDatePicker;
    @FXML
    private TextField workingHoursTextField;
    @FXML
    private ChoiceBox<String> probationPeriodChoiceBox;
    @FXML
    private TextField ifYesProbationPeriodTextField;
    @FXML
    private TextField endOfRoleExpectTextField;
    @FXML
    private ChoiceBox<String> hazardChoiceBox;
    @FXML
    private TextField ifYesHazardTextField;
    @FXML
    private ChoiceBox trainingChoiceBox;
    @FXML
    private TextField ifYesTrainingTextField;
    @FXML
    private ChoiceBox<String> workFromHomeChoiceBox;
    @FXML
    private ChoiceBox<String> frequencyWorkFromHomeChoiceBox;
    @FXML
    private TextField ifYesWorkFromHome;
    @FXML
    private ChoiceBox<String> workDiffSitesChoiceBox;
    @FXML
    private TextField ifYesworkDiffSitesTextField;
    @FXML
    private ChoiceBox<String> travelOutUKChoiceBox;
    @FXML
    private ChoiceBox<String> locationRiskChoiceBox;
    @FXML
    private TextField locationRiskTextField;
    @FXML
    private ChoiceBox<String> measuresChoiceBox;
    @FXML
    private TextField ifYesMeasuresTextField;
    @FXML
    private ChoiceBox<String> personalFactorsChoiceBox;
    @FXML
    private ChoiceBox<String> publicLiabilityInsChoiceBox;
    @FXML
    private TextField ifNoPublicLiabilityInsTextField;
    @FXML
    private TextField insProvNameTextField;
    @FXML
    private DatePicker insProvExpDateDatePicker;
    @FXML
    private ChoiceBox<String> EmpLiabilityInsChoiceBox;
    @FXML
    private TextField insEmpProvNameTextField;
    @FXML
    private DatePicker insEmpProvExpDateDatePicker;
    @FXML
    private TextField ifNoEmpLiabilityInsTextField;
    @FXML
    private ChoiceBox<String> IndemInsChoiceBox;
    @FXML
    private TextField IndemInsProvNameTextField;
    @FXML
    private DatePicker IndemInsExpDateDatePicker;
    @FXML
    private ChoiceBox<String> ins1ChoiceBox;
    @FXML
    private TextField ins1ProvNameTextField;
    @FXML
    private DatePicker ins1ExpDateDatePicker;
    @FXML
    private ChoiceBox<String> ins2PChoiceBox;
    @FXML
    private TextField ins2ProvNameTextField;
    @FXML
    private DatePicker ins2ExpDateDatePicker;
    @FXML
    private ChoiceBox<String> ins3PChoiceBox;
    @FXML
    private TextField ins3ProvNameTextField;
    @FXML
    private DatePicker ins3ExpDateDatePicker;
    @FXML
    private ChoiceBox<String> accidentsReportChoiceBox;
    @FXML
    private ChoiceBox<String> writtenHandSpolicyChoiceBox;
    @FXML
    private ChoiceBox<String> hAndStrainingChoiceBox;
    @FXML
    private ChoiceBox<String> siteVisitsChoiceBox;
    @FXML
    private TextField ifNoSiteVisitsTextField;
    @FXML
    private ChoiceBox<String> disclosureChoiceBox;
    @FXML
    private TextField ifYesDisclosureTextField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField providerStaffJobTitleTextField;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private TextField providerStaffNameTextField;
    @FXML
    private TextField providerStaffSignatureTextField;
    @FXML
    private ChoiceBox<String> organisationActivitiesChoiceBox;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button saveButton;
    private boolean sectionValid1 = false, sectionValid2 = false, sectionValid3 = false, sectionValid4 = false, sectionValid5 = false, sectionValid6 = false, sectionValid7 = false, sectionValid8 = false, sectionValid9 = false, sectionValid10 = false, sectionValid11 = false, sectionValid12 = false, sectionValid13 = false, sectionDeclaration = false;
    private String column1, column2, column3, column4, column5, column6, column7, column8, column9, column10;
    private String column11, column12, column13, column14, column15, column16, column17, column18, column19, column20;
    private String column21, column22, column23, column24, column25, column26, column27, column28, column29, column30;
    private String column31, column32, column33, column34, column35, column36, column37, column38, column39, column40;
    private String column41, column42, column43, column44, column45, column46, column47, column48, column49, column50;
    private String column51, column52, column53, column54, column55, column56, column57, column58, column59, column60;
    private String column61, column62, column63, column64;
    @FXML
    private Label studentErrorMessageLabel;
    @FXML
    private ComboBox<String> selectStudentComboBox;
    @FXML
    private Button selectButton;
    @FXML
    private Button viewStudentFormButton;
    @FXML
    private Button viewTutorFormButton;
    @FXML
    private Hyperlink provFormHyperlink1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        organisationActivitiesChoiceBox.getItems().addAll(choice2);
        probationPeriodChoiceBox.getItems().addAll(choice1);
        hazardChoiceBox.getItems().addAll(choice1);
        trainingChoiceBox.getItems().addAll(choice1);
        workFromHomeChoiceBox.getItems().addAll(choice3);
        frequencyWorkFromHomeChoiceBox.getItems().addAll(choice4);
        workDiffSitesChoiceBox.getItems().addAll(choice1);
        travelOutUKChoiceBox.getItems().addAll(choice1);
        locationRiskChoiceBox.getItems().addAll(choice1);
        measuresChoiceBox.getItems().addAll(choice1);
        personalFactorsChoiceBox.getItems().addAll(choice1);
        publicLiabilityInsChoiceBox.getItems().addAll(choice5);
        EmpLiabilityInsChoiceBox.getItems().addAll(choice1);
        IndemInsChoiceBox.getItems().addAll(choice6);
        ins1ChoiceBox.getItems().addAll(choice1);
        ins2PChoiceBox.getItems().addAll(choice1);
        ins3PChoiceBox.getItems().addAll(choice7);
        accidentsReportChoiceBox.getItems().addAll(choice1);
        writtenHandSpolicyChoiceBox.getItems().addAll(choice1);
        hAndStrainingChoiceBox.getItems().addAll(choice1);
        disclosureChoiceBox.getItems().addAll(choice1);
        siteVisitsChoiceBox.getItems().addAll(choice1);

        try {
            DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            String getRows = "SELECT * FROM studentForm";
            ResultSet queryResult = statement.executeQuery(getRows);
            ;
            while (queryResult.next()) {
                listOfStudentNumbers.add(queryResult.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        selectStudentComboBox.getItems().addAll(listOfStudentNumbers);
    }

    public void dragBarOnAction(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void dragBarOnMovementAction(MouseEvent event) {
        Stage stage = (Stage) dragBar.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void minimiseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) minimiseButton.getScene().getWindow();
        stage.setIconified(true);
    }

    public void logoutButtonOnAction(ActionEvent event) throws IOException {
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Logout");
        logoutAlert.setHeaderText("Are you sure you want to log out?");

        if (logoutAlert.showAndWait().get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(loginScene);
            loginStage.show();
        }
    }

    public void homeButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("placement_teamDashboard.fxml"));
        Parent root = fxmlLoader.load();
        Scene dashboardScene = new Scene(root);
        PlacementTeamDashboardController placementdashboardC = fxmlLoader.getController();
        placementdashboardC.setName(foreN, surN);
        placementdashboardC.setPieChart();
        Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }

    public void helpButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("provider_help.fxml"));
        Parent root = fxmlLoader.load();
        ProviderHelpController ProviderHelpC = fxmlLoader.getController();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) helpbutton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }

    @FXML
    private void goToProvUrl1() {
        openProviderFormURL("https://www.gov.uk/employers-liability-insurance");
    }

    public void openProviderFormURL(String url) {
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                URI uri = new URI(url);
                desktop.browse(uri);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unfortunately, we're unable to open the link. Please check your internet connection and try again.");
        }
    }

    public void checkSection1() {
        errorMessageLabel.setText("");
        if (organisationNameTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 1(A)!");
            sectionValid1 = false;
            return;
        } else {
            sectionValid1 = true;
        }
        if (placementAddressTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 1(B)!");
            sectionValid1 = false;
            return;
        } else {
            sectionValid1 = true;
        }
        if (postcodeTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 1(C)!");
            sectionValid1 = false;
            return;
        } else {
            sectionValid1 = true;
        }
        if (webAddressTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 1(D)!");
            sectionValid1 = false;
            return;
        } else {
            sectionValid1 = true;
        }
        if (organisationActivitiesChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 1(E)!");
            sectionValid1 = false;
            return;
        } else {
            sectionValid1 = true;
        }
        if (("Yes").equals(organisationActivitiesChoiceBox.getValue()) && organisationActivitiesTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 1(F)!");
            sectionValid1 = false;
        } else {
            sectionValid1 = true;
        }
    }

    public void checkSection2() {
        errorMessageLabel.setText("");
        if (placementStudentNameTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 2(A)!!");
            sectionValid2 = false;
        } else {
            sectionValid2 = true;
        }
    }

    public void checkSection3() {
        errorMessageLabel.setText("");
        if (nameOfMainContactTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 3(A)!");
            sectionValid3 = false;
            return;
        } else {
            sectionValid3 = true;
        }
        if (contactJobTitleTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 3(B)!");
            sectionValid3 = false;
            return;
        } else {
            sectionValid3 = true;
        }
        if (contactEmailField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 3(C)!");
            sectionValid3 = false;
            return;
        } else {
            sectionValid3 = true;
        }
        if (contactTelNumberTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 3(D)!");
            sectionValid3 = false;
            return;
        } else {
            sectionValid3 = true;
        }
    }

    public void checkSection4() {
        errorMessageLabel.setText("");
        if (jobTitleTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 4(A)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (roleStartDateDatePicker.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4(B)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (roleEndDateDatePicker.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4(C)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (workingHoursTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 4(D)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (probationPeriodChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4(E)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (("Yes").equals(probationPeriodChoiceBox.getValue()) && ifYesProbationPeriodTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 4(E) - Provide Information!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (endOfRoleExpectTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 4(F)!");
            sectionValid4 = false;
        } else {
            sectionValid4 = true;
        }
    }

    public void checkSection5() {
        errorMessageLabel.setText("");
        if (hazardChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5(A)!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (("Yes").equals(hazardChoiceBox.getValue()) && ifYesHazardTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 5(A) - Provide Information!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (trainingChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5(B)!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (("Yes").equals(trainingChoiceBox.getValue()) && ifYesTrainingTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 5(B) - Provide Information!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (workFromHomeChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5(C)!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (("Yes").equals(workFromHomeChoiceBox.getValue()) && frequencyWorkFromHomeChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5(C) - Provide Frequency!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (("Yes").equals(workFromHomeChoiceBox.getValue()) && ifYesWorkFromHome.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 5(D)!");
            sectionValid5 = false;
        } else {
            sectionValid5 = true;
        }
    }

    public void checkSection6() {
        if (workDiffSitesChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 6(A)!");
            sectionValid6 = false;
            return;
        } else {
            sectionValid6 = true;
        }
        if (("Yes").equals(workDiffSitesChoiceBox.getValue()) && ifYesworkDiffSitesTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 6(A) - Provide Information!");
            sectionValid6 = false;
            return;
        } else {
            sectionValid6 = true;
        }
        if (travelOutUKChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 6(B)!");
            sectionValid6 = false;
        } else {
            sectionValid6 = true;
        }
    }

    public void checkSection7() {
        errorMessageLabel.setText("");
        if (locationRiskChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 7(A)!");
            sectionValid7 = false;
            return;
        } else {
            sectionValid7 = true;
        }
        if (("Yes").equals(locationRiskChoiceBox.getValue()) && locationRiskTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 7(A) - Provide Information!");
            sectionValid7 = false;
        } else {
            sectionValid7 = true;
        }
    }

    public void checkSection8() {
        errorMessageLabel.setText("");
        if (measuresChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 8(A)!");
            sectionValid8 = false;
            return;
        } else {
            sectionValid8 = true;
        }
        if (("Yes").equals(measuresChoiceBox.getValue()) && ifYesMeasuresTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 8(A) - Provide Information!");
            sectionValid8 = false;
        } else {
            sectionValid8 = true;
        }
    }

    public void checkSection9() {
        errorMessageLabel.setText("");
        if (personalFactorsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 9(A)!");
            sectionValid9 = false;
        } else {
            sectionValid9 = true;
        }
    }

    public void checkSection12() {
        errorMessageLabel.setText("");
        if (accidentsReportChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 12(A)!");
            sectionValid12 = false;
            return;
        } else {
            sectionValid12 = true;
        }
        if (writtenHandSpolicyChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 12(B)!");
            sectionValid12 = false;
            return;
        } else {
            sectionValid12 = true;
        }
        if (hAndStrainingChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 12(C)!");
            sectionValid12 = false;
        } else {
            sectionValid12 = true;
        }
    }

    public void checkSection13() {
        errorMessageLabel.setText("");
        if (siteVisitsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 13(A)!");
            sectionValid13 = false;
            return;
        } else {
            sectionValid13 = true;
        }
        if (("No").equals(siteVisitsChoiceBox.getValue()) && ifNoSiteVisitsTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 13(A) - Provide Reasoning!");
            sectionValid13 = false;
            return;
        } else {
            sectionValid13 = true;
        }
        if (disclosureChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 13(B)!");
            sectionValid13 = false;
            return;

        } else {
            sectionValid13 = true;
        }
        if (("Yes").equals(disclosureChoiceBox.getValue()) && ifYesDisclosureTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 13(A) - Provide Information!");
            sectionValid13 = false;
        } else {
            sectionValid13 = true;
        }
    }

    public void checkDeclaration() {
        errorMessageLabel.setText("");
        if (providerStaffNameTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete declaration (provide staff name)!");
            sectionDeclaration = false;
            return;
        } else {
            sectionDeclaration = true;
        }
        if (providerStaffJobTitleTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete declaration (provide job title)!");
            sectionDeclaration = false;
            return;
        } else {
            sectionDeclaration = true;
        }
        if (providerStaffSignatureTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete declaration (provide signature)!");
            sectionDeclaration = false;
            return;
        } else {
            sectionDeclaration = true;
        }
        if (dateDatePicker.getValue() == null) {
            errorMessageLabel.setText("Please complete declaration (provide date)!");
            sectionDeclaration = false;
        } else {
            sectionDeclaration = true;
        }
    }

    public void checkSection10() {
        errorMessageLabel.setText("");
        if (publicLiabilityInsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 10 (1)!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (("Yes").equals(publicLiabilityInsChoiceBox.getValue()) && (insProvNameTextField.getText().isBlank() || insProvExpDateDatePicker.getValue() == null)) {
            errorMessageLabel.setText("Please complete section 10(1(A))!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (("No").equals(publicLiabilityInsChoiceBox.getValue()) && ifNoPublicLiabilityInsTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 10(1(A))!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (EmpLiabilityInsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 10 (2)!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (("Yes").equals(EmpLiabilityInsChoiceBox.getValue()) && (insEmpProvNameTextField.getText().isBlank() || insEmpProvExpDateDatePicker.getValue() == null)) {
            errorMessageLabel.setText("Please complete section 10 (2(A))!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (("No").equals(EmpLiabilityInsChoiceBox.getValue()) && ifNoEmpLiabilityInsTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 10 (2(A))!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (IndemInsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 10 (3)!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (("Yes").equals(IndemInsChoiceBox.getValue()) && (IndemInsProvNameTextField.getText().isBlank() || IndemInsExpDateDatePicker.getValue() == null)) {
            errorMessageLabel.setText("Please complete section 10 (3(A))!");
            sectionValid10 = false;
        } else {
            sectionValid10 = true;
        }
    }

    public void checkSection11() {
        if (ins1ChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 11 (1)!");
            sectionValid11 = false;
            return;
        } else {
            sectionValid11 = true;
        }
        if (("Yes").equals(ins1ChoiceBox.getValue()) && (ins1ProvNameTextField.getText().isBlank() || ins1ExpDateDatePicker.getValue() == null)) {
            errorMessageLabel.setText("Please complete section 11 (1(A))!");
            sectionValid11 = false;
            return;
        } else {
            sectionValid11 = true;
        }
        if (ins2PChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 11 (2)!");
            sectionValid11 = false;
            return;
        } else {
            sectionValid11 = true;
        }
        if (("Yes").equals(ins2PChoiceBox.getValue()) && (ins2ProvNameTextField.getText().isBlank() || ins2ExpDateDatePicker.getValue() == null)) {
            errorMessageLabel.setText("Please complete section 11 (2(A))!");
            sectionValid11 = false;
            return;
        } else {
            sectionValid11 = true;
        }
        if (ins3PChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 11 (3)!");
            sectionValid11 = false;
            return;
        } else {
            sectionValid11 = true;
        }
        if (("Yes").equals(ins3PChoiceBox.getValue()) && (ins3ProvNameTextField.getText().isBlank() || ins3ExpDateDatePicker.getValue() == null)) {
            errorMessageLabel.setText("Please complete section 11 (3(A))!");
            sectionValid11 = false;
        } else {
            sectionValid11 = true;
            return;
        }
    }

    public void submitButtonOnAction(ActionEvent event) {
        errorMessageLabel.setText("");
        if (selectStudentComboBox.getValue() == null) {
            errorMessageLabel.setText("Please select a student!");
            return;
        }
        checkSection1();
        if (sectionValid1) {
            System.out.print("Sec 1 valid");
            checkSection2();
        }
        else {
            return;
        }
        if (sectionValid2) {
            System.out.print("Sec 2 valid");
            checkSection3();
        }
        else {
            return;
        }
        if (sectionValid3) {
            System.out.print("Sec 3 valid");
            checkSection4();
        }
        else {
            return;
        }
        if (sectionValid4) {
            System.out.print("Sec 4 valid");
            checkSection5();
        }
        else {
            return;
        }
        if (sectionValid5) {
            System.out.print("Sec 5 valid");
            checkSection6();
        }
        else {
            return;
        }
        if (sectionValid6) {
            System.out.print("Sec 6 valid");
            checkSection7();
        }
        else {
            return;
        }
        if (sectionValid7) {
            System.out.print("Sec 7 valid");
            checkSection8();
        }
        else {
            return;
        }
        if (sectionValid8) {
            System.out.print("Sec 8 valid");
            checkSection9();
        }
        else {
            return;
        }
        if (sectionValid9) {
            System.out.print("Sec 9 valid");
            checkSection12();
        }
        else {
            return;
        }
        if (sectionValid12) {
            System.out.print("Sec 12 valid");
            checkSection13();
        }
        else {
            return;
        }
        if (sectionValid13) {
            System.out.print("Sec 13 valid");
            checkDeclaration();
        }
        else {
            return;
        }
        if (sectionDeclaration) {
            System.out.print("Dec valid");
            submitForm();
        }
    }

    public void saveForm() {
        try {
            DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            String rowExsistsQuery = "SELECT count(1) FROM savedProviderForm WHERE username = '" + userN + "'";

            String insertFields = "INSERT INTO savedProviderForm(username, nameOfOrganisation, addressPlace, postCode, webAddress, organisationUndertakeActivity, organisationUndertakeActivityDetails, studentName, nameOfMainContact, contactJobTitle, contactEmail, contactTelephoneNumber, jobTitle, roleStartDate, roleEndDate, workingHoursPerWeek, roleIncludeProbationPeriod, roleIncludeProbationPeriodDetails, roleEndStudentAchievement, exposeStudentToHazards, exposeStudentToHazardsDetails, studentRequiresTraining, studentRequiresTrainingDetails, studentWorkingFromHome, howFrequentlyWorkHome, studentMonitoringFromHome, workFromDifferentSite, workFromDifferentSiteDetails, placementRequireTravelAbroad, isOrganisationsLocationRiskyToStudent, isOrganisationsLocationRiskyToStudentDetails, shouldStudentTakePercaution, shouldStudentTakePercautionDetails, willOrganisationSupportStudent, ukPublicInsurance, ukPublicInsuranceProviderName, ukPublicInsuranceExpiryDate, ukPublicInsuranceNo, ukEmployerInsurance, ukEmployerInsuranceProviderName, ukEmployerInsuranceExpiryDate, ukEmployerInsuranceNo, ukProfessionalInsurance, ukProfessionalInsuranceProviderName, ukProfessionalInsuranceExpiryDate, nonUkPublicInsurance, nonUkPublicInsuranceProviderName, nonUkPublicInsuranceExpiryDate, nonUkStudentInjuryInsurance, nonUkStudentInjuryInsuranceProviderName, nonUkStudentInjuryInsuranceExpiryDate, nonUkCompensationInsurance, nonUkCompensationInsuranceProviderName, nonUkCompensationInsuranceExpiryDate, accidentsReportingProcedure, healthPolicy, healthTraining, staffUndertakeSiteVisit, staffUndertakeSiteVisitDetails, confidentialityPlacement, confidentialityPlacementDetails, formFillerName, formFillerJobTitle, formFillerDigitalSignature, formFilledDate, studentNumber) VALUES ('";
            String insertValues = userN + "','" + organisationNameTextField.getText() + "','" + placementAddressTextField.getText() + "','" + postcodeTextField.getText() + "','" + webAddressTextField.getText() + "','" + organisationActivitiesChoiceBox.getValue() + "','" + organisationActivitiesTextField.getText() + "','" + placementStudentNameTextField.getText() + "','" + nameOfMainContactTextField.getText() + "','" + contactJobTitleTextField.getText() + "','" + contactEmailField.getText() + "','" + contactTelNumberTextField.getText() + "','" + jobTitleTextField.getText() + "','" + roleStartDateDatePicker.getValue() + "','" + roleEndDateDatePicker.getValue() + "','" + workingHoursTextField.getText() + "','" + probationPeriodChoiceBox.getValue() + "','" + ifYesProbationPeriodTextField.getText() + "','" + endOfRoleExpectTextField.getText() + "','" + hazardChoiceBox.getValue() + "','" + ifYesHazardTextField.getText() + "','" + trainingChoiceBox.getValue() + "','" + ifYesTrainingTextField.getText() + "','" + workFromHomeChoiceBox.getValue() + "','" + frequencyWorkFromHomeChoiceBox.getValue() + "','" + ifYesWorkFromHome.getText() + "','" + workDiffSitesChoiceBox.getValue() + "','" + ifYesworkDiffSitesTextField.getText() + "','" + travelOutUKChoiceBox.getValue() + "','" + locationRiskChoiceBox.getValue() + "','" + locationRiskTextField.getText() + "','" + measuresChoiceBox.getValue() + "','" + ifYesMeasuresTextField.getText() + "','" + personalFactorsChoiceBox.getValue() + "','" + publicLiabilityInsChoiceBox.getValue() + "','" + insProvNameTextField.getText() + "','" + insProvExpDateDatePicker.getValue() + "','" + ifNoPublicLiabilityInsTextField.getText() + "','" + EmpLiabilityInsChoiceBox.getValue() + "','" + insEmpProvNameTextField.getText() + "','" + insEmpProvExpDateDatePicker.getValue() + "','" + ifNoEmpLiabilityInsTextField.getText() + "','" + IndemInsChoiceBox.getValue() + "','" + IndemInsProvNameTextField.getText() + "','" + IndemInsExpDateDatePicker.getValue() + "','" + ins1ChoiceBox.getValue() + "','" + ins1ProvNameTextField.getText() + "','" + ins1ExpDateDatePicker.getValue() + "','" + ins2PChoiceBox.getValue() + "','" + ins2ProvNameTextField.getText() + "','" + ins2ExpDateDatePicker.getValue() + "','" + ins3PChoiceBox.getValue() + "','" + ins3ProvNameTextField.getText() + "','" + ins3ExpDateDatePicker.getValue() + "','" + accidentsReportChoiceBox.getValue() + "','" + writtenHandSpolicyChoiceBox.getValue() + "','" + hAndStrainingChoiceBox.getValue() + "','" + siteVisitsChoiceBox.getValue() + "','" + ifNoSiteVisitsTextField.getText() + "','" + disclosureChoiceBox.getValue() + "','" + ifYesDisclosureTextField.getText() + "','" + providerStaffNameTextField.getText() + "','" + providerStaffJobTitleTextField.getText() + "','" + providerStaffSignatureTextField.getText() + "','" + dateDatePicker.getValue() + "','" + selectStudentComboBox.getValue() + "')";

            String updateFields = "UPDATE savedProviderForm SET nameOfOrganisation='" + organisationNameTextField.getText()
                    + "', addressPlace='" + placementAddressTextField.getText()
                    + "', postCode='" + postcodeTextField.getText()
                    + "', webAddress='" + webAddressTextField.getText()
                    + "', organisationUndertakeActivity='" + organisationActivitiesChoiceBox.getValue()
                    + "', organisationUndertakeActivityDetails='" + organisationActivitiesTextField.getText()
                    + "', studentName='" + placementStudentNameTextField.getText()
                    + "', nameOfMainContact='" + nameOfMainContactTextField.getText()
                    + "', contactJobTitle='" + contactJobTitleTextField.getText()
                    + "', contactEmail='" + contactEmailField.getText()
                    + "', contactTelephoneNumber='" + contactTelNumberTextField.getText()
                    + "', jobTitle='" + jobTitleTextField.getText()
                    + "', roleStartDate='" + roleStartDateDatePicker.getValue()
                    + "', roleEndDate='" + roleEndDateDatePicker.getValue()
                    + "', workingHoursPerWeek='" + workingHoursTextField.getText()
                    + "', roleIncludeProbationPeriod='" + probationPeriodChoiceBox.getValue()
                    + "', roleIncludeProbationPeriodDetails='" + ifYesProbationPeriodTextField.getText()
                    + "', roleEndStudentAchievement='" + endOfRoleExpectTextField.getText()
                    + "', exposeStudentToHazards='" + hazardChoiceBox.getValue()
                    + "', exposeStudentToHazardsDetails='" + ifYesHazardTextField.getText()
                    + "', studentRequiresTraining='" + trainingChoiceBox.getValue()
                    + "', studentRequiresTrainingDetails='" + ifYesTrainingTextField.getText()
                    + "', studentWorkingFromHome='" + workFromHomeChoiceBox.getValue()
                    + "', howFrequentlyWorkHome='" + frequencyWorkFromHomeChoiceBox.getValue()
                    + "', studentMonitoringFromHome='" + ifYesWorkFromHome.getText()
                    + "', workFromDifferentSite='" + workDiffSitesChoiceBox.getValue()
                    + "', workFromDifferentSiteDetails='" + ifYesworkDiffSitesTextField.getText()
                    + "', placementRequireTravelAbroad='" + travelOutUKChoiceBox.getValue()
                    + "', isOrganisationsLocationRiskyToStudent='" + locationRiskChoiceBox.getValue()
                    + "', isOrganisationsLocationRiskyToStudentDetails='" + locationRiskTextField.getText()
                    + "', shouldStudentTakePercaution='" + measuresChoiceBox.getValue()
                    + "', shouldStudentTakePercautionDetails='" + ifYesMeasuresTextField.getText()
                    + "', willOrganisationSupportStudent='" + personalFactorsChoiceBox.getValue()
                    + "', ukPublicInsurance='" + publicLiabilityInsChoiceBox.getValue()
                    + "', ukPublicInsuranceProviderName='" + insProvNameTextField.getText()
                    + "', ukPublicInsuranceExpiryDate='" + insProvExpDateDatePicker.getValue()
                    + "', ukPublicInsuranceNo='" + ifNoPublicLiabilityInsTextField.getText()
                    + "', ukEmployerInsurance='" + EmpLiabilityInsChoiceBox.getValue()
                    + "', ukEmployerInsuranceProviderName='" + insEmpProvNameTextField.getText()
                    + "', ukEmployerInsuranceExpiryDate='" + insEmpProvExpDateDatePicker.getValue()
                    + "', ukEmployerInsuranceNo='" + ifNoEmpLiabilityInsTextField.getText()
                    + "', ukProfessionalInsurance='" + IndemInsChoiceBox.getValue()
                    + "', ukProfessionalInsuranceProviderName='" + IndemInsProvNameTextField.getText()
                    + "', ukProfessionalInsuranceExpiryDate='" + IndemInsExpDateDatePicker.getValue()
                    + "', nonUkPublicInsurance='" + ins1ChoiceBox.getValue()
                    + "', nonUkPublicInsuranceProviderName='" + ins1ProvNameTextField.getText()
                    + "', nonUkPublicInsuranceExpiryDate='" + ins1ExpDateDatePicker.getValue()
                    + "', nonUkStudentInjuryInsurance='" + ins2PChoiceBox.getValue()
                    + "', nonUkStudentInjuryInsuranceProviderName='" + ins2ProvNameTextField.getText()
                    + "', nonUkStudentInjuryInsuranceExpiryDate='" + ins2ExpDateDatePicker.getValue()
                    + "', nonUkCompensationInsurance='" + ins3PChoiceBox.getValue()
                    + "', nonUkCompensationInsuranceProviderName='" + ins3ProvNameTextField.getText()
                    + "', nonUkCompensationInsuranceExpiryDate='" + ins3ExpDateDatePicker.getValue()
                    + "', accidentsReportingProcedure='" + accidentsReportChoiceBox.getValue()
                    + "', healthPolicy='" + writtenHandSpolicyChoiceBox.getValue()
                    + "', healthTraining='" + hAndStrainingChoiceBox.getValue()
                    + "', staffUndertakeSiteVisit='" + siteVisitsChoiceBox.getValue()
                    + "', staffUndertakeSiteVisitDetails='" + ifNoSiteVisitsTextField.getText()
                    + "', confidentialityPlacement='" + disclosureChoiceBox.getValue()
                    + "', confidentialityPlacementDetails='" + ifYesDisclosureTextField.getText()
                    + "', formFillerName='" + providerStaffNameTextField.getText()
                    + "', formFillerJobTitle='" + providerStaffJobTitleTextField.getText()
                    + "', formFillerDigitalSignature='" + providerStaffSignatureTextField.getText()
                    + "', formFilledDate='" + dateDatePicker.getValue()
                    + "', username ='" + userN
                    + "' WHERE studentNumber = '" + selectStudentComboBox.getValue() + "'";

            ResultSet queryResult = statement.executeQuery(rowExsistsQuery);
            String insertToRegister = insertFields + insertValues;

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    try {
                        statement.executeUpdate(updateFields);
                        errorMessageLabel.setText("Your form has been successfully saved!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                } else {
                    try {
                        statement.executeUpdate(insertToRegister);
                        errorMessageLabel.setText("Your form has been successfully saved!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void submitForm() {
        saveForm();
        errorMessageLabel.setText("");
        try {
            DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            String rowExsistsQuery = "SELECT count(1) FROM providerForm WHERE username = '" + userN + "'";


            String insertFields = "INSERT INTO providerForm(username, nameOfOrganisation, addressPlace, postCode, webAddress, organisationUndertakeActivity, organisationUndertakeActivityDetails, studentName, nameOfMainContact, contactJobTitle, contactEmail, contactTelephoneNumber, jobTitle, roleStartDate, roleEndDate, workingHoursPerWeek, roleIncludeProbationPeriod, roleIncludeProbationPeriodDetails, roleEndStudentAchievement, exposeStudentToHazards, exposeStudentToHazardsDetails, studentRequiresTraining, studentRequiresTrainingDetails, studentWorkingFromHome, howFrequentlyWorkHome, studentMonitoringFromHome, workFromDifferentSite, workFromDifferentSiteDetails, placementRequireTravelAbroad, isOrganisationsLocationRiskyToStudent, isOrganisationsLocationRiskyToStudentDetails, shouldStudentTakePercaution, shouldStudentTakePercautionDetails, willOrganisationSupportStudent, ukPublicInsurance, ukPublicInsuranceProviderName, ukPublicInsuranceExpiryDate, ukPublicInsuranceNo, ukEmployerInsurance, ukEmployerInsuranceProviderName, ukEmployerInsuranceExpiryDate, ukEmployerInsuranceNo, ukProfessionalInsurance, ukProfessionalInsuranceProviderName, ukProfessionalInsuranceExpiryDate, nonUkPublicInsurance, nonUkPublicInsuranceProviderName, nonUkPublicInsuranceExpiryDate, nonUkStudentInjuryInsurance, nonUkStudentInjuryInsuranceProviderName, nonUkStudentInjuryInsuranceExpiryDate, nonUkCompensationInsurance, nonUkCompensationInsuranceProviderName, nonUkCompensationInsuranceExpiryDate, accidentsReportingProcedure, healthPolicy, healthTraining, staffUndertakeSiteVisit, staffUndertakeSiteVisitDetails, confidentialityPlacement, confidentialityPlacementDetails, formFillerName, formFillerJobTitle, formFillerDigitalSignature, formFilledDate, studentNumber) VALUES ('";
            String insertValues = userN + "','" + organisationNameTextField.getText() + "','" + placementAddressTextField.getText() + "','" + postcodeTextField.getText() + "','" + webAddressTextField.getText() + "','" + organisationActivitiesChoiceBox.getValue() + "','" + organisationActivitiesTextField.getText() + "','" + placementStudentNameTextField.getText() + "','" + nameOfMainContactTextField.getText() + "','" + contactJobTitleTextField.getText() + "','" + contactEmailField.getText() + "','" + contactTelNumberTextField.getText() + "','" + jobTitleTextField.getText() + "','" + roleStartDateDatePicker.getValue() + "','" + roleEndDateDatePicker.getValue() + "','" + workingHoursTextField.getText() + "','" + probationPeriodChoiceBox.getValue() + "','" + ifYesProbationPeriodTextField.getText() + "','" + endOfRoleExpectTextField.getText() + "','" + hazardChoiceBox.getValue() + "','" + ifYesHazardTextField.getText() + "','" + trainingChoiceBox.getValue() + "','" + ifYesTrainingTextField.getText() + "','" + workFromHomeChoiceBox.getValue() + "','" + frequencyWorkFromHomeChoiceBox.getValue() + "','" + ifYesWorkFromHome.getText() + "','" + workDiffSitesChoiceBox.getValue() + "','" + ifYesworkDiffSitesTextField.getText() + "','" + travelOutUKChoiceBox.getValue() + "','" + locationRiskChoiceBox.getValue() + "','" + locationRiskTextField.getText() + "','" + measuresChoiceBox.getValue() + "','" + ifYesMeasuresTextField.getText() + "','" + personalFactorsChoiceBox.getValue() + "','" + publicLiabilityInsChoiceBox.getValue() + "','" + insProvNameTextField.getText() + "','" + insProvExpDateDatePicker.getValue() + "','" + ifNoPublicLiabilityInsTextField.getText() + "','" + EmpLiabilityInsChoiceBox.getValue() + "','" + insEmpProvNameTextField.getText() + "','" + insEmpProvExpDateDatePicker.getValue() + "','" + ifNoEmpLiabilityInsTextField.getText() + "','" + IndemInsChoiceBox.getValue() + "','" + IndemInsProvNameTextField.getText() + "','" + IndemInsExpDateDatePicker.getValue() + "','" + ins1ChoiceBox.getValue() + "','" + ins1ProvNameTextField.getText() + "','" + ins1ExpDateDatePicker.getValue() + "','" + ins2PChoiceBox.getValue() + "','" + ins2ProvNameTextField.getText() + "','" + ins2ExpDateDatePicker.getValue() + "','" + ins3PChoiceBox.getValue() + "','" + ins3ProvNameTextField.getText() + "','" + ins3ExpDateDatePicker.getValue() + "','" + accidentsReportChoiceBox.getValue() + "','" + writtenHandSpolicyChoiceBox.getValue() + "','" + hAndStrainingChoiceBox.getValue() + "','" + siteVisitsChoiceBox.getValue() + "','" + ifNoSiteVisitsTextField.getText() + "','" + disclosureChoiceBox.getValue() + "','" + ifYesDisclosureTextField.getText() + "','" + providerStaffNameTextField.getText() + "','" + providerStaffJobTitleTextField.getText() + "','" + providerStaffSignatureTextField.getText() + "','" + dateDatePicker.getValue() + "','" + selectStudentComboBox.getValue() + "')";

            String updateFields = "UPDATE providerForm SET nameOfOrganisation='" + organisationNameTextField.getText()
                    + "', addressPlace='" + placementAddressTextField.getText()
                    + "', postCode='" + postcodeTextField.getText()
                    + "', webAddress='" + webAddressTextField.getText()
                    + "', organisationUndertakeActivity='" + organisationActivitiesChoiceBox.getValue()
                    + "', organisationUndertakeActivityDetails='" + organisationActivitiesTextField.getText()
                    + "', studentName='" + placementStudentNameTextField.getText()
                    + "', nameOfMainContact='" + nameOfMainContactTextField.getText()
                    + "', contactJobTitle='" + contactJobTitleTextField.getText()
                    + "', contactEmail='" + contactEmailField.getText()
                    + "', contactTelephoneNumber='" + contactTelNumberTextField.getText()
                    + "', jobTitle='" + jobTitleTextField.getText()
                    + "', roleStartDate='" + roleStartDateDatePicker.getValue()
                    + "', roleEndDate='" + roleEndDateDatePicker.getValue()
                    + "', workingHoursPerWeek='" + workingHoursTextField.getText()
                    + "', roleIncludeProbationPeriod='" + probationPeriodChoiceBox.getValue()
                    + "', roleIncludeProbationPeriodDetails='" + ifYesProbationPeriodTextField.getText()
                    + "', roleEndStudentAchievement='" + endOfRoleExpectTextField.getText()
                    + "', exposeStudentToHazards='" + hazardChoiceBox.getValue()
                    + "', exposeStudentToHazardsDetails='" + ifYesHazardTextField.getText()
                    + "', studentRequiresTraining='" + trainingChoiceBox.getValue()
                    + "', studentRequiresTrainingDetails='" + ifYesTrainingTextField.getText()
                    + "', studentWorkingFromHome='" + workFromHomeChoiceBox.getValue()
                    + "', howFrequentlyWorkHome='" + frequencyWorkFromHomeChoiceBox.getValue()
                    + "', studentMonitoringFromHome='" + ifYesWorkFromHome.getText()
                    + "', workFromDifferentSite='" + workDiffSitesChoiceBox.getValue()
                    + "', workFromDifferentSiteDetails='" + ifYesworkDiffSitesTextField.getText()
                    + "', placementRequireTravelAbroad='" + travelOutUKChoiceBox.getValue()
                    + "', isOrganisationsLocationRiskyToStudent='" + locationRiskChoiceBox.getValue()
                    + "', isOrganisationsLocationRiskyToStudentDetails='" + locationRiskTextField.getText()
                    + "', shouldStudentTakePercaution='" + measuresChoiceBox.getValue()
                    + "', shouldStudentTakePercautionDetails='" + ifYesMeasuresTextField.getText()
                    + "', willOrganisationSupportStudent='" + personalFactorsChoiceBox.getValue()
                    + "', ukPublicInsurance='" + publicLiabilityInsChoiceBox.getValue()
                    + "', ukPublicInsuranceProviderName='" + insProvNameTextField.getText()
                    + "', ukPublicInsuranceExpiryDate='" + insProvExpDateDatePicker.getValue()
                    + "', ukPublicInsuranceNo='" + ifNoPublicLiabilityInsTextField.getText()
                    + "', ukEmployerInsurance='" + EmpLiabilityInsChoiceBox.getValue()
                    + "', ukEmployerInsuranceProviderName='" + insEmpProvNameTextField.getText()
                    + "', ukEmployerInsuranceExpiryDate='" + insEmpProvExpDateDatePicker.getValue()
                    + "', ukEmployerInsuranceNo='" + ifNoEmpLiabilityInsTextField.getText()
                    + "', ukProfessionalInsurance='" + IndemInsChoiceBox.getValue()
                    + "', ukProfessionalInsuranceProviderName='" + IndemInsProvNameTextField.getText()
                    + "', ukProfessionalInsuranceExpiryDate='" + IndemInsExpDateDatePicker.getValue()
                    + "', nonUkPublicInsurance='" + ins1ChoiceBox.getValue()
                    + "', nonUkPublicInsuranceProviderName='" + ins1ProvNameTextField.getText()
                    + "', nonUkPublicInsuranceExpiryDate='" + ins1ExpDateDatePicker.getValue()
                    + "', nonUkStudentInjuryInsurance='" + ins2PChoiceBox.getValue()
                    + "', nonUkStudentInjuryInsuranceProviderName='" + ins2ProvNameTextField.getText()
                    + "', nonUkStudentInjuryInsuranceExpiryDate='" + ins2ExpDateDatePicker.getValue()
                    + "', nonUkCompensationInsurance='" + ins3PChoiceBox.getValue()
                    + "', nonUkCompensationInsuranceProviderName='" + ins3ProvNameTextField.getText()
                    + "', nonUkCompensationInsuranceExpiryDate='" + ins3ExpDateDatePicker.getValue()
                    + "', accidentsReportingProcedure='" + accidentsReportChoiceBox.getValue()
                    + "', healthPolicy='" + writtenHandSpolicyChoiceBox.getValue()
                    + "', healthTraining='" + hAndStrainingChoiceBox.getValue()
                    + "', staffUndertakeSiteVisit='" + siteVisitsChoiceBox.getValue()
                    + "', staffUndertakeSiteVisitDetails='" + ifNoSiteVisitsTextField.getText()
                    + "', confidentialityPlacement='" + disclosureChoiceBox.getValue()
                    + "', confidentialityPlacementDetails='" + ifYesDisclosureTextField.getText()
                    + "', formFillerName='" + providerStaffNameTextField.getText()
                    + "', formFillerJobTitle='" + providerStaffJobTitleTextField.getText()
                    + "', formFillerDigitalSignature='" + providerStaffSignatureTextField.getText()
                    + "', formFilledDate='" + dateDatePicker.getValue()
                    + "', username ='" + userN
                    + "' WHERE studentNumber = '" + selectStudentComboBox.getValue() + "'";

            ResultSet queryResult = statement.executeQuery(rowExsistsQuery);
            String insertToRegister = insertFields + insertValues;

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    try {
                        statement.executeUpdate(updateFields);
                        Alert submissionAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        submissionAlert.setTitle("Form Submission");
                        submissionAlert.setHeaderText("Your form has been successfully submitted!");

                        if (submissionAlert.showAndWait().get() == ButtonType.OK) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("placement_teamDashboard.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene dashboardScene = new Scene(root);
                            PlacementTeamDashboardController dashboardC = fxmlLoader.getController();
                            dashboardC.setName(foreN, surN);
                            dashboardC.setPieChart();
                            Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
                            dashboardStage.setScene(dashboardScene);
                            dashboardStage.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                } else {
                    try {
                        statement.executeUpdate(insertToRegister);
                        Alert submissionAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        submissionAlert.setTitle("Form Submission");
                        submissionAlert.setHeaderText("Your form has been successfully submitted!");

                        if (submissionAlert.showAndWait().get() == ButtonType.OK) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("placement_teamDashboard.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene dashboardScene = new Scene(root);
                            PlacementTeamDashboardController dashboardC = fxmlLoader.getController();
                            dashboardC.setName(foreN, surN);
                            dashboardC.setPieChart();
                            Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
                            dashboardStage.setScene(dashboardScene);
                            dashboardStage.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void saveButtonOnAction(ActionEvent event) {
        errorMessageLabel.setText("");
        if (selectStudentComboBox.getValue() == null) {
            errorMessageLabel.setText("Please select a student!");
            return;
        }
        saveForm();
    }

    public void setForm() throws SQLException {
        // Create a database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Define the SQL query
        String sqlQuery = "SELECT * FROM savedProviderForm WHERE studentNumber = ?";

        try {
            // Create a prepared statement with the SQL query and set the username parameter
            PreparedStatement statement = connectDB.prepareStatement(sqlQuery);
            statement.setString(1, selectStudentComboBox.getValue());

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the current row
                column1 = resultSet.getString(3);
                column2 = resultSet.getString(4);
                column3 = resultSet.getString(5);
                column4 = resultSet.getString(6);
                column5 = resultSet.getString(7);
                column6 = resultSet.getString(8);
                column7 = resultSet.getString(9);
                column8 = resultSet.getString(10);
                column9 = resultSet.getString(11);
                column10 = resultSet.getString(12);
                column11 = resultSet.getString(13);
                column12 = resultSet.getString(14);
                column13 = resultSet.getString(15);
                column14 = resultSet.getString(16);
                column15 = resultSet.getString(17);
                column16 = resultSet.getString(18);
                column17 = resultSet.getString(19);
                column18 = resultSet.getString(20);
                column19 = resultSet.getString(21);
                column20 = resultSet.getString(22);
                column21 = resultSet.getString(23);
                column22 = resultSet.getString(24);
                column23 = resultSet.getString(25);
                column24 = resultSet.getString(26);
                column25 = resultSet.getString(27);
                column26 = resultSet.getString(28);
                column27 = resultSet.getString(29);
                column28 = resultSet.getString(30);
                column29 = resultSet.getString(31);
                column30 = resultSet.getString(32);
                column31 = resultSet.getString(33);
                column32 = resultSet.getString(34);
                column33 = resultSet.getString(35);
                column34 = resultSet.getString(36);
                column35 = resultSet.getString(37);
                column36 = resultSet.getString(38);
                column37 = resultSet.getString(39);
                column38 = resultSet.getString(40);
                column39 = resultSet.getString(41);
                column40 = resultSet.getString(42);
                column41 = resultSet.getString(43);
                column42 = resultSet.getString(44);
                column43 = resultSet.getString(45);
                column44 = resultSet.getString(46);
                column45 = resultSet.getString(47);
                column46 = resultSet.getString(48);
                column47 = resultSet.getString(49);
                column48 = resultSet.getString(50);
                column49 = resultSet.getString(51);
                column50 = resultSet.getString(52);
                column51 = resultSet.getString(53);
                column52 = resultSet.getString(54);
                column53 = resultSet.getString(55);
                column54 = resultSet.getString(56);
                column55 = resultSet.getString(57);
                column56 = resultSet.getString(58);
                column57 = resultSet.getString(59);
                column58 = resultSet.getString(60);
                column59 = resultSet.getString(61);
                column60 = resultSet.getString(62);
                column61 = resultSet.getString(63);
                column62 = resultSet.getString(64);
                column63 = resultSet.getString(65);
                column64 = resultSet.getString(66);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                if (Objects.equals(column1, "null")) {
                    organisationNameTextField.setText(null);
                }
                else {
                    organisationNameTextField.setText(column1);
                }
                if (Objects.equals(column2, "null")) {
                    placementAddressTextField.setText(null);
                }
                else {
                    placementAddressTextField.setText(column2);
                }
                if (Objects.equals(column3, "null")) {
                    postcodeTextField.setText(null);
                }
                else {
                    postcodeTextField.setText(column3);
                }
                if (Objects.equals(column4, "null")) {
                    webAddressTextField.setText(null);
                }
                else {
                    webAddressTextField.setText(column4);
                }
                if (Objects.equals(column5, "null")) {
                    organisationActivitiesChoiceBox.setValue(null);
                }
                else {
                    organisationActivitiesChoiceBox.setValue(column5);
                }
                if (Objects.equals(column6, "null")) {
                    organisationActivitiesTextField.setText(null);
                }
                else {
                    organisationActivitiesTextField.setText(column6);
                }
                if (Objects.equals(column7, "null")) {
                    placementStudentNameTextField.setText(null);
                }
                else {
                    placementStudentNameTextField.setText(column7);
                }
                if (Objects.equals(column8, "null")) {
                    nameOfMainContactTextField.setText(null);
                }
                else {
                    nameOfMainContactTextField.setText(column8);
                }
                if (Objects.equals(column9, "null")) {
                    contactJobTitleTextField.setText(null);
                }
                else {
                    contactJobTitleTextField.setText(column9);
                }
                if (Objects.equals(column10, "null")) {
                    contactEmailField.setText(null);
                }
                else {
                    contactEmailField.setText(column10);
                }
                if (Objects.equals(column11, "null")) {
                    contactTelNumberTextField.setText(null);
                }
                else {
                    contactTelNumberTextField.setText(column11);
                }
                if (Objects.equals(column12, "null")) {
                    jobTitleTextField.setText(null);
                }
                else {
                    jobTitleTextField.setText(column12);
                }
                if (Objects.equals(column13, "null")) {
                    roleStartDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date1 = LocalDate.parse(column13, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    roleStartDateDatePicker.setValue(date1);
                }
                if (Objects.equals(column14, "null")) {
                    roleEndDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date2 = LocalDate.parse(column14, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    roleEndDateDatePicker.setValue(date2);
                }
                if (Objects.equals(column15, "null")) {
                    workingHoursTextField.setText(null);
                }
                else {
                    workingHoursTextField.setText(column15);
                }
                if (Objects.equals(column16, "null")) {
                    probationPeriodChoiceBox.setValue(null);
                }
                else {
                    probationPeriodChoiceBox.setValue(column16);
                }
                if (Objects.equals(column17, "null")) {
                    ifYesProbationPeriodTextField.setText(null);
                }
                else {
                    ifYesProbationPeriodTextField.setText(column17);
                }
                if (Objects.equals(column18, "null")) {
                    endOfRoleExpectTextField.setText(null);
                }
                else {
                    endOfRoleExpectTextField.setText(column18);
                }
                if (Objects.equals(column19, "null")) {
                    hazardChoiceBox.setValue(null);
                }
                else {
                    hazardChoiceBox.setValue(column19);
                }
                if (Objects.equals(column20, "null")) {
                    ifYesHazardTextField.setText(null);
                }
                else {
                    ifYesHazardTextField.setText(column20);
                }
                if (Objects.equals(column21, "null")) {
                    trainingChoiceBox.setValue(null);
                }
                else {
                    trainingChoiceBox.setValue(column21);
                }
                if (Objects.equals(column22, "null")) {
                    ifYesTrainingTextField.setText(null);
                }
                else {
                    ifYesTrainingTextField.setText(column22);
                }
                if (Objects.equals(column23, "null")) {
                    workFromHomeChoiceBox.setValue(null);
                }
                else {
                    workFromHomeChoiceBox.setValue(column23);
                }
                if (Objects.equals(column24, "null")) {
                    frequencyWorkFromHomeChoiceBox.setValue(null);
                }
                else {
                    frequencyWorkFromHomeChoiceBox.setValue(column24);
                }
                if (Objects.equals(column25, "null")) {
                    ifYesWorkFromHome.setText(null);
                }
                else {
                    ifYesWorkFromHome.setText(column25);
                }
                if (Objects.equals(column26, "null")) {
                    workDiffSitesChoiceBox.setValue(null);
                }
                else {
                    workDiffSitesChoiceBox.setValue(column26);
                }
                if (Objects.equals(column27, "null")) {
                    ifYesworkDiffSitesTextField.setText(null);
                }
                else {
                    ifYesworkDiffSitesTextField.setText(column27);
                }
                if (Objects.equals(column28, "null")) {
                    travelOutUKChoiceBox.setValue(null);
                }
                else {
                    travelOutUKChoiceBox.setValue(column28);
                }
                if (Objects.equals(column29, "null")) {
                    locationRiskChoiceBox.setValue(null);
                }
                else {
                    locationRiskChoiceBox.setValue(column29);
                }
                if (Objects.equals(column30, "null")) {
                    locationRiskTextField.setText(null);
                }
                else {
                    locationRiskTextField.setText(column30);
                }
                if (Objects.equals(column31, "null")) {
                    measuresChoiceBox.setValue(null);
                }
                else {
                    measuresChoiceBox.setValue(column31);
                }
                if (Objects.equals(column32, "null")) {
                    ifYesMeasuresTextField.setText(null);
                }
                else {
                    ifYesMeasuresTextField.setText(column32);
                }
                if (Objects.equals(column33, "null")) {
                    personalFactorsChoiceBox.setValue(null);
                }
                else {
                    personalFactorsChoiceBox.setValue(column33);
                }
                if (Objects.equals(column34, "null")) {
                    publicLiabilityInsChoiceBox.setValue(null);
                }
                else {
                    publicLiabilityInsChoiceBox.setValue(column34);
                }
                if (Objects.equals(column35, "null")) {
                    insProvNameTextField.setText(null);
                }
                else {
                    insProvNameTextField.setText(column35);
                }
                if (Objects.equals(column36, "null")) {
                    insProvExpDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date3 = LocalDate.parse(column36, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    insProvExpDateDatePicker.setValue(date3);
                }
                if (Objects.equals(column37, "null")) {
                    ifNoPublicLiabilityInsTextField.setText(null);
                }
                else {
                    ifNoPublicLiabilityInsTextField.setText(column37);
                }
                if (Objects.equals(column38, "null")) {
                    EmpLiabilityInsChoiceBox.setValue(null);
                }
                else {
                    EmpLiabilityInsChoiceBox.setValue(column38);
                }
                if (Objects.equals(column39, "null")) {
                    insEmpProvNameTextField.setText(null);
                }
                else {
                    insEmpProvNameTextField.setText(column39);
                }
                if (Objects.equals(column40, "null")) {
                    insEmpProvExpDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date4 = LocalDate.parse(column40, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    insEmpProvExpDateDatePicker.setValue(date4);
                }
                if (Objects.equals(column41, "null")) {
                    ifNoEmpLiabilityInsTextField.setText(null);
                }
                else {
                    ifNoEmpLiabilityInsTextField.setText(column41);
                }
                if (Objects.equals(column42, "null")) {
                    IndemInsChoiceBox.setValue(null);
                }
                else {
                    IndemInsChoiceBox.setValue(column42);
                }
                if (Objects.equals(column43, "null")) {
                    IndemInsProvNameTextField.setText(null);
                }
                else {
                    IndemInsProvNameTextField.setText(column43);
                }
                if (Objects.equals(column44, "null")) {
                    IndemInsExpDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date5 = LocalDate.parse(column44, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    IndemInsExpDateDatePicker.setValue(date5);
                }
                if (Objects.equals(column45, "null")) {
                    ins1ChoiceBox.setValue(null);
                }
                else {
                    ins1ChoiceBox.setValue(column45);
                }
                if (Objects.equals(column46, "null")) {
                    ins1ProvNameTextField.setText(null);
                }
                else {
                    ins1ProvNameTextField.setText(column46);
                }
                if (Objects.equals(column47, "null")) {
                    ins1ExpDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date6 = LocalDate.parse(column47, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ins1ExpDateDatePicker.setValue(date6);
                }
                if (Objects.equals(column48, "null")) {
                    ins2PChoiceBox.setValue(null);
                }
                else {
                    ins2PChoiceBox.setValue(column48);
                }
                if (Objects.equals(column49, "null")) {
                    ins2ProvNameTextField.setText(null);
                }
                else {
                    ins2ProvNameTextField.setText(column49);
                }
                if (Objects.equals(column50, "null")) {
                    ins2ExpDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date7 = LocalDate.parse(column50, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ins2ExpDateDatePicker.setValue(date7);
                }
                if (Objects.equals(column51, "null")) {
                    ins3PChoiceBox.setValue(null);
                }
                else {
                    ins3PChoiceBox.setValue(column51);
                }
                if (Objects.equals(column52, "null")) {
                    ins3ProvNameTextField.setText(null);
                }
                else {
                    ins3ProvNameTextField.setText(column52);
                }
                if (Objects.equals(column53, "null")) {
                    ins3ExpDateDatePicker.setValue(null);
                }
                else {
                    LocalDate date8 = LocalDate.parse(column53, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ins3ExpDateDatePicker.setValue(date8);
                }
                if (Objects.equals(column54, "null")) {
                    accidentsReportChoiceBox.setValue(null);
                }
                else {
                    accidentsReportChoiceBox.setValue(column54);
                }
                if (Objects.equals(column55, "null")) {
                    writtenHandSpolicyChoiceBox.setValue(null);
                }
                else {
                    writtenHandSpolicyChoiceBox.setValue(column55);
                }
                if (Objects.equals(column56, "null")) {
                    hAndStrainingChoiceBox.setValue(null);
                }
                else {
                    hAndStrainingChoiceBox.setValue(column56);
                }
                if (Objects.equals(column57, "null")) {
                    siteVisitsChoiceBox.setValue(null);
                }
                else {
                    siteVisitsChoiceBox.setValue(column57);
                }
                if (Objects.equals(column58, "null")) {
                    ifNoSiteVisitsTextField.setText(null);
                }
                else {
                    ifNoSiteVisitsTextField.setText(column58);
                }
                if (Objects.equals(column59, "null")) {
                    disclosureChoiceBox.setValue(null);
                }
                else {
                    disclosureChoiceBox.setValue(column59);
                }
                if (Objects.equals(column60, "null")) {
                    ifYesDisclosureTextField.setText(null);
                }
                else {
                    ifYesDisclosureTextField.setText(column60);
                }
                if (Objects.equals(column61, "null")) {
                    providerStaffNameTextField.setText(null);
                }
                else {
                    providerStaffNameTextField.setText(column61);
                }
                if (Objects.equals(column62, "null")) {
                    providerStaffJobTitleTextField.setText(null);
                }
                else {
                    providerStaffJobTitleTextField.setText(column62);
                }
                if (Objects.equals(column63, "null")) {
                    providerStaffSignatureTextField.setText(null);
                }
                else {
                    providerStaffSignatureTextField.setText(column63);
                }
                if (Objects.equals(column64, "null")) {
                    dateDatePicker.setValue(null);
                }
                else {
                    LocalDate date9 = LocalDate.parse(column64, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    dateDatePicker.setValue(date9);
                }
            }
        } catch (SQLException e) {
            // Handle any errors that occur
            e.printStackTrace();
        } finally {
            // Close the database connection and any other resources
            try {
                connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void selectStudentComboBoxOnAction(ActionEvent event) throws SQLException {
        studentErrorMessageLabel.setText("");
        errorMessageLabel.setText("");
        lockForm();
        resetForm();
        if (selectStudentComboBox.getValue() == null) {
            studentErrorMessageLabel.setText("Please select a student before proceeding!");

        }
        else {
            setForm();
        }
    }

    public void viewStudentFormOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        if (selectStudentComboBox.getValue() == null) {
            studentErrorMessageLabel.setText("Please select a student before proceeding!");
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_student_form.fxml"));
            Parent root = fxmlLoader.load();
            Scene dashboardScene = new Scene(root);
            ViewStudentFormController studentF = fxmlLoader.getController();
            studentF.viewStudentFormSet(selectStudentComboBox.getValue());
            Stage stage = new Stage();
            stage.setScene(dashboardScene);
            stage.initStyle(StageStyle.UNDECORATED);
            //dashboardStage.centerOnScreen();
            stage.show();
        }
    }

    public void viewTutorFormOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        if (selectStudentComboBox.getValue() == null) {
            studentErrorMessageLabel.setText("Please select a student before proceeding!");
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_tutor_form.fxml"));
            Parent root = fxmlLoader.load();
            Scene dashboardScene = new Scene(root);
            ViewTutorFormController tutorF = fxmlLoader.getController();
            tutorF.viewStudentFormSet(selectStudentComboBox.getValue());
            Stage stage = new Stage();
            stage.setScene(dashboardScene);
            stage.initStyle(StageStyle.UNDECORATED);
            //dashboardStage.centerOnScreen();
            stage.show();
        }
    }

    public void resetForm() {
        organisationNameTextField.setText("");
        placementAddressTextField.setText("");
        postcodeTextField.setText("");
        webAddressTextField.setText("");
        organisationActivitiesChoiceBox.setValue("");
        organisationActivitiesTextField.setText("");
        placementStudentNameTextField.setText("");
        nameOfMainContactTextField.setText("");
        contactJobTitleTextField.setText("");
        contactEmailField.setText("");
        contactTelNumberTextField.setText("");
        jobTitleTextField.setText("");
        roleStartDateDatePicker.setValue(null);
        roleEndDateDatePicker.setValue(null);
        workingHoursTextField.setText("");
        probationPeriodChoiceBox.setValue("");
        ifYesProbationPeriodTextField.setText("");
        endOfRoleExpectTextField.setText("");
        hazardChoiceBox.setValue("");
        ifYesHazardTextField.setText("");
        trainingChoiceBox.setValue("");
        ifYesTrainingTextField.setText("");
        workFromHomeChoiceBox.setValue("");
        frequencyWorkFromHomeChoiceBox.setValue("");
        ifYesWorkFromHome.setText("");
        workDiffSitesChoiceBox.setValue("");
        ifYesworkDiffSitesTextField.setText("");
        travelOutUKChoiceBox.setValue("");
        locationRiskChoiceBox.setValue("");
        locationRiskTextField.setText("");
        measuresChoiceBox.setValue("");
        ifYesMeasuresTextField.setText("");
        personalFactorsChoiceBox.setValue("");
        publicLiabilityInsChoiceBox.setValue("");
        insProvNameTextField.setText("");
        insProvExpDateDatePicker.setValue(null);
        ifNoPublicLiabilityInsTextField.setText("");
        EmpLiabilityInsChoiceBox.setValue("");
        insEmpProvNameTextField.setText("");
        insEmpProvExpDateDatePicker.setValue(null);
        ifNoEmpLiabilityInsTextField.setText("");
        IndemInsChoiceBox.setValue("");
        IndemInsProvNameTextField.setText("");
        IndemInsExpDateDatePicker.setValue(null);
        ins1ChoiceBox.setValue("");
        ins1ProvNameTextField.setText("");
        ins1ExpDateDatePicker.setValue(null);
        ins2PChoiceBox.setValue("");
        ins2ProvNameTextField.setText("");
        ins2ExpDateDatePicker.setValue(null);
        ins3PChoiceBox.setValue("");
        ins3ProvNameTextField.setText("");
        ins3ExpDateDatePicker.setValue(null);
        accidentsReportChoiceBox.setValue("");
        writtenHandSpolicyChoiceBox.setValue("");
        hAndStrainingChoiceBox.setValue("");
        siteVisitsChoiceBox.setValue("");
        ifNoSiteVisitsTextField.setText("");
        disclosureChoiceBox.setValue("");
        ifYesDisclosureTextField.setText("");
        providerStaffNameTextField.setText("");
        providerStaffJobTitleTextField.setText("");
        providerStaffSignatureTextField.setText("");
        dateDatePicker.setValue(null);
    }

    public void lockForm() {
        if (selectStudentComboBox.getValue() == null) {
            organisationNameTextField.setDisable(true);
            placementAddressTextField.setDisable(true);
            postcodeTextField.setDisable(true);
            webAddressTextField.setDisable(true);
            organisationActivitiesChoiceBox.setDisable(true);
            organisationActivitiesTextField.setDisable(true);
            placementStudentNameTextField.setDisable(true);
            nameOfMainContactTextField.setDisable(true);
            contactJobTitleTextField.setDisable(true);
            contactEmailField.setDisable(true);
            contactTelNumberTextField.setDisable(true);
            jobTitleTextField.setDisable(true);
            roleStartDateDatePicker.setDisable(true);
            roleEndDateDatePicker.setDisable(true);
            workingHoursTextField.setDisable(true);
            probationPeriodChoiceBox.setDisable(true);
            ifYesProbationPeriodTextField.setDisable(true);
            endOfRoleExpectTextField.setDisable(true);
            hazardChoiceBox.setDisable(true);
            ifYesHazardTextField.setDisable(true);
            trainingChoiceBox.setDisable(true);
            ifYesTrainingTextField.setDisable(true);
            workFromHomeChoiceBox.setDisable(true);
            frequencyWorkFromHomeChoiceBox.setDisable(true);
            ifYesWorkFromHome.setDisable(true);
            workDiffSitesChoiceBox.setDisable(true);
            ifYesworkDiffSitesTextField.setDisable(true);
            travelOutUKChoiceBox.setDisable(true);
            locationRiskChoiceBox.setDisable(true);
            locationRiskTextField.setDisable(true);
            measuresChoiceBox.setDisable(true);
            ifYesMeasuresTextField.setDisable(true);
            personalFactorsChoiceBox.setDisable(true);
            publicLiabilityInsChoiceBox.setDisable(true);
            insProvNameTextField.setDisable(true);
            insProvExpDateDatePicker.setDisable(true);
            ifNoPublicLiabilityInsTextField.setDisable(true);
            EmpLiabilityInsChoiceBox.setDisable(true);
            insEmpProvNameTextField.setDisable(true);
            insEmpProvExpDateDatePicker.setDisable(true);
            ifNoEmpLiabilityInsTextField.setDisable(true);
            IndemInsChoiceBox.setDisable(true);
            IndemInsProvNameTextField.setDisable(true);
            IndemInsExpDateDatePicker.setDisable(true);
            ins1ChoiceBox.setDisable(true);
            ins1ProvNameTextField.setDisable(true);
            ins1ExpDateDatePicker.setDisable(true);
            ins2PChoiceBox.setDisable(true);
            ins2ProvNameTextField.setDisable(true);
            ins2ExpDateDatePicker.setDisable(true);
            ins3PChoiceBox.setDisable(true);
            ins3ProvNameTextField.setDisable(true);
            ins3ExpDateDatePicker.setDisable(true);
            accidentsReportChoiceBox.setDisable(true);
            writtenHandSpolicyChoiceBox.setDisable(true);
            hAndStrainingChoiceBox.setDisable(true);
            siteVisitsChoiceBox.setDisable(true);
            ifNoSiteVisitsTextField.setDisable(true);
            disclosureChoiceBox.setDisable(true);
            ifYesDisclosureTextField.setDisable(true);
            providerStaffNameTextField.setDisable(true);
            providerStaffJobTitleTextField.setDisable(true);
            providerStaffSignatureTextField.setDisable(true);
            dateDatePicker.setDisable(true);
        }
        else {
            organisationNameTextField.setDisable(false);
            placementAddressTextField.setDisable(false);
            postcodeTextField.setDisable(false);
            webAddressTextField.setDisable(false);
            organisationActivitiesChoiceBox.setDisable(false);
            organisationActivitiesTextField.setDisable(false);
            placementStudentNameTextField.setDisable(false);
            nameOfMainContactTextField.setDisable(false);
            contactJobTitleTextField.setDisable(false);
            contactEmailField.setDisable(false);
            contactTelNumberTextField.setDisable(false);
            jobTitleTextField.setDisable(false);
            roleStartDateDatePicker.setDisable(false);
            roleEndDateDatePicker.setDisable(false);
            workingHoursTextField.setDisable(false);
            probationPeriodChoiceBox.setDisable(false);
            ifYesProbationPeriodTextField.setDisable(false);
            endOfRoleExpectTextField.setDisable(false);
            hazardChoiceBox.setDisable(false);
            ifYesHazardTextField.setDisable(false);
            trainingChoiceBox.setDisable(false);
            ifYesTrainingTextField.setDisable(false);
            workFromHomeChoiceBox.setDisable(false);
            frequencyWorkFromHomeChoiceBox.setDisable(false);
            ifYesWorkFromHome.setDisable(false);
            workDiffSitesChoiceBox.setDisable(false);
            ifYesworkDiffSitesTextField.setDisable(false);
            travelOutUKChoiceBox.setDisable(false);
            locationRiskChoiceBox.setDisable(false);
            locationRiskTextField.setDisable(false);
            measuresChoiceBox.setDisable(false);
            ifYesMeasuresTextField.setDisable(false);
            personalFactorsChoiceBox.setDisable(false);
            publicLiabilityInsChoiceBox.setDisable(false);
            insProvNameTextField.setDisable(false);
            insProvExpDateDatePicker.setDisable(false);
            ifNoPublicLiabilityInsTextField.setDisable(false);
            EmpLiabilityInsChoiceBox.setDisable(false);
            insEmpProvNameTextField.setDisable(false);
            insEmpProvExpDateDatePicker.setDisable(false);
            ifNoEmpLiabilityInsTextField.setDisable(false);
            IndemInsChoiceBox.setDisable(false);
            IndemInsProvNameTextField.setDisable(false);
            IndemInsExpDateDatePicker.setDisable(false);
            ins1ChoiceBox.setDisable(false);
            ins1ProvNameTextField.setDisable(false);
            ins1ExpDateDatePicker.setDisable(false);
            ins2PChoiceBox.setDisable(false);
            ins2ProvNameTextField.setDisable(false);
            ins2ExpDateDatePicker.setDisable(false);
            ins3PChoiceBox.setDisable(false);
            ins3ProvNameTextField.setDisable(false);
            ins3ExpDateDatePicker.setDisable(false);
            accidentsReportChoiceBox.setDisable(false);
            writtenHandSpolicyChoiceBox.setDisable(false);
            hAndStrainingChoiceBox.setDisable(false);
            siteVisitsChoiceBox.setDisable(false);
            ifNoSiteVisitsTextField.setDisable(false);
            disclosureChoiceBox.setDisable(false);
            ifYesDisclosureTextField.setDisable(false);
            providerStaffNameTextField.setDisable(false);
            providerStaffJobTitleTextField.setDisable(false);
            providerStaffSignatureTextField.setDisable(false);
            dateDatePicker.setDisable(false);
        }
    }
}