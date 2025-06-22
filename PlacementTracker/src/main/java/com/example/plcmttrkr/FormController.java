package com.example.plcmttrkr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import java.awt.Desktop;

public class FormController extends LoginController implements Initializable {
    private String column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17, column18, column19, column20, column21, column22, column23, column24, column25, column26, column27, column28, column29, column30, column31, column32, column33, column34, column35, column36, column37, column38, column39, column40, column41, column42, column43, column44, column45, column46, column47, column48, column49, column50, column51, column52, column53;
    private String[] choice1 = {"Yes", "No"};
    private String[] choice2 = {"Own Vehicle", "Public transport", "Walking", "Cycle", "Other"};
    private String[] choice3 = {"Yes", "No", "Don't know"};
    private String[] choice4 = {"Rent shared house", "Rent individual house", "Live at home", "Other"};
    private String[] choice5 = {"Yes", "No", "N/A (No risks)"};
    private String[] choice6 = {"Yes", "No", "N/A (country not in the EU)"};
    private String[] choice7 = {"Yes", "No", "N/A"};
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean sec1Valid = false, sec2Valid = false, sec3Valid = false, sec4Valid = false, sec5Valid = false, sec5Valid1 = false, sec5Valid2 = false, sec5Valid3 = false, sec5Valid4 = false, sec6Valid = false, sec7Valid = false, sec8Valid = false, sec9Valid = false, decValid = false;
    @FXML
    private Label userTypeLabel;
    @FXML
    private ScrollPane studentScrollPane;
    @FXML
    private AnchorPane dragBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button minimiseButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField studentNumberTextField;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private TextField programmeOfStudyTextField;
    @FXML
    private TextField schoolDepartmentTextField;
    @FXML
    private TextField contactTelephoneNumberTextField;
    @FXML
    private ChoiceBox<String> internationalStudentWithVisaChoiceBox;
    @FXML
    private ChoiceBox<String> studentVisaDurationChoiceBox;
    @FXML
    private Button submitButton;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private TextField nameOfOrgTextField;
    @FXML
    private TextField addressOfPlacementTextField;
    @FXML
    private TextField postcodeTextField;
    @FXML
    private TextField webAddressTextField;
    @FXML
    private TextField contactNameTextField;
    @FXML
    private TextField contactJobTitleTextField;
    @FXML
    private TextField contactEmailTextField;
    @FXML
    private TextField contactPTelephoneNumberTextField;
    @FXML
    private TextField roleTitleTextField;
    @FXML
    private DatePicker roleStartDateDatePicker;
    @FXML
    private DatePicker roleEndDateDatePicker;
    @FXML
    private TextField workingHoursPerWeekTextField;
    @FXML
    private ChoiceBox<String> probationPeriodChoiceBox;
    @FXML
    private TextField lengthOfProbationTextField;
    @FXML
    private TextField salaryPerYearTextField;
    @FXML
    private TextField sourceRoleTextField;
    @FXML
    private ChoiceBox<String> informedPlacementChoiceBox;
    @FXML
    private TextField roleDescriptionTextField;
    @FXML
    private ChoiceBox<String> remoteWorkChoiceBox;
    @FXML
    private TextField overviewRemoteWorkTextField;
    @FXML
    private TextField whyWorkHomeTextField;
    @FXML
    private ChoiceBox<String> travelChoiceBox;
    @FXML
    private TextField travelOtherTextField;
    @FXML
    private ChoiceBox<String> roleLocationDifferentChoiceBox;
    @FXML
    private TextField detailsDifferentLocationTextField;
    @FXML
    private ChoiceBox<String> overseasTravelRequiredChoiceBox;
    @FXML
    private ChoiceBox<String> overseasGuidanceChoiceBox;
    @FXML
    private ChoiceBox<String> overseasTravelChoiceBox;
    @FXML
    private ChoiceBox<String> accommodationArrangementsChoiceBox;
    @FXML
    private TextField accommodationOtherTextField;
    @FXML
    private ChoiceBox<String> overseasPlacementOfficeChoiceBox;
    @FXML
    private ChoiceBox<String> overseasHealthChoiceBox;
    @FXML
    private TextField overseasHealthInfoTextField;
    @FXML
    private ChoiceBox<String> precautionaryMeasuresChoiceBox;
    @FXML
    private TextField precautionaryMeasuresTextField;
    @FXML
    private ChoiceBox<String> safeZoneAppChoiceBox;
    @FXML
    private ChoiceBox<String> globalHealthInsuranceChoiceBox;
    @FXML
    private ChoiceBox<String> adjustmentsChoiceBox;
    @FXML
    private ChoiceBox<String> overseasStudentApplicationFormChoiceBox;
    @FXML
    private ChoiceBox<String> overseasPlacementRiskEscalation;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField signatureTextField;
    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private Button saveButton;
    @FXML
    private Hyperlink studFormHyperlink1;

    @FXML
    private Hyperlink studFormHyperlink2;

    @FXML
    private Hyperlink studFormHyperlink3;

    @FXML
    private Hyperlink studFormHyperlink4;

    @FXML
    private Hyperlink studFormHyperlink5;

    @FXML
    private Hyperlink studFormHyperlink6;

    @FXML
    private Hyperlink studFormHyperlink7;

    @FXML
    private Hyperlink studFormHyperlink8;

