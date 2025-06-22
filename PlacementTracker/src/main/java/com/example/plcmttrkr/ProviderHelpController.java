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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProviderHelpController extends LoginController implements Initializable {

    //variables for storing the offset of the mouse cursor on the drag bar
    private double xOffset = 0;
    private double yOffset = 0;

    // FXML elements defined in the associated FXML file
    @FXML
    private AnchorPane dragBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button minimiseButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button providerFormButton;
    @FXML
    private Button formButton;
    @FXML
    private Hyperlink link1;
    @FXML
    private Hyperlink link2;
    @FXML
    private Hyperlink link3;
    @FXML
    private Hyperlink link4;
    @FXML
    private Hyperlink link5;

    //method allows user to click link and open website
    @FXML
    void setLink1(ActionEvent event) throws URISyntaxException, IOException {
        System.out.println("link1");
        Desktop.getDesktop().browse(new URI("https://www.gov.uk/employers-liability-insurance"));
    }

    //method allows user to click link and open website
    @FXML
    void setLink2(ActionEvent event) throws URISyntaxException, IOException {
        System.out.println("link2");
        Desktop.getDesktop().browse(new URI("https://le.ac.uk/"));
    }

    //method allows user to click link and open website
    @FXML
    void setLink3(ActionEvent event) throws URISyntaxException, IOException {
        System.out.println("link3");
        Desktop.getDesktop().browse(new URI("https://uniofleicester.sharepoint.com/sites/Insurance/SitePages/Staff-Student-Overseas-Travel.aspx"));
    }

    //method allows user to click link and open website
    @FXML
    void setLink4(ActionEvent event) throws URISyntaxException, IOException {
        System.out.println("link4");
        Desktop.getDesktop().browse(new URI("https://le.ac.uk/study/student-support/safezone"));
    }

    //method allows user to click link and open website
    @FXML
    void setLink5(ActionEvent event) throws URISyntaxException, IOException {
        System.out.println("link4");
        Desktop.getDesktop().browse(new URI("https://www.nhs.uk/using-the-nhs/healthcare-abroad/apply-for-a-free-uk-global-health-insurance-card-ghic/"));
    }

    // method for handling the mouse click event on the drag bar
    public void dragBarOnAction(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    // method for handling the mouse movement event on the drag bar
    public void dragBarOnMovementAction(MouseEvent event) {
        Stage stage = (Stage) dragBar.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    // method for handling the button click event on the cancel button
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    // method for handling the button click event on the minimise button
    public void minimiseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) minimiseButton.getScene().getWindow();
        stage.setIconified(true);
    }

    // method for handling the button click event on the logout button
    public void logoutButtonOnAction(ActionEvent event) throws IOException {
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Logout");
        logoutAlert.setHeaderText("Are you sure you want to log out?");
        // show the alert dialog and wait for the user's response

        if (logoutAlert.showAndWait().get() == ButtonType.OK) {
            // load the login FXML file and create a new login scene
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root);
            // get the current window and set the new login scene as the window's scene
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(loginScene);
            loginStage.show();
        }
    }
    // method for handling the button click event on the home button
    public void homeButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("placement_teamDashboard.fxml"));
        Parent root = fxmlLoader.load();
        Scene dashboardScene = new Scene(root);
        PlacementTeamDashboardController placementdashboardC = fxmlLoader.getController();
        placementdashboardC.setName(foreN, surN);
        Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }

    // method for handling the button click event on the form button
    public void formButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("provider_form.fxml"));
        Parent root = fxmlLoader.load();
        ProviderFormController ProviderFormC = fxmlLoader.getController();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) formButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
