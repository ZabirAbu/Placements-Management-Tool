package com.example.plcmttrkr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TutorFormController extends LoginController implements Initializable {
    public List<String> listOfStudentNumbers = new ArrayList<String>();
    private String column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17, column18, column19, column20, column21, column22, column23, column24, column25, column26, column27, column28, column29, column30, column31, column32, column33, column34;
    private boolean sectionValid1 = false, sectionValid2 = false, sectionValid3 = false, sectionValid4 = false, sectionValid5 = false, sectionValid6 = false, sectionValid7 = false, sectionValid8 = false, sectionValid9 = false, sectionValid10 = false, sectionValid11 = false, sectionValid12 = false;
    private String[] choice1 = {"Yes", "No"};
    private String[] choice2 = {"Yes", "No", "Not applicable"};
    private String[] choice3 = {"Low", "Medium", "High"};
    private String[] choice4 = {"N/A", "Low", "Medium", "High"};
    private String[] choice5 = {"Yes", "No", "N/A"};
    private String[] choice6 = {"Placement request authorised", "Placement request on hold until further information is provided (please provide reasons below)", "Placement request rejected (please provide reasons below)"};
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Button viewStudentFormButton;
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
    private Label errorMessageLabel;

    @FXML
    private TextField studentNameTextField;

    @FXML
    private TextField nameOfOrgTextField;
    @FXML
    private ChoiceBox<String> impactOnUniChoiceBox;

    @FXML
    private ChoiceBox<String> matchNameAndAddressChoiceBox;

    @FXML
    private ChoiceBox<String> minimumRequirementsDurationChoiceBox;
    @FXML
    private ChoiceBox<String> minimumRequirementsAcademicChoiceBox;
    @FXML
    private ChoiceBox<String> providerConfirmChoiceBox;
    @FXML
    private ChoiceBox<String> reasonableProviderChoiceBox;
    @FXML
    private ChoiceBox<String> studentVisaChoiceBox;
    @FXML
    private ChoiceBox<String> visaDateComplyChoiceBox;

    @FXML
    private ChoiceBox<String> workConditionsChoiceBox;
    @FXML
    private ChoiceBox<String> appropriateTrainingChoiceBox;
    @FXML
    private ChoiceBox<String> workFromHomeChoiceBox;
    @FXML
    private ChoiceBox<String> legitimacyChoiceBox;

    @FXML
    private ChoiceBox<String> travelIssuesChoiceBox;
    @FXML
    private ChoiceBox<String> multipleSitesChoiceBox;

    @FXML
    private ChoiceBox<String> riskAtMainLocationChoiceBox;
    @FXML
    private ChoiceBox<String> inappropriateAccommodationChoiceBox;

    @FXML
    private ChoiceBox<String> precautionaryMeasuresChoiceBox;

    @FXML
    private ChoiceBox<String> personalFactorsChoiceBox;

    @FXML
    private ChoiceBox<String> liabilityInsuranceChoiceBox;
    @FXML
    private ChoiceBox<String> employerLiabilityInsurance;
    @FXML
    private ChoiceBox<String> indemnityInsuranceChoiceBox;
    @FXML
    private ChoiceBox<String> healthAndSafetyChoiceBox;

    @FXML
    private ChoiceBox<String> objectionsChoiceBox;
    @FXML
    private ChoiceBox<String> confidentialityIssuesChoiceBox;

    @FXML
    private ChoiceBox<String> decisionChoiceBox;
    @FXML
    private TextField reasoningTextField;
    @FXML
    private TextField tutorNameTextField;
    @FXML
    private TextField tutorSignatureTextField;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private TextField placementSupervisorTextField;

    @FXML
    private TextField directorOfOperationsTextField;
    @FXML
    private DatePicker secondDateDatePicker;

    @FXML
    private Button submitButton;
    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> selectedStudentComboBox;

    @FXML
    private Label selectStudentErrorLabel;

    @FXML
    private Hyperlink tutFormHyperlink1;
    @FXML
    private Button helpButton;

    public void helpButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("placement_tutor_help.fxml"));
        Parent root = fxmlLoader.load();
        PlacementTutorHelpController formC = fxmlLoader.getController();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) helpButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutorDashboard.fxml"));
        Parent root = fxmlLoader.load();
        Scene dashboardScene = new Scene(root);
        TutorDashboardController tutorDashboardC = fxmlLoader.getController();
        tutorDashboardC.setName(foreN, surN);
        tutorDashboardC.setPieChart();
        Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        impactOnUniChoiceBox.getItems().addAll(choice3);
        matchNameAndAddressChoiceBox.getItems().addAll(choice1);
        minimumRequirementsDurationChoiceBox.getItems().addAll((choice1));
        minimumRequirementsAcademicChoiceBox.getItems().addAll((choice1));
        providerConfirmChoiceBox.getItems().addAll((choice1));
        reasonableProviderChoiceBox.getItems().addAll((choice1));
        studentVisaChoiceBox.getItems().addAll((choice1));
        visaDateComplyChoiceBox.getItems().addAll((choice5));
        workConditionsChoiceBox.getItems().addAll((choice3));
        appropriateTrainingChoiceBox.getItems().addAll((choice1));
        workFromHomeChoiceBox.getItems().addAll((choice3));
        legitimacyChoiceBox.getItems().addAll((choice4));
        travelIssuesChoiceBox.getItems().addAll((choice3));
        multipleSitesChoiceBox.getItems().addAll((choice3));
        riskAtMainLocationChoiceBox.getItems().addAll((choice3));
        inappropriateAccommodationChoiceBox.getItems().addAll((choice3));
        precautionaryMeasuresChoiceBox.getItems().addAll((choice3));
        personalFactorsChoiceBox.getItems().addAll((choice3));

        liabilityInsuranceChoiceBox.getItems().addAll((choice1));
        employerLiabilityInsurance.getItems().addAll((choice1));
        indemnityInsuranceChoiceBox.getItems().addAll((choice2));
        healthAndSafetyChoiceBox.getItems().addAll((choice1));

        objectionsChoiceBox.getItems().addAll((choice1));
        confidentialityIssuesChoiceBox.getItems().addAll((choice1));

        decisionChoiceBox.getItems().addAll((choice6));

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
        selectedStudentComboBox.getItems().addAll(listOfStudentNumbers);
    }

    @FXML
    private void goToTutUrl1() {
        openTutorFormURL("https://uniofleicester.sharepoint.com/sites/Insurance/SitePages/Staff-Student-Overseas-Travel.aspx");
    }

    public void openTutorFormURL(String url) {
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
        if (studentNameTextField.getText().isBlank()) {
            errorMessageLabel.setText("Please complete section 1 (A)!");
            sectionValid1 = false;
        } else {
            sectionValid1 = true;
        }

    }

    public void checkSection2() {
        if (nameOfOrgTextField.getText() == null) {
            errorMessageLabel.setText("Please complete section 2 (A)!");
            sectionValid2 = false;
            return;
        } else {
            sectionValid2 = true;
        }
        if (impactOnUniChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 2 (B)!");
            sectionValid2 = false;
            return;
        } else {
            sectionValid2 = true;
        }
    }

    public void checkSection3() {
        if (matchNameAndAddressChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 3 (A)!");
            sectionValid3 = false;
            return;
        } else {
            sectionValid3 = true;
        }
    }

    public void checkSection4() {
        if (minimumRequirementsDurationChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4 (A)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (minimumRequirementsAcademicChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4 (B)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (providerConfirmChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4 (C)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (reasonableProviderChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4 (D)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (studentVisaChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4 (E)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
        if (visaDateComplyChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 4 (F)!");
            sectionValid4 = false;
            return;
        } else {
            sectionValid4 = true;
        }
    }

    public void checkSection5() {
        if (workConditionsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5 (A)!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (appropriateTrainingChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5 (B)!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (workFromHomeChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5 (C)!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
        if (legitimacyChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 5 (D)!");
            sectionValid5 = false;
            return;
        } else {
            sectionValid5 = true;
        }
    }

    public void checkSection6() {
        if (travelIssuesChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 6 (A)!");
            sectionValid6 = false;
            return;
        } else {
            sectionValid6 = true;
        }
        if (multipleSitesChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 6 (B)!");
            sectionValid6 = false;
            return;
        } else {
            sectionValid6 = true;
        }
    }

    public void checkSection7() {
        if (riskAtMainLocationChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 7 (A)!");
            sectionValid7 = false;
            return;
        } else {
            sectionValid7 = true;
        }
        if (inappropriateAccommodationChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 7 (B)!");
            sectionValid7 = false;
            return;
        } else {
            sectionValid7 = true;
        }
    }

    public void checkSection8() {
        if (precautionaryMeasuresChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 8 (A)!");
            sectionValid8 = false;
            return;
        } else {
            sectionValid8 = true;
        }
    }

    public void checkSection9() {
        if (personalFactorsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 9 (A)!");
            sectionValid9 = false;
            return;
        } else {
            sectionValid9 = true;
        }
    }

    public void checkSection10() {
        if (liabilityInsuranceChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 10 (A)!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (employerLiabilityInsurance.getValue() == null) {
            errorMessageLabel.setText("Please complete section 10 (B)!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (indemnityInsuranceChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 10 (C)!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
        if (healthAndSafetyChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 10 (D)!");
            sectionValid10 = false;
            return;
        } else {
            sectionValid10 = true;
        }
    }

    public void checkSection11() {
        if (objectionsChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 11 (A)!");
            sectionValid11 = false;
            return;
        } else {
            sectionValid11 = true;
        }
        if (confidentialityIssuesChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 11 (B)!");
            sectionValid11 = false;
            return;
        } else {
            sectionValid11 = true;
        }
    }

    public void checkSection12() {
        if (decisionChoiceBox.getValue() == null) {
            errorMessageLabel.setText("Please complete section 12 (Authorisation)");
            sectionValid12 = false;
            return;
        } else {
            sectionValid12 = true;
            if ((!("Placement request authorised").equals(decisionChoiceBox.getValue()) && (reasoningTextField.getText() == null))) {
                errorMessageLabel.setText("Please complete section 12 (Provide reasoning)!");
                sectionValid12 = false;
                return;
            }
        }
        if (tutorNameTextField.getText() == null) {
            errorMessageLabel.setText("Please complete section 12 (Name Of Tutor)!");
            sectionValid12 = false;
            return;
        } else {
            sectionValid12 = true;
        }
        if (tutorSignatureTextField.getText() == null) {
            errorMessageLabel.setText("Please complete section 12 (Placement Tutor Signature)!");
            sectionValid12 = false;
            return;
        } else {
            sectionValid12 = true;
        }
        if (dateDatePicker.getValue() == null) {
            errorMessageLabel.setText("Please complete section 12 (Date)!");
            sectionValid12 = false;
            return;
        } else {
            sectionValid12 = true;
        }
        if (placementSupervisorTextField.getText() == null) {
            errorMessageLabel.setText("Please complete section 12 (Allocated Placement Supervisor)!");
            sectionValid12 = false;
            return;
        } else {
            sectionValid12 = true;
        }
    }

    public void submitButtonOnAction(ActionEvent event) {
        if (selectedStudentComboBox.getValue() == null) {
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
            checkSection10();
        }
        else {
            return;
        }
        if (sectionValid10) {
            System.out.print("Sec 10 valid");
            checkSection11();
        }
        else {
            return;
        }
        if (sectionValid11) {
            System.out.print("Sec 11 valid");
            checkSection12();
        }
        else {
            return;
        }
        if (sectionValid12) {
            System.out.print("Sec 12 valid");
        }
        else {
            return;
        }
        if (selectedStudentComboBox.getValue() == null) {
            errorMessageLabel.setText("Please select a student number to submit the form!");
            return;
        }
        else {
            submitForm();
        }
    }

    public void saveForm() {
        try {
            DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();

            String rowExsistsQuery = "SELECT count(1) FROM savedTutorForm WHERE studentNumber = '" + selectedStudentComboBox.getValue() + "'";


            String insertFields = "INSERT INTO savedTutorForm(studentNumber, username, studentName , nameOfOrganisation, impactOnUniversity, matchNameAndAddress, minimumRequirementsDuration, minimumRequirementsAcademic, providerConfirm, reasonableProvider, studentVisa, visaDateComply, workConditions, appropriateTraining, workFromHome, legitimacy, travelIssues, multipleSites, riskAtMainLocation, inappropriateAccommodation, precautionaryMeasures, personalFactors, liabilityInsurance, employerLiability, indemnityInsurance, healthAndSafety, objections, confidentialityIssues, decision, reasoning, tutorName, tutorSignature, dateDatePicker, placementSupervisor, directorOfOperations, secondDateDatePicker) VALUES ('";
            String insertValues = selectedStudentComboBox.getValue() + "','" + userN + "','" + studentNameTextField.getText() + "','" + nameOfOrgTextField.getText() + "','" + impactOnUniChoiceBox.getValue() + "','" + matchNameAndAddressChoiceBox.getValue() + "','" + minimumRequirementsDurationChoiceBox.getValue() + "','" + minimumRequirementsAcademicChoiceBox.getValue() + "','" + providerConfirmChoiceBox.getValue() + "','" + reasonableProviderChoiceBox.getValue() + "','" + studentVisaChoiceBox.getValue() + "','" + visaDateComplyChoiceBox.getValue() + "','" + workConditionsChoiceBox.getValue() + "','" + appropriateTrainingChoiceBox.getValue() + "','" + workFromHomeChoiceBox.getValue() + "','" + legitimacyChoiceBox.getValue() + "','" + travelIssuesChoiceBox.getValue() + "','" + multipleSitesChoiceBox.getValue() + "','" + riskAtMainLocationChoiceBox.getValue() + "','" + inappropriateAccommodationChoiceBox.getValue() + "','" + precautionaryMeasuresChoiceBox.getValue() + "','" + personalFactorsChoiceBox.getValue() + "','" + liabilityInsuranceChoiceBox.getValue() + "','" + employerLiabilityInsurance.getValue() + "','" + indemnityInsuranceChoiceBox.getValue() + "','" + healthAndSafetyChoiceBox.getValue() + "','" + objectionsChoiceBox.getValue() + "','" + confidentialityIssuesChoiceBox.getValue() + "','" + decisionChoiceBox.getValue() + "','" + reasoningTextField.getText() + "','" + tutorNameTextField.getText() + "','" + tutorSignatureTextField.getText() + "','" + dateDatePicker.getValue() + "','" + placementSupervisorTextField.getText() + "','" + directorOfOperationsTextField.getText() + "','" + secondDateDatePicker.getValue() + "')";

                    String updateFields = "UPDATE savedTutorForm SET studentName='" + studentNameTextField.getText()
                    + "', nameOfOrganisation='" + nameOfOrgTextField.getText()
                    + "', impactOnUniversity='" + impactOnUniChoiceBox.getValue()
                    + "', matchNameAndAddress='" + matchNameAndAddressChoiceBox.getValue()
                    + "', minimumRequirementsDuration='" + minimumRequirementsDurationChoiceBox.getValue()
                    + "', minimumRequirementsAcademic='" + minimumRequirementsAcademicChoiceBox.getValue()
                    + "', providerConfirm='" + providerConfirmChoiceBox.getValue()
                    + "', reasonableProvider='" + reasonableProviderChoiceBox.getValue()
                    + "', studentVisa='" + studentVisaChoiceBox.getValue()
                    + "', visaDateComply='" + visaDateComplyChoiceBox.getValue()
                    + "', workConditions='" + workConditionsChoiceBox.getValue()
                    + "', appropriateTraining='" + appropriateTrainingChoiceBox.getValue()
                    + "', workFromHome='" + workFromHomeChoiceBox.getValue()
                    + "', legitimacy='" + legitimacyChoiceBox.getValue()
                    + "', travelIssues='" + travelIssuesChoiceBox.getValue()
                    + "', multipleSites='" + multipleSitesChoiceBox.getValue()
                    + "', riskAtMainLocation='" + riskAtMainLocationChoiceBox.getValue()
                    + "', inappropriateAccommodation='" + inappropriateAccommodationChoiceBox.getValue()
                    + "', precautionaryMeasures='" + precautionaryMeasuresChoiceBox.getValue()
                    + "', personalFactors='" + personalFactorsChoiceBox.getValue()
                    + "', liabilityInsurance='" + liabilityInsuranceChoiceBox.getValue()
                    + "', employerLiability='" + employerLiabilityInsurance.getValue()
                    + "', indemnityInsurance='" + indemnityInsuranceChoiceBox.getValue()
                    + "', healthAndSafety='" + healthAndSafetyChoiceBox.getValue()
                    + "', objections='" + objectionsChoiceBox.getValue()
                    + "', confidentialityIssues='" + confidentialityIssuesChoiceBox.getValue()
                    + "', decision='" + decisionChoiceBox.getValue()
                    + "', reasoning='" + reasoningTextField.getText()
                    + "', tutorName='" + tutorNameTextField.getText()
                    + "', tutorSignature='" + tutorSignatureTextField.getText()
                    + "', dateDatePicker='" + dateDatePicker.getValue()
                    + "', placementSupervisor='" + placementSupervisorTextField.getText()
                    + "', directorOfOperations='" + directorOfOperationsTextField.getText()
                    + "', secondDateDatePicker='" + secondDateDatePicker.getValue()
                    + "' WHERE studentNumber = '" + selectedStudentComboBox.getValue() + "'";

            ResultSet queryResult = statement.executeQuery(rowExsistsQuery);
            String insertToRegister = insertFields + insertValues;

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    try {
                        statement.executeUpdate(updateFields);
                        errorMessageLabel.setText("Your form has been successfully saved and updated!");
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

            String rowExsistsQuery = "SELECT count(1) FROM tutorForm WHERE studentNumber = '" + selectedStudentComboBox.getValue() + "'";


            String insertFields = "INSERT INTO tutorForm(studentNumber, username, studentName , nameOfOrganisation, impactOnUniversity, matchNameAndAddress, minimumRequirementsDuration, minimumRequirementsAcademic, providerConfirm, reasonableProvider, studentVisa, visaDateComply, workConditions, appropriateTraining, workFromHome, legitimacy, travelIssues, multipleSites, riskAtMainLocation, inappropriateAccommodation, precautionaryMeasures, personalFactors, liabilityInsurance, employerLiability, indemnityInsurance, healthAndSafety, objections, confidentialityIssues, decision, reasoning, tutorName, tutorSignature, dateDatePicker, placementSupervisor, directorOfOperations, secondDateDatePicker) VALUES ('";
            String insertValues = selectedStudentComboBox.getValue() + "','" + userN + "','" + studentNameTextField.getText() + "','" + nameOfOrgTextField.getText() + "','" + impactOnUniChoiceBox.getValue() + "','" + matchNameAndAddressChoiceBox.getValue() + "','" + minimumRequirementsDurationChoiceBox.getValue() + "','" + minimumRequirementsAcademicChoiceBox.getValue() + "','" + providerConfirmChoiceBox.getValue() + "','" + reasonableProviderChoiceBox.getValue() + "','" + studentVisaChoiceBox.getValue() + "','" + visaDateComplyChoiceBox.getValue() + "','" + workConditionsChoiceBox.getValue() + "','" + appropriateTrainingChoiceBox.getValue() + "','" + workFromHomeChoiceBox.getValue() + "','" + legitimacyChoiceBox.getValue() + "','" + travelIssuesChoiceBox.getValue() + "','" + multipleSitesChoiceBox.getValue() + "','" + riskAtMainLocationChoiceBox.getValue() + "','" + inappropriateAccommodationChoiceBox.getValue() + "','" + precautionaryMeasuresChoiceBox.getValue() + "','" + personalFactorsChoiceBox.getValue() + "','" + liabilityInsuranceChoiceBox.getValue() + "','" + employerLiabilityInsurance.getValue() + "','" + indemnityInsuranceChoiceBox.getValue() + "','" + healthAndSafetyChoiceBox.getValue() + "','" + objectionsChoiceBox.getValue() + "','" + confidentialityIssuesChoiceBox.getValue() + "','" + decisionChoiceBox.getValue() + "','" + reasoningTextField.getText() + "','" + tutorNameTextField.getText() + "','" + tutorSignatureTextField.getText() + "','" + dateDatePicker.getValue() + "','" + placementSupervisorTextField.getText() + "','" + directorOfOperationsTextField.getText() + "','" + secondDateDatePicker.getValue() + "')";

            String updateFields = "UPDATE tutorForm SET studentName='" + studentNameTextField.getText()
                    + "', studentNumber='" + selectedStudentComboBox.getValue()
                    + "', nameOfOrganisation='" + nameOfOrgTextField.getText()
                    + "', impactOnUniversity='" + impactOnUniChoiceBox.getValue()
                    + "', matchNameAndAddress='" + matchNameAndAddressChoiceBox.getValue()
                    + "', minimumRequirementsDuration='" + minimumRequirementsDurationChoiceBox.getValue()
                    + "', minimumRequirementsAcademic='" + minimumRequirementsAcademicChoiceBox.getValue()
                    + "', providerConfirm='" + providerConfirmChoiceBox.getValue()
                    + "', reasonableProvider='" + reasonableProviderChoiceBox.getValue()
                    + "', studentVisa='" + studentVisaChoiceBox.getValue()
                    + "', visaDateComply='" + visaDateComplyChoiceBox.getValue()
                    + "', workConditions='" + workConditionsChoiceBox.getValue()
                    + "', appropriateTraining='" + appropriateTrainingChoiceBox.getValue()
                    + "', workFromHome='" + workFromHomeChoiceBox.getValue()
                    + "', legitimacy='" + legitimacyChoiceBox.getValue()
                    + "', travelIssues='" + travelIssuesChoiceBox.getValue()
                    + "', multipleSites='" + multipleSitesChoiceBox.getValue()
                    + "', riskAtMainLocation='" + riskAtMainLocationChoiceBox.getValue()
                    + "', inappropriateAccommodation='" + inappropriateAccommodationChoiceBox.getValue()
                    + "', precautionaryMeasures='" + precautionaryMeasuresChoiceBox.getValue()
                    + "', personalFactors='" + personalFactorsChoiceBox.getValue()
                    + "', liabilityInsurance='" + liabilityInsuranceChoiceBox.getValue()
                    + "', employerLiability='" + employerLiabilityInsurance.getValue()
                    + "', indemnityInsurance='" + indemnityInsuranceChoiceBox.getValue()
                    + "', healthAndSafety='" + healthAndSafetyChoiceBox.getValue()
                    + "', objections='" + objectionsChoiceBox.getValue()
                    + "', confidentialityIssues='" + confidentialityIssuesChoiceBox.getValue()
                    + "', decision='" + decisionChoiceBox.getValue()
                    + "', reasoning='" + reasoningTextField.getText()
                    + "', tutorName='" + tutorNameTextField.getText()
                    + "', tutorSignature='" + tutorSignatureTextField.getText()
                    + "', dateDatePicker='" + dateDatePicker.getValue()
                    + "', placementSupervisor='" + placementSupervisorTextField.getText()
                    + "', directorOfOperations='" + directorOfOperationsTextField.getText()
                    + "', secondDateDatePicker='" + secondDateDatePicker.getValue()
                    + "' WHERE studentNumber = '" + selectedStudentComboBox.getValue() + "'";

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
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutorDashboard.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene dashboardScene = new Scene(root);
                            TutorDashboardController dashboardC = fxmlLoader.getController();
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
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutorDashboard.fxml"));
                            Parent root = fxmlLoader.load();
                            Scene dashboardScene = new Scene(root);
                            TutorDashboardController dashboardC = fxmlLoader.getController();
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

    public void setForm() throws SQLException {
        // Create a database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Define the SQL query
        String sqlQuery = "SELECT * FROM savedTutorForm WHERE studentNumber = ?";

        try {
            // Create a prepared statement with the SQL query and set the username parameter
            PreparedStatement statement = connectDB.prepareStatement(sqlQuery);
            statement.setString(1, selectedStudentComboBox.getValue());

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the current row
                column1 = resultSet.getString(4);
                column2 = resultSet.getString(5);
                column3 = resultSet.getString(6);
                column4 = resultSet.getString(7);
                column5 = resultSet.getString(8);
                column6 = resultSet.getString(9);
                column7 = resultSet.getString(10);
                column8 = resultSet.getString(11);
                column9 = resultSet.getString(12);
                column10 = resultSet.getString(13);
                column11 = resultSet.getString(14);
                column12 = resultSet.getString(15);
                column13 = resultSet.getString(16);
                column14 = resultSet.getString(17);
                column15 = resultSet.getString(18);
                column16 = resultSet.getString(19);
                column17 = resultSet.getString(20);
                column18 = resultSet.getString(21);
                column19 = resultSet.getString(22);
                column20 = resultSet.getString(23);
                column21 = resultSet.getString(24);
                column22 = resultSet.getString(25);
                column23 = resultSet.getString(26);
                column24 = resultSet.getString(27);
                column25 = resultSet.getString(28);
                column26 = resultSet.getString(29);
                column27 = resultSet.getString(30);
                column28 = resultSet.getString(31);
                column29 = resultSet.getString(32);
                column30 = resultSet.getString(33);
                column31 = resultSet.getString(34);
                column32 = resultSet.getString(35);
                column33 = resultSet.getString(36);
                column34 = resultSet.getString(37);


                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                studentNameTextField.setText(column1);
                nameOfOrgTextField.setText(column2);

                if (Objects.equals(column3, "null")) {
                    impactOnUniChoiceBox.setValue(null);
                }
                else {
                    impactOnUniChoiceBox.setValue(column3);
                }
                if (Objects.equals(column4, "null")) {
                    matchNameAndAddressChoiceBox.setValue(null);
                }
                else {
                    matchNameAndAddressChoiceBox.setValue(column4);
                }
                if (Objects.equals(column5, "null")) {
                    minimumRequirementsDurationChoiceBox.setValue(null);
                }
                else {
                    minimumRequirementsDurationChoiceBox.setValue(column5);
                }
                if (Objects.equals(column6, "null")) {
                    minimumRequirementsAcademicChoiceBox.setValue(null);
                }
                else {
                    minimumRequirementsAcademicChoiceBox.setValue(column6);
                }
                if (Objects.equals(column7, "null")) {
                    providerConfirmChoiceBox.setValue(null);
                }
                else {
                    providerConfirmChoiceBox.setValue(column7);
                }
                if (Objects.equals(column8, "null")) {
                    reasonableProviderChoiceBox.setValue(null);
                }
                else {
                    reasonableProviderChoiceBox.setValue(column8);
                }
                if (Objects.equals(column9, "null")) {
                    studentVisaChoiceBox.setValue(null);
                }
                else {
                    studentVisaChoiceBox.setValue(column9);
                }
                if (Objects.equals(column10, "null")) {
                    visaDateComplyChoiceBox.setValue(null);
                }
                else {
                    visaDateComplyChoiceBox.setValue(column10);
                }
                if (Objects.equals(column11, "null")) {
                    workConditionsChoiceBox.setValue(null);
                }
                else {
                    workConditionsChoiceBox.setValue(column11);
                }
                if (Objects.equals(column12, "null")) {
                    appropriateTrainingChoiceBox.setValue(null);
                }
                else {
                    appropriateTrainingChoiceBox.setValue(column12);
                }
                if (Objects.equals(column13, "null")) {
                    workFromHomeChoiceBox.setValue(null);
                }
                else {
                    workFromHomeChoiceBox.setValue(column13);
                }
                if (Objects.equals(column14, "null")) {
                    legitimacyChoiceBox.setValue(null);
                }
                else {
                    legitimacyChoiceBox.setValue(column14);
                }
                if (Objects.equals(column15, "null")) {
                    travelIssuesChoiceBox.setValue(null);
                }
                else {
                    travelIssuesChoiceBox.setValue(column15);
                }
                if (Objects.equals(column16, "null")) {
                    multipleSitesChoiceBox.setValue(null);
                }
                else {
                    multipleSitesChoiceBox.setValue(column16);
                }
                if (Objects.equals(column17, "null")) {
                    riskAtMainLocationChoiceBox.setValue(null);
                }
                else {
                    riskAtMainLocationChoiceBox.setValue(column17);
                }
                if (Objects.equals(column18, "null")) {
                    inappropriateAccommodationChoiceBox.setValue(null);
                }
                else {
                    inappropriateAccommodationChoiceBox.setValue(column18);
                }
                if (Objects.equals(column19, "null")) {
                    precautionaryMeasuresChoiceBox.setValue(null);
                }
                else {
                    precautionaryMeasuresChoiceBox.setValue(column19);
                }
                if (Objects.equals(column20, "null")) {
                    personalFactorsChoiceBox.setValue(null);
                }
                else {
                    personalFactorsChoiceBox.setValue(column20);
                }
                if (Objects.equals(column21, "null")) {
                    liabilityInsuranceChoiceBox.setValue(null);
                }
                else {
                    liabilityInsuranceChoiceBox.setValue(column21);
                }
                if (Objects.equals(column22, "null")) {
                    employerLiabilityInsurance.setValue(null);
                }
                else {
                    employerLiabilityInsurance.setValue(column22);
                }
                if (Objects.equals(column23, "null")) {
                    indemnityInsuranceChoiceBox.setValue(null);
                }
                else {
                    indemnityInsuranceChoiceBox.setValue(column23);
                }
                if (Objects.equals(column24, "null")) {
                    healthAndSafetyChoiceBox.setValue(null);
                }
                else {
                    healthAndSafetyChoiceBox.setValue(column24);
                }
                if (Objects.equals(column25, "null")) {
                    objectionsChoiceBox.setValue(null);
                }
                else {
                    objectionsChoiceBox.setValue(column25);
                }
                if (Objects.equals(column26, "null")) {
                    confidentialityIssuesChoiceBox.setValue(null);
                }
                else {
                    confidentialityIssuesChoiceBox.setValue(column26);
                }
                if (Objects.equals(column27, "null")) {
                    decisionChoiceBox.setValue(null);
                }
                else {
                    decisionChoiceBox.setValue(column27);
                }
                if (Objects.equals(column28, "null")) {
                    reasoningTextField.setText(null);
                }
                else {
                    reasoningTextField.setText(column28);
                }
                if (Objects.equals(column29, "null")) {
                    tutorNameTextField.setText(null);
                }
                else {
                    tutorNameTextField.setText(column29);
                }
                if (Objects.equals(column30, "null")) {
                    tutorSignatureTextField.setText(null);
                }
                else {
                    tutorSignatureTextField.setText(column30);
                }
                if (!Objects.equals(column31, "null")) {
                    LocalDate date3 = LocalDate.parse(column31, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    dateDatePicker.setValue(date3);
                }
                if (Objects.equals(column32, "null")) {
                    placementSupervisorTextField.setText(null);
                }
                else {
                    placementSupervisorTextField.setText(column32);
                }
                if (Objects.equals(column33, "null")) {
                    directorOfOperationsTextField.setText(null);
                }
                else {
                    directorOfOperationsTextField.setText(column33);
                }
                if (!Objects.equals(column34, "null")) {
                    LocalDate date3 = LocalDate.parse(column34, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    secondDateDatePicker.setValue(date3);
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
        if (selectedStudentComboBox.getValue() == null) {
            errorMessageLabel.setText("Please select a student!");
        }
        else {
            saveForm();
        }
    }

    public void selectedStudentComboBoxOnAction(ActionEvent event) throws SQLException {
        selectStudentErrorLabel.setText("");
        resetForm();
        lockForm();
        if (selectedStudentComboBox.getValue() == null) {
            selectStudentErrorLabel.setText("Please select a student before proceeding!");

        }
        else {
            setForm();
        }
    }

    public void resetForm() {
        studentNameTextField.setText("");
        nameOfOrgTextField.setText("");
        impactOnUniChoiceBox.setValue(null);
        matchNameAndAddressChoiceBox.setValue(null);
        minimumRequirementsDurationChoiceBox.setValue(null);
        minimumRequirementsAcademicChoiceBox.setValue(null);
        providerConfirmChoiceBox.setValue(null);
        reasonableProviderChoiceBox.setValue(null);
        studentVisaChoiceBox.setValue(null);
        visaDateComplyChoiceBox.setValue(null);
        workConditionsChoiceBox.setValue(null);
        appropriateTrainingChoiceBox.setValue(null);
        workFromHomeChoiceBox.setValue(null);
        legitimacyChoiceBox.setValue(null);
        travelIssuesChoiceBox.setValue(null);
        multipleSitesChoiceBox.setValue(null);
        riskAtMainLocationChoiceBox.setValue(null);
        inappropriateAccommodationChoiceBox.setValue(null);
        precautionaryMeasuresChoiceBox.setValue(null);
        personalFactorsChoiceBox.setValue(null);
        liabilityInsuranceChoiceBox.setValue(null);
        employerLiabilityInsurance.setValue(null);
        indemnityInsuranceChoiceBox.setValue(null);
        healthAndSafetyChoiceBox.setValue(null);
        objectionsChoiceBox.setValue(null);
        confidentialityIssuesChoiceBox.setValue(null);
        decisionChoiceBox.setValue(null);
        reasoningTextField.setText("");
        tutorNameTextField.setText("");
        tutorSignatureTextField.setText("");
        dateDatePicker.setValue(null);
        placementSupervisorTextField.setText("");
        directorOfOperationsTextField.setText("");
        secondDateDatePicker.setValue(null);
    }

    public void viewStudentFormButtonOnAction(ActionEvent event) throws IOException, SQLException {
        if (selectedStudentComboBox.getValue() == null) {
            selectStudentErrorLabel.setText("Please select a student before proceeding!");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_student_form.fxml"));
            Parent root = fxmlLoader.load();
            Scene dashboardScene = new Scene(root);
            ViewStudentFormController studentF = fxmlLoader.getController();
            studentF.viewStudentFormSet(selectedStudentComboBox.getValue());
            Stage stage = new Stage();
            stage.setScene(dashboardScene);
            stage.initStyle(StageStyle.UNDECORATED);
            //dashboardStage.centerOnScreen();
            stage.show();
        }
    }

    public void viewProviderFormButtonOnAction(ActionEvent event) throws IOException, SQLException {
        if (selectedStudentComboBox.getValue() == null) {
            selectStudentErrorLabel.setText("Please select a student before proceeding!");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view_provider_form.fxml"));
            Parent root = fxmlLoader.load();
            Scene dashboardScene = new Scene(root);
            ViewProviderFormController providerF = fxmlLoader.getController();
            providerF.viewProviderFormSet(selectedStudentComboBox.getValue());
            Stage stage = new Stage();
            stage.setScene(dashboardScene);
            stage.initStyle(StageStyle.UNDECORATED);
            //dashboardStage.centerOnScreen();
            stage.show();
        }
    }

    public void lockForm() {
        if (selectedStudentComboBox.getValue() == null) {
            studentNameTextField.setDisable(true);
            nameOfOrgTextField.setDisable(true);
            impactOnUniChoiceBox.setDisable(true);
            matchNameAndAddressChoiceBox.setDisable(true);
            minimumRequirementsDurationChoiceBox.setDisable(true);
            minimumRequirementsAcademicChoiceBox.setDisable(true);
            providerConfirmChoiceBox.setDisable(true);
            reasonableProviderChoiceBox.setDisable(true);
            studentVisaChoiceBox.setDisable(true);
            visaDateComplyChoiceBox.setDisable(true);
            workConditionsChoiceBox.setDisable(true);
            appropriateTrainingChoiceBox.setDisable(true);
            workFromHomeChoiceBox.setDisable(true);
            legitimacyChoiceBox.setDisable(true);
            travelIssuesChoiceBox.setDisable(true);
            multipleSitesChoiceBox.setDisable(true);
            riskAtMainLocationChoiceBox.setDisable(true);
            inappropriateAccommodationChoiceBox.setDisable(true);
            precautionaryMeasuresChoiceBox.setDisable(true);
            personalFactorsChoiceBox.setDisable(true);
            liabilityInsuranceChoiceBox.setDisable(true);
            employerLiabilityInsurance.setDisable(true);
            indemnityInsuranceChoiceBox.setDisable(true);
            healthAndSafetyChoiceBox.setDisable(true);
            objectionsChoiceBox.setDisable(true);
            confidentialityIssuesChoiceBox.setDisable(true);
            decisionChoiceBox.setDisable(true);
            reasoningTextField.setDisable(true);
            tutorNameTextField.setDisable(true);
            tutorSignatureTextField.setDisable(true);
            dateDatePicker.setDisable(true);
            placementSupervisorTextField.setDisable(true);
            directorOfOperationsTextField.setDisable(true);
            secondDateDatePicker.setDisable(true);
        }
        else {
            studentNameTextField.setDisable(false);
            nameOfOrgTextField.setDisable(false);
            impactOnUniChoiceBox.setDisable(false);
            matchNameAndAddressChoiceBox.setDisable(false);
            minimumRequirementsDurationChoiceBox.setDisable(false);
            minimumRequirementsAcademicChoiceBox.setDisable(false);
            providerConfirmChoiceBox.setDisable(false);
            reasonableProviderChoiceBox.setDisable(false);
            studentVisaChoiceBox.setDisable(false);
            visaDateComplyChoiceBox.setDisable(false);
            workConditionsChoiceBox.setDisable(false);
            appropriateTrainingChoiceBox.setDisable(false);
            workFromHomeChoiceBox.setDisable(false);
            legitimacyChoiceBox.setDisable(false);
            travelIssuesChoiceBox.setDisable(false);
            multipleSitesChoiceBox.setDisable(false);
            riskAtMainLocationChoiceBox.setDisable(false);
            inappropriateAccommodationChoiceBox.setDisable(false);
            precautionaryMeasuresChoiceBox.setDisable(false);
            personalFactorsChoiceBox.setDisable(false);
            liabilityInsuranceChoiceBox.setDisable(false);
            employerLiabilityInsurance.setDisable(false);
            indemnityInsuranceChoiceBox.setDisable(false);
            healthAndSafetyChoiceBox.setDisable(false);
            objectionsChoiceBox.setDisable(false);
            confidentialityIssuesChoiceBox.setDisable(false);
            decisionChoiceBox.setDisable(false);
            reasoningTextField.setDisable(false);
            tutorNameTextField.setDisable(false);
            tutorSignatureTextField.setDisable(false);
            dateDatePicker.setDisable(false);
            placementSupervisorTextField.setDisable(false);
            directorOfOperationsTextField.setDisable(false);
            secondDateDatePicker.setDisable(false);
        }
    }
}


