package com.example.plcmttrkr;

import com.mysql.cj.log.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PlacementTutorHelpController extends LoginController {

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
    private Button logoutButton;
    @FXML
    private Button formButton;
    @FXML
    private Button homeButton;


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
        Alert logoutAlert = new Alert (Alert.AlertType.CONFIRMATION) ;
        logoutAlert.setTitle("Logout"); logoutAlert.setHeaderText ("Are you sure you want to log out?");

        // show the alert dialog and wait for the user's response
        if(logoutAlert.showAndWait().get() == ButtonType.OK){
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

    // method for handling the button click event on the form button
    public void formButtonOnAction(ActionEvent event) throws IOException, SQLException {
        // load the tutor form FXML file and create a new tutor form scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutor_form.fxml"));
        Parent root = fxmlLoader.load();
        TutorFormController formC = fxmlLoader.getController();
        // configure the tutor form and lock it for editing
        formC.setForm();
        formC.lockForm();
        // get the current window and set the new tutor form scene as the window's scene
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) formButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
    }

    // method for handling the button click event on the home button
    public void homeButtonOnAction(ActionEvent event) throws IOException, SQLException {
        // load the tutor dashboard FXML file and create a new tutor dashboard scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutorDashboard.fxml"));
        Parent root = fxmlLoader.load();
        Scene dashboardScene = new Scene(root);
        // get the controller for the tutor dashboard and set the name and pie chart
        TutorDashboardController dashboardC = fxmlLoader.getController();
        dashboardC.setName(foreN, surN);
        dashboardC.setPieChart();
        // get the current stage and set the scene to the tutor dashboard scene
        Stage dashboardStage = (Stage) homeButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }
}