    @FXML
    private Hyperlink studFormHyperlink9;
    @FXML
    private Button helpButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //Set choice boxes
        internationalStudentWithVisaChoiceBox.getItems().addAll(choice1);
        studentVisaDurationChoiceBox.getItems().addAll(choice1);
        probationPeriodChoiceBox.getItems().addAll(choice1);
        informedPlacementChoiceBox.getItems().addAll(choice1);
        remoteWorkChoiceBox.getItems().addAll(choice1);
        travelChoiceBox.getItems().addAll(choice2);
        roleLocationDifferentChoiceBox.getItems().addAll(choice1);
        overseasTravelRequiredChoiceBox.getItems().addAll(choice3);
        overseasGuidanceChoiceBox.getItems().addAll(choice1);
        overseasTravelChoiceBox.getItems().addAll(choice1);
        accommodationArrangementsChoiceBox.getItems().addAll(choice4);
        overseasPlacementOfficeChoiceBox.getItems().addAll(choice1);
        overseasHealthChoiceBox.getItems().addAll(choice5);
        precautionaryMeasuresChoiceBox.getItems().addAll(choice1);
        safeZoneAppChoiceBox.getItems().addAll(choice1);
        globalHealthInsuranceChoiceBox.getItems().addAll(choice6);
        adjustmentsChoiceBox.getItems().addAll(choice7);
        overseasStudentApplicationFormChoiceBox.getItems().addAll(choice1);
        overseasPlacementRiskEscalation.getItems().addAll(choice1);
        lockStudentNumber(); //Lock student number text field if required
    }

    public void helpButtonOnAction(ActionEvent event) throws IOException { //Loads help page when help button is pressed
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_help.fxml"));
        Parent root = fxmlLoader.load();
        StudentHelpController formC = fxmlLoader.getController();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) helpButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
    }

    public void dragBarOnAction(MouseEvent event) { //Retrieves current coordinates for drag bar
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void dragBarOnMovementAction(MouseEvent event) { //Allows drag bar movement
        Stage stage = (Stage) dragBar.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void cancelButtonOnAction(ActionEvent event) { //Close button, terminates stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void minimiseButtonOnAction(ActionEvent event) { //Minimise button, minimises stage
        Stage stage = (Stage) minimiseButton.getScene().getWindow();
        stage.setIconified(true);
    }

    public void logoutButtonOnAction(ActionEvent event) throws IOException { //Logout button action event
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION); //Create dialog box
        logoutAlert.setTitle("Logout");
        logoutAlert.setHeaderText("Are you sure you want to log out?");

        if (logoutAlert.showAndWait().get() == ButtonType.OK) { //Wait for user response
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml")); //Loads login page
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(loginScene);
            loginStage.show();
        }
    }

    public void homeButtonOnAction(ActionEvent event) throws IOException, SQLException { //Home button action event
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml")); //Loads dashboard
        Parent root = fxmlLoader.load();
        Scene dashboardScene = new Scene(root);
        DashboardController dashboardC = fxmlLoader.getController();
        dashboardC.setName(foreN, surN); //Set methods for class
        dashboardC.setDashboard();
        dashboardC.setAboutMe();
        Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }

    @FXML
    private void goToUrl1() { //URL links
        openURL("https://uniofleicester.sharepoint.com/sites/Insurance/SitePages/Staff-Student-Overseas-Travel.aspx");
    }

    //Handle studFormHyperlink2 click event
    @FXML
    private void goToUrl2() {
        openURL("https://uniofleicester.sharepoint.com/sites/Insurance/SitePages/Staff-Student-Overseas-Travel.aspx");
    }

    //Handle studFormHyperlink3 click event
    @FXML
    private void goToUrl3() {
        openURL("https://uniofleicester.sharepoint.com/sites/Insurance/SitePages/Staff-Student-Overseas-Travel.aspx");
    }

    //Handle studFormHyperlink4 click event
    @FXML
    private void goToUrl4() {
        openURL("https://www.gov.uk/government/organisations/foreign-commonwealth-development-office");
    }

    //Handle studFormHyperlink5 click event
    @FXML
    private void goToUrl5() {
        openURL("https://le.ac.uk/study/student-support/safezone");
    }

    //Handle studFormHyperlink6 click event
    @FXML
    private void goToUrl6() {
        openURL("https://www.nhs.uk/using-the-nhs/healthcare-abroad/apply-for-a-free-uk-global-health-insurance-card-ghic/");
    }

    //Handle studFormHyperlink7 click event
    @FXML
    private void goToUrl7() {
        openURL("https://le.ac.uk/accessability-centre");
    }

    //Handle studFormHyperlink8 click event
    @FXML
    private void goToUrl8() {
        openURL("https://uniofleicester.sharepoint.com/sites/Insurance/SitePages/Staff-Student-Overseas-Travel.aspx");
    }

    //Handle studFormHyperlink9 click event
    @FXML
    private void goToUrl9() {
        openURL("https://uniofleicester.sharepoint.com/sites/Insurance/SitePages/Staff-Student-Overseas-Travel.aspx");
    }


    private void openURL(String url) { //Handles URL exceptions
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


    public void checkSection1() { //Validates text boxes for section 1
        errorMessageLabel.setText("");
        if (firstNameTextField.getText().isBlank() || surnameTextField.getText().isBlank() || studentNumberTextField.getText().isBlank() || emailAddressTextField
                .getText().isBlank() || programmeOfStudyTextField.getText().isBlank() || schoolDepartmentTextField.getText().isBlank() || contactTelephoneNumberTextField.getText().isBlank() || internationalStudentWithVisaChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all required fields in section 1!");
            sec1Valid = false;
        } else {
            sec1Valid = true;
        }
        if (("Yes").equals(internationalStudentWithVisaChoiceBox.getValue()) && studentVisaDurationChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all required fields in section 1 (I)!");
            sec1Valid = false;
        }
    }

    public void checkSection2() { //Validates text boxes for section 2
        errorMessageLabel.setText("");
        if (nameOfOrgTextField.getText().isBlank() || addressOfPlacementTextField.getText().isBlank() || postcodeTextField.getText().isBlank() || webAddressTextField.getText().isBlank() || contactNameTextField.getText().isBlank() || contactJobTitleTextField.getText().isBlank() || contactEmailTextField.getText().isBlank() || contactPTelephoneNumberTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please fill in all required fields in section 2!");
            sec2Valid = false;
        } else {
            sec2Valid = true;
        }
    }

    public void checkSection3() { //Validates text boxes and choice boxes for section 3
        errorMessageLabel.setText("");
        if (roleTitleTextField.getText().isBlank() || roleStartDateDatePicker.getValue() == null || roleEndDateDatePicker.getValue() == null || workingHoursPerWeekTextField.getText().isBlank() || probationPeriodChoiceBox.getValue() == null || salaryPerYearTextField.getText().isBlank() || sourceRoleTextField.getText().isBlank() || informedPlacementChoiceBox.getValue() == null || roleDescriptionTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please fill in all required fields in section 3!");
            sec3Valid = false;
        } else {
            sec3Valid = true;
        }
        if (("Yes").equals(probationPeriodChoiceBox.getValue()) && lengthOfProbationTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please fill in all required fields in section 3 (E)!");
            sec3Valid = false;
        }
    }

    public void checkSection4() { //Validates text boxes and choice boxes for section 4
        errorMessageLabel.setText("");
        if (remoteWorkChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all required fields in section 4!");
            sec4Valid = false;
            return;
        } else {
            sec4Valid = true;
        }
        if (("Yes").equals(remoteWorkChoiceBox.getValue()) && (overviewRemoteWorkTextField.getText().isBlank() || whyWorkHomeTextField.getText().isBlank())) {
            errorMessageLabel.setText("Please fill in all required fields in section 4 (B)/(C)!");
            sec4Valid = false;
        }
    }

    public void checkSection5() { //Validates text boxes and choice boxes for section 5
        errorMessageLabel.setText("");
        if (travelChoiceBox.getValue() == null || roleLocationDifferentChoiceBox.getValue() == null || overseasTravelRequiredChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all required fields in section 5!");
            sec5Valid1 = false;
            return;
        } else {
            sec5Valid1 = true;
            if (("Other").equals(travelChoiceBox.getValue()) && travelOtherTextField.getText().isBlank()) {
                errorMessageLabel.setText("Please fill in all required fields in section 5 (A)!");
                sec5Valid2 = false;
                return;
            } else {
                sec5Valid2 = true;
            }
            if (("Yes").equals(roleLocationDifferentChoiceBox.getValue()) && detailsDifferentLocationTextField.getText().isBlank()) {
                errorMessageLabel.setText("Please fill in all required fields in section 5 (B)!");
                sec5Valid3 = false;
                return;
            } else {
                sec5Valid3 = true;
            }
            if (("Yes").equals(overseasTravelRequiredChoiceBox.getValue()) && (overseasGuidanceChoiceBox.getValue() == null || overseasTravelChoiceBox.getValue() == null)) {
                errorMessageLabel.setText("Please fill in all required fields in section 5 (D)/(E)!");
                sec5Valid4 = false;
                return;
            } else {
                sec5Valid4 = true;
            }
        }
        if (sec5Valid1 && sec5Valid2 && sec5Valid3 && sec5Valid4) {  //Validates section 5
            sec5Valid = true;
        } else {
            sec5Valid = false;
        }
    }

    public void checkSection6() {  //Validates text boxes and choice boxes for section 6
        errorMessageLabel.setText("");
        if (accommodationArrangementsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all required fields in section 6!");
            sec6Valid = false;
            return;
        } else {
            sec6Valid = true;
            if (("Other").equals(accommodationArrangementsChoiceBox.getValue()) && accommodationOtherTextField.getText().isBlank()) {
                errorMessageLabel.setText("Please fill in all required fields in section 6 (A)!");
                sec6Valid = false;
                return;
            }
            if (("Yes").equals(overseasTravelRequiredChoiceBox.getValue()) && (overseasPlacementOfficeChoiceBox.getValue() == null || overseasHealthChoiceBox.getValue() == null || overseasHealthInfoTextField.getText().isBlank())) {
                errorMessageLabel.setText("Please fill in all required fields in section 6 (B)/(C)!");
                sec6Valid = false;
                return;
            }
        }
    }

    public void checkSection7() {  //Validates text boxes and choice boxes for section 7
        errorMessageLabel.setText("");
        if (precautionaryMeasuresChoiceBox.getValue() == null || safeZoneAppChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all required fields in section 7!");
            sec7Valid = false;
            return;
        } else {
            sec7Valid = true;
            if (("Yes").equals(precautionaryMeasuresChoiceBox.getValue()) && precautionaryMeasuresTextField.getText().isBlank()) {
                errorMessageLabel.setText("Please fill in all required fields in section 7 (A)!");
                sec7Valid = false;
                return;
            }
            if (("Yes").equals(overseasTravelRequiredChoiceBox.getValue()) && globalHealthInsuranceChoiceBox.getValue() == null) {
                errorMessageLabel.setText("Please fill in all required fields in section 7 (C)!");
                sec7Valid = false;
                return;
            }
        }
    }

    public void checkSection8() {  //Validates text boxes and choice boxes for section 8
        errorMessageLabel.setText("");
        if (adjustmentsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please fill in all required fields in section 8!");
            sec8Valid = false;
        } else {
            sec8Valid = true;
        }
    }

    public void checkSection9() {  //Validates text boxes and choice boxes for section 9
        errorMessageLabel.setText("");
        if (("Yes").equals(overseasTravelRequiredChoiceBox.getValue()) && (overseasStudentApplicationFormChoiceBox.getValue() == null || overseasPlacementRiskEscalation.getValue() == null)) {
            errorMessageLabel.setText("Please fill in all required fields in section 9!");
            sec9Valid = false;
        } else {
            sec9Valid = true;
        }
    }

    public void checkDeclaration() { //Validates declaration
        errorMessageLabel.setText("");
        if (nameTextField.getText().isBlank() || signatureTextField.getText().isBlank() || dateDatePicker.getValue() == null) {
            errorMessageLabel.setText("Please fill in the declaration!");
            decValid = false;
        } else {
            decValid = true;
        }
    }

    public void submitForm() {
        saveForm(); //When submitting previous saved forms gets overwritten
        errorMessageLabel.setText(""); //Reset error message
        try {
            DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            String rowExsistsQuery = "SELECT count(1) FROM studentForm WHERE username = '" + userN + "'"; //Check if row exsists for this user

            //Prepare SQL queries to insert and update columns
            String insertFields = "INSERT INTO studentForm(username, firstName, surname, studentNumber, emailAddress, programmeOfStudy, schoolDepartment, contactNumber, internationalStudent, visaDuration, nameOfOrg, placementAddress, postCode, webAddress, contactName, contactJobTitle, contactEmail, contactTelephoneNumber, roleTitle, roleStartDate, roleEndDate, workingHours, probationPeriod, lengthProbationPeriod, placementSalary, sourceRole, informedPlacementProvider, roleDescription, remoteWork, overviewWorkRemotely, remoteWorkJustification, travelPlacement, travelPlacementOther, differentLocation, differentLocationDetails, overseasTravelRequired, readOverseasTravelGuide, travelFinanceThought, accommodationArrangements, accommodationArrangementsOther, overseasPlacementFCDCheck, overseasPlacementRisks, overseasPlacementRisksInfo, precautionaryMeasures, precautionaryMeasuresInfo, safeZoneApp, overseasPlacementGHIC, disabilityHealthCondition, overseasPlacementSTAForm, overseasPlacementRiskAssessment, decName, decSignature, decDate) VALUES ('";
            String insertValues = userN + "','" + firstNameTextField.getText() + "','" + surnameTextField.getText() + "','" + studentNumberTextField.getText() + "','" + emailAddressTextField.getText() + "','" + programmeOfStudyTextField.getText() + "','" + schoolDepartmentTextField.getText() + "','" + contactTelephoneNumberTextField.getText() + "','" + internationalStudentWithVisaChoiceBox.getValue() + "','" + studentVisaDurationChoiceBox.getValue() + "','" + nameOfOrgTextField.getText() + "','" + addressOfPlacementTextField.getText() + "','" + postcodeTextField.getText() + "','" + webAddressTextField.getText() + "','" + contactNameTextField.getText() + "','" + contactJobTitleTextField.getText() + "','" + contactEmailTextField.getText() + "','" + contactPTelephoneNumberTextField.getText() + "','" + roleTitleTextField.getText() + "','" + roleStartDateDatePicker.getValue() + "','" + roleEndDateDatePicker.getValue() + "','" + workingHoursPerWeekTextField.getText() + "','" + probationPeriodChoiceBox.getValue() + "','" + lengthOfProbationTextField.getText() + "','" + salaryPerYearTextField.getText() + "','" + sourceRoleTextField.getText() + "','" + informedPlacementChoiceBox.getValue() + "','" + roleDescriptionTextField.getText() + "','" + remoteWorkChoiceBox.getValue() + "','" + overviewRemoteWorkTextField.getText() + "','" + whyWorkHomeTextField.getText() + "','" + travelChoiceBox.getValue() + "','" + travelOtherTextField.getText() + "','" + roleLocationDifferentChoiceBox.getValue() + "','" + detailsDifferentLocationTextField.getText() + "','" + overseasTravelRequiredChoiceBox.getValue() + "','" + overseasGuidanceChoiceBox.getValue() + "','" + overseasTravelChoiceBox.getValue() + "','" + accommodationArrangementsChoiceBox.getValue() + "','" + accommodationOtherTextField.getText() + "','" + overseasPlacementOfficeChoiceBox.getValue() + "','" + overseasHealthChoiceBox.getValue() + "','" + overseasHealthInfoTextField.getText() + "','" + precautionaryMeasuresChoiceBox.getValue() + "','" + precautionaryMeasuresTextField.getText() + "','" + safeZoneAppChoiceBox.getValue() + "','" + globalHealthInsuranceChoiceBox.getValue() + "','" + adjustmentsChoiceBox.getValue() + "','" + overseasStudentApplicationFormChoiceBox.getValue() + "','" + overseasPlacementRiskEscalation.getValue() + "','" + nameTextField.getText() + "','" + signatureTextField.getText() + "','" + dateDatePicker.getValue() + "')";

            String updateFields = "UPDATE studentForm SET firstName='" + firstNameTextField.getText()
                    + "', surname='" + surnameTextField.getText()
                    + "', studentNumber='" + studentNumberTextField.getText()
                    + "', emailAddress='" + emailAddressTextField.getText()
                    + "', programmeOfStudy='" + programmeOfStudyTextField.getText()
                    + "', schoolDepartment='" + schoolDepartmentTextField.getText()
                    + "', contactNumber='" + contactTelephoneNumberTextField.getText()
                    + "', internationalStudent='" + internationalStudentWithVisaChoiceBox.getValue()
                    + "', visaDuration='" + studentVisaDurationChoiceBox.getValue()
                    + "', nameOfOrg='" + nameOfOrgTextField.getText()
                    + "', placementAddress='" + addressOfPlacementTextField.getText()
                    + "', postCode='" + postcodeTextField.getText()
                    + "', webAddress='" + webAddressTextField.getText()
                    + "', contactName='" + contactNameTextField.getText()
                    + "', contactJobTitle='" + contactJobTitleTextField.getText()
                    + "', contactEmail='" + contactEmailTextField.getText()
                    + "', contactTelephoneNumber='" + contactPTelephoneNumberTextField.getText()
                    + "', roleTitle='" + roleTitleTextField.getText()
                    + "', roleStartDate='" + roleStartDateDatePicker.getValue()
                    + "', roleEndDate='" + roleEndDateDatePicker.getValue()
                    + "', workingHours='" + workingHoursPerWeekTextField.getText()
                    + "', probationPeriod='" + probationPeriodChoiceBox.getValue()
                    + "', lengthProbationPeriod='" + lengthOfProbationTextField.getText()
                    + "', placementSalary='" + salaryPerYearTextField.getText()
                    + "', sourceRole='" + sourceRoleTextField.getText()
                    + "', informedPlacementProvider='" + informedPlacementChoiceBox.getValue()
                    + "', roleDescription='" + roleDescriptionTextField.getText()
                    + "', remoteWork='" + remoteWorkChoiceBox.getValue()
                    + "', overviewWorkRemotely='" + overviewRemoteWorkTextField.getText()
                    + "', remoteWorkJustification='" + whyWorkHomeTextField.getText()
                    + "', travelPlacement='" + travelChoiceBox.getValue()
                    + "', travelPlacementOther='" + travelOtherTextField.getText()
                    + "', differentLocation='" + roleLocationDifferentChoiceBox.getValue()
                    + "', differentLocationDetails='" + detailsDifferentLocationTextField.getText()
                    + "', overseasTravelRequired='" + overseasTravelRequiredChoiceBox.getValue()
                    + "', readOverseasTravelGuide='" + overseasGuidanceChoiceBox.getValue()
                    + "', travelFinanceThought='" + overseasTravelChoiceBox.getValue()
                    + "', accommodationArrangements='" + accommodationArrangementsChoiceBox.getValue()
                    + "', accommodationArrangementsOther='" + accommodationOtherTextField.getText()
                    + "', overseasPlacementFCDCheck='" + overseasPlacementOfficeChoiceBox.getValue()
                    + "', overseasPlacementRisks='" + overseasHealthChoiceBox.getValue()
                    + "', overseasPlacementRisksInfo='" + overseasHealthInfoTextField.getText()
                    + "', precautionaryMeasures='" + precautionaryMeasuresChoiceBox.getValue()
                    + "', precautionaryMeasuresInfo='" + precautionaryMeasuresTextField.getText()
                    + "', safeZoneApp='" + safeZoneAppChoiceBox.getValue()
                    + "', overseasPlacementGHIC='" + globalHealthInsuranceChoiceBox.getValue()
                    + "', disabilityHealthCondition='" + adjustmentsChoiceBox.getValue()
                    + "', overseasPlacementSTAForm='" + overseasStudentApplicationFormChoiceBox.getValue()
                    + "', overseasPlacementRiskAssessment='" + overseasPlacementRiskEscalation.getValue()
                    + "', decName='" + nameTextField.getText()
                    + "', decSignature='" + signatureTextField.getText()
                    + "', decDate='" + dateDatePicker.getValue()
                    + "' WHERE username = '" + userN + "'";

            //Join SQL queries
            ResultSet queryResult = statement.executeQuery(rowExsistsQuery);
            String insertToRegister = insertFields + insertValues;

            while (queryResult.next()) { //Row already exists
                if (queryResult.getInt(1) == 1) {
                    try {
                        statement.executeUpdate(updateFields);
                        lockStudentNumber();
                        Alert submissionAlert = new Alert(Alert.AlertType.CONFIRMATION); //Create alert dialog box
                        submissionAlert.setTitle("Form Submission");
                        submissionAlert.setHeaderText("Your form has been successfully submitted!");

                        if (submissionAlert.showAndWait().get() == ButtonType.OK) { //Preview dialog box and wait for response
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml")); //Return user to dashboard
                            Parent root = fxmlLoader.load();
                            Scene dashboardScene = new Scene(root);
                            DashboardController dashboardC = fxmlLoader.getController();
                            dashboardC.setName(foreN, surN);
                            dashboardC.setDashboard();
                            dashboardC.setAboutMe();
                            Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
                            dashboardStage.setScene(dashboardScene);
                            dashboardStage.show();
                        }
                    } catch (Exception e) { //Catch any SQL errors (for debugging)
                        e.printStackTrace();
                        e.getCause();
                    }
                } else {
                    try {
                        statement.executeUpdate(insertToRegister);
                        lockStudentNumber(); //Locks student number after submission
                        Alert submissionAlert = new Alert(Alert.AlertType.CONFIRMATION); //Create dialog box
                        submissionAlert.setTitle("Form Submission");
                        submissionAlert.setHeaderText("Your form has been successfully submitted!");

                        if (submissionAlert.showAndWait().get() == ButtonType.OK) { //Preview and wait for response
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml")); //Return user to dashboard
                            Parent root = fxmlLoader.load();
                            Scene dashboardScene = new Scene(root);
                            DashboardController dashboardC = fxmlLoader.getController(); //Call methods to set dashboard
                            dashboardC.setName(foreN, surN);
                            dashboardC.setDashboard();
                            dashboardC.setAboutMe();
                            Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
                            dashboardStage.setScene(dashboardScene);
                            dashboardStage.show();
                        }
                    } catch (Exception e) { //Catch any SQL errors (useful for debugging)
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

    public void saveForm() {
        try {
            DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            //Row exists SQL query
            String rowExsistsQuery = "SELECT count(1) FROM savedStudentForm WHERE username = '" + userN + "'";

            String insertFields = "INSERT INTO savedStudentForm(username, firstName, surname, studentNumber, emailAddress, programmeOfStudy, schoolDepartment, contactNumber, internationalStudent, visaDuration, nameOfOrg, placementAddress, postCode, webAddress, contactName, contactJobTitle, contactEmail, contactTelephoneNumber, roleTitle, roleStartDate, roleEndDate, workingHours, probationPeriod, lengthProbationPeriod, placementSalary, sourceRole, informedPlacementProvider, roleDescription, remoteWork, overviewWorkRemotely, remoteWorkJustification, travelPlacement, travelPlacementOther, differentLocation, differentLocationDetails, overseasTravelRequired, readOverseasTravelGuide, travelFinanceThought, accommodationArrangements, accommodationArrangementsOther, overseasPlacementFCDCheck, overseasPlacementRisks, overseasPlacementRisksInfo, precautionaryMeasures, precautionaryMeasuresInfo, safeZoneApp, overseasPlacementGHIC, disabilityHealthCondition, overseasPlacementSTAForm, overseasPlacementRiskAssessment, decName, decSignature, decDate) VALUES ('";
            String insertValues = userN + "','" + firstNameTextField.getText() + "','" + surnameTextField.getText() + "','" + studentNumberTextField.getText() + "','" + emailAddressTextField.getText() + "','" + programmeOfStudyTextField.getText() + "','" + schoolDepartmentTextField.getText() + "','" + contactTelephoneNumberTextField.getText() + "','" + internationalStudentWithVisaChoiceBox.getValue() + "','" + studentVisaDurationChoiceBox.getValue() + "','" + nameOfOrgTextField.getText() + "','" + addressOfPlacementTextField.getText() + "','" + postcodeTextField.getText() + "','" + webAddressTextField.getText() + "','" + contactNameTextField.getText() + "','" + contactJobTitleTextField.getText() + "','" + contactEmailTextField.getText() + "','" + contactPTelephoneNumberTextField.getText() + "','" + roleTitleTextField.getText() + "','" + roleStartDateDatePicker.getValue() + "','" + roleEndDateDatePicker.getValue() + "','" + workingHoursPerWeekTextField.getText() + "','" + probationPeriodChoiceBox.getValue() + "','" + lengthOfProbationTextField.getText() + "','" + salaryPerYearTextField.getText() + "','" + sourceRoleTextField.getText() + "','" + informedPlacementChoiceBox.getValue() + "','" + roleDescriptionTextField.getText() + "','" + remoteWorkChoiceBox.getValue() + "','" + overviewRemoteWorkTextField.getText() + "','" + whyWorkHomeTextField.getText() + "','" + travelChoiceBox.getValue() + "','" + travelOtherTextField.getText() + "','" + roleLocationDifferentChoiceBox.getValue() + "','" + detailsDifferentLocationTextField.getText() + "','" + overseasTravelRequiredChoiceBox.getValue() + "','" + overseasGuidanceChoiceBox.getValue() + "','" + overseasTravelChoiceBox.getValue() + "','" + accommodationArrangementsChoiceBox.getValue() + "','" + accommodationOtherTextField.getText() + "','" + overseasPlacementOfficeChoiceBox.getValue() + "','" + overseasHealthChoiceBox.getValue() + "','" + overseasHealthInfoTextField.getText() + "','" + precautionaryMeasuresChoiceBox.getValue() + "','" + precautionaryMeasuresTextField.getText() + "','" + safeZoneAppChoiceBox.getValue() + "','" + globalHealthInsuranceChoiceBox.getValue() + "','" + adjustmentsChoiceBox.getValue() + "','" + overseasStudentApplicationFormChoiceBox.getValue() + "','" + overseasPlacementRiskEscalation.getValue() + "','" + nameTextField.getText() + "','" + signatureTextField.getText() + "','" + dateDatePicker.getValue() + "')";

            String updateFields = "UPDATE savedStudentForm SET firstName='" + firstNameTextField.getText()
                    + "', surname='" + surnameTextField.getText()
                    + "', studentNumber='" + studentNumberTextField.getText()
                    + "', emailAddress='" + emailAddressTextField.getText()
                    + "', programmeOfStudy='" + programmeOfStudyTextField.getText()
                    + "', schoolDepartment='" + schoolDepartmentTextField.getText()
                    + "', contactNumber='" + contactTelephoneNumberTextField.getText()
                    + "', internationalStudent='" + internationalStudentWithVisaChoiceBox.getValue()
                    + "', visaDuration='" + studentVisaDurationChoiceBox.getValue()
                    + "', nameOfOrg='" + nameOfOrgTextField.getText()
                    + "', placementAddress='" + addressOfPlacementTextField.getText()
                    + "', postCode='" + postcodeTextField.getText()
                    + "', webAddress='" + webAddressTextField.getText()
                    + "', contactName='" + contactNameTextField.getText()
                    + "', contactJobTitle='" + contactJobTitleTextField.getText()
                    + "', contactEmail='" + contactEmailTextField.getText()
                    + "', contactTelephoneNumber='" + contactPTelephoneNumberTextField.getText()
                    + "', roleTitle='" + roleTitleTextField.getText()
                    + "', roleStartDate='" + roleStartDateDatePicker.getValue()
                    + "', roleEndDate='" + roleEndDateDatePicker.getValue()
                    + "', workingHours='" + workingHoursPerWeekTextField.getText()
                    + "', probationPeriod='" + probationPeriodChoiceBox.getValue()
                    + "', lengthProbationPeriod='" + lengthOfProbationTextField.getText()
                    + "', placementSalary='" + salaryPerYearTextField.getText()
                    + "', sourceRole='" + sourceRoleTextField.getText()
                    + "', informedPlacementProvider='" + informedPlacementChoiceBox.getValue()
                    + "', roleDescription='" + roleDescriptionTextField.getText()
                    + "', remoteWork='" + remoteWorkChoiceBox.getValue()
                    + "', overviewWorkRemotely='" + overviewRemoteWorkTextField.getText()
                    + "', remoteWorkJustification='" + whyWorkHomeTextField.getText()
                    + "', travelPlacement='" + travelChoiceBox.getValue()
                    + "', travelPlacementOther='" + travelOtherTextField.getText()
                    + "', differentLocation='" + roleLocationDifferentChoiceBox.getValue()
                    + "', differentLocationDetails='" + detailsDifferentLocationTextField.getText()
                    + "', overseasTravelRequired='" + overseasTravelRequiredChoiceBox.getValue()
                    + "', readOverseasTravelGuide='" + overseasGuidanceChoiceBox.getValue()
                    + "', travelFinanceThought='" + overseasTravelChoiceBox.getValue()
                    + "', accommodationArrangements='" + accommodationArrangementsChoiceBox.getValue()
                    + "', accommodationArrangementsOther='" + accommodationOtherTextField.getText()
                    + "', overseasPlacementFCDCheck='" + overseasPlacementOfficeChoiceBox.getValue()
                    + "', overseasPlacementRisks='" + overseasHealthChoiceBox.getValue()
                    + "', overseasPlacementRisksInfo='" + overseasHealthInfoTextField.getText()
                    + "', precautionaryMeasures='" + precautionaryMeasuresChoiceBox.getValue()
                    + "', precautionaryMeasuresInfo='" + precautionaryMeasuresTextField.getText()
                    + "', safeZoneApp='" + safeZoneAppChoiceBox.getValue()
                    + "', overseasPlacementGHIC='" + globalHealthInsuranceChoiceBox.getValue()
                    + "', disabilityHealthCondition='" + adjustmentsChoiceBox.getValue()
                    + "', overseasPlacementSTAForm='" + overseasStudentApplicationFormChoiceBox.getValue()
                    + "', overseasPlacementRiskAssessment='" + overseasPlacementRiskEscalation.getValue()
                    + "', decName='" + nameTextField.getText()
                    + "', decSignature='" + signatureTextField.getText()
                    + "', decDate='" + dateDatePicker.getValue()
                    + "' WHERE username = '" + userN + "'";

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

    public void setForm() throws SQLException {
        // Create a database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Define the SQL query
        String sqlQuery = "SELECT * FROM savedStudentForm WHERE username = ?";

        try {
            // Create a prepared statement with the SQL query and set the username parameter
            PreparedStatement statement = connectDB.prepareStatement(sqlQuery);
            statement.setString(1, userN);

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

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                firstNameTextField.setText(column1);
                surnameTextField.setText(column2);
                studentNumberTextField.setText(column3);
                emailAddressTextField.setText(column4);
                programmeOfStudyTextField.setText(column5);
                schoolDepartmentTextField.setText(column6);
                contactTelephoneNumberTextField.setText(column7);
                if (Objects.equals(column8, "null")) {
                    internationalStudentWithVisaChoiceBox.setValue(null);
                } else {
                    internationalStudentWithVisaChoiceBox.setValue(column8);
                }
                if (Objects.equals(column9, "null")) {
                    studentVisaDurationChoiceBox.setValue(null);
                } else {
                    studentVisaDurationChoiceBox.setValue(column9);
                }
                nameOfOrgTextField.setText(column10);
                addressOfPlacementTextField.setText(column11);
                postcodeTextField.setText(column12);
                webAddressTextField.setText(column13);
                contactNameTextField.setText(column14);
                contactJobTitleTextField.setText(column15);
                contactEmailTextField.setText(column16);
                contactPTelephoneNumberTextField.setText(column17);
                roleTitleTextField.setText(column18);
                if (!Objects.equals(column19, "null")) {
                    LocalDate date1 = LocalDate.parse(column19, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    roleStartDateDatePicker.setValue(date1);
                }
                if (!Objects.equals(column20, "null")) {
                    LocalDate date2 = LocalDate.parse(column20, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    roleEndDateDatePicker.setValue(date2);
                }
                workingHoursPerWeekTextField.setText(column21);
                if (Objects.equals(column22, "null")) {
                    probationPeriodChoiceBox.setValue(null);
                } else {
                    probationPeriodChoiceBox.setValue(column22);
                }
                lengthOfProbationTextField.setText(column23);
                salaryPerYearTextField.setText(column24);
                sourceRoleTextField.setText(column25);
                if (Objects.equals(column26, "null")) {
                    informedPlacementChoiceBox.setValue(null);
                } else {
                    informedPlacementChoiceBox.setValue(column26);
                }
                roleDescriptionTextField.setText(column27);
                if (Objects.equals(column28, "null")) {
                    remoteWorkChoiceBox.setValue(null);
                } else {
                    remoteWorkChoiceBox.setValue(column28);
                }
                overviewRemoteWorkTextField.setText(column29);
                whyWorkHomeTextField.setText(column30);
                if (Objects.equals(column31, "null")) {
                    travelChoiceBox.setValue(null);
                } else {
                    travelChoiceBox.setValue(column31);
                }
                travelOtherTextField.setText(column32);
                if (Objects.equals(column33, "null")) {
                    roleLocationDifferentChoiceBox.setValue(null);
                } else {
                    roleLocationDifferentChoiceBox.setValue(column33);
                }
                detailsDifferentLocationTextField.setText(column34);
                if (Objects.equals(column35, "null")) {
                    overseasTravelRequiredChoiceBox.setValue(null);
                } else {
                    overseasTravelRequiredChoiceBox.setValue(column35);
                }
                if (Objects.equals(column36, "null")) {
                    overseasGuidanceChoiceBox.setValue(null);
                } else {
                    overseasGuidanceChoiceBox.setValue(column36);
                }
                if (Objects.equals(column37, "null")) {
                    overseasTravelChoiceBox.setValue(null);
                } else {
                    overseasTravelChoiceBox.setValue(column37);
                }
                if (Objects.equals(column38, "null")) {
                    accommodationArrangementsChoiceBox.setValue(null);
                } else {
                    accommodationArrangementsChoiceBox.setValue(column38);
                }
                accommodationOtherTextField.setText(column39);
                if (Objects.equals(column40, "null")) {
                    overseasPlacementOfficeChoiceBox.setValue(null);
                } else {
                    overseasPlacementOfficeChoiceBox.setValue(column40);
                }
                if (Objects.equals(column41, "null")) {
                    overseasHealthChoiceBox.setValue(null);
                } else {
                    overseasHealthChoiceBox.setValue(column41);
                }
                overseasHealthInfoTextField.setText(column42);
                if (Objects.equals(column43, "null")) {
                    precautionaryMeasuresChoiceBox.setValue(null);
                } else {
                    precautionaryMeasuresChoiceBox.setValue(column43);
                }
                precautionaryMeasuresTextField.setText(column44);
                if (Objects.equals(column45, "null")) {
                    safeZoneAppChoiceBox.setValue(null);
                } else {
                    safeZoneAppChoiceBox.setValue(column45);
                }
                if (Objects.equals(column46, "null")) {
                    globalHealthInsuranceChoiceBox.setValue(null);
                } else {
                    globalHealthInsuranceChoiceBox.setValue(column46);
                }
                if (Objects.equals(column47, "null")) {
                    adjustmentsChoiceBox.setValue(null);
                } else {
                    adjustmentsChoiceBox.setValue(column47);
                }
                if (Objects.equals(column48, "null")) {
                    overseasStudentApplicationFormChoiceBox.setValue(null);
                } else {
                    overseasStudentApplicationFormChoiceBox.setValue(column48);
                }
                if (Objects.equals(column49, "null")) {
                    overseasPlacementRiskEscalation.setValue(null);
                } else {
                    overseasPlacementRiskEscalation.setValue(column49);
                }
                nameTextField.setText(column50);
                signatureTextField.setText(column51);
                if (!Objects.equals(column52, "null")) {
                    LocalDate date3 = LocalDate.parse(column52, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    dateDatePicker.setValue(date3);
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

    public void saveButtonOnAction(ActionEvent event) {
        saveForm();
    }

    public void submitButtonOnAction(ActionEvent event) {
        checkSection1();
        if (sec1Valid) {
            System.out.print("Sec 1 valid");
            checkSection2();
            if (sec2Valid) {
                System.out.print("Sec 2 valid");
                checkSection3();
                if (sec3Valid) {
                    System.out.print("Sec 3 valid");
                    checkSection4();
                    if (sec4Valid) {
                        System.out.print("Sec 4 valid");
                        checkSection5();
                        if (sec5Valid) {
                            System.out.print("Sec 5 valid");
                            checkSection6();
                            if (sec6Valid) {
                                System.out.print("Sec 6 valid");
                                checkSection7();
                                if (sec7Valid) {
                                    System.out.print("Sec 7 valid");
                                    checkSection8();
                                    if (sec8Valid) {
                                        System.out.print("Sec 8 valid");
                                        checkSection9();
                                        if (sec9Valid) {
                                            System.out.print("Sec 9 valid");
                                            checkDeclaration();
                                            if (decValid) {
                                                System.out.print("Dec valid");
                                                submitForm();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void lockStudentNumber() {
        try {
            DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            String rowExsistsQuery = "SELECT count(1) FROM studentForm WHERE username = '" + userN + "'";

            ResultSet queryResult = statement.executeQuery(rowExsistsQuery);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    studentNumberTextField.setDisable(true);
                }
                else {
                    studentNumberTextField.setDisable(false);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

