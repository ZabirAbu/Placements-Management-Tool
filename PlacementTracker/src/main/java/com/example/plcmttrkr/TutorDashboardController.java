package com.example.plcmttrkr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TutorDashboardController extends LoginController{
    //Setting variables for pie chart data
    private int totalSavedForms, totalSubmittedForms, totalUsers;
    private int PPtotalSavedForms, PPtotalSubmittedForms;
    //Drag bar variables
    private double xOffset = 0;
    private double yOffset = 0;
    //Define FXML elements
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
    private Label welcomeUser;
    @FXML
    private Label dateLabel;
    @FXML
    private PieChart piechartOne;
    @FXML
    private PieChart piechartTwo;
    @FXML
    private Button helpButton;

    //Action event for the help button
    public void helpButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("placement_tutor_help.fxml")); //Load help fxml file
        Parent root = fxmlLoader.load();
        PlacementTutorHelpController formC = fxmlLoader.getController(); //Help page controller
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) helpButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
    }

    public void dragBarOnAction(MouseEvent event) { //Get coordinates for drag bar
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void dragBarOnMovementAction(MouseEvent event) { //Moving drag bar
        Stage stage = (Stage) dragBar.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void cancelButtonOnAction(ActionEvent event) { //Action event to terminate stage
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void minimiseButtonOnAction(ActionEvent event) { //Action event to minimise stage
        Stage stage = (Stage) minimiseButton.getScene().getWindow();
        stage.setIconified(true);
    }

    public void logoutButtonOnAction(ActionEvent event) throws IOException { //Logout action event
        Alert logoutAlert = new Alert (Alert.AlertType.CONFIRMATION) ; //Create alert box dialog
        logoutAlert.setTitle("Logout"); logoutAlert.setHeaderText ("Are you sure you want to log out?");

        if(logoutAlert.showAndWait().get() == ButtonType.OK){ //Wait for user response
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml")); //Load login page
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(loginScene);
            loginStage.show();
        }
    }

    public void formButtonOnAction(ActionEvent event) throws IOException, SQLException { //Form action event
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutor_form.fxml"));
        Parent root = fxmlLoader.load();
        TutorFormController TutorFormC = fxmlLoader.getController();
        TutorFormC.lockForm();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) formButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
    }

    public void setName(String p1, String p2) { //Set dashboard name
        welcomeUser.setText("Welcome " + p1 + " " + p2 + "!");
        dateLabel.setText(String.valueOf(java.time.LocalDate.now()));
    }

    public void setPieChart() { //Create and set pie charts
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        //SQL queries to get pie chart data
        String query = "SELECT COUNT(username) FROM accounts WHERE userRole = 'Student'";
        String query1 = "SELECT COUNT(username) FROM savedStudentForm";
        String query2 = "SELECT COUNT(username) FROM studentForm";


        String query4 = "SELECT COUNT(username) FROM savedProviderForm";
        String query5 = "SELECT COUNT(username) FROM providerForm";


        try {
            // Create a prepared statement with the SQL query
            PreparedStatement statement = connectDB.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            rs.next();
            totalUsers = rs.getInt(1);

            // Create a prepared statement with the SQL query
            PreparedStatement statement1 = connectDB.prepareStatement(query1);
            ResultSet rs1 = statement1.executeQuery();
            rs1.next();
            totalSavedForms = rs1.getInt(1);
            System.out.println("Total number of saved student forms: " + totalSavedForms);

            PreparedStatement statement2 = connectDB.prepareStatement(query2);
            ResultSet rs2 = statement2.executeQuery();
            rs2.next();
            totalSubmittedForms = rs2.getInt(1);
            System.out.println("Total number of submitted student forms: " + totalSubmittedForms);

            System.out.println("Total number of users not started or submitted: " + (totalUsers - totalSavedForms));

            //Calculating number of forms
            totalUsers = totalUsers - totalSavedForms;
            totalSavedForms = totalSavedForms - totalSubmittedForms;


            // Create a prepared statement with the SQL query
            PreparedStatement statement4 = connectDB.prepareStatement(query4);
            ResultSet rs4 = statement4.executeQuery();
            rs4.next();
            PPtotalSavedForms = rs4.getInt(1);
            System.out.println("Total number of saved student forms: " + PPtotalSavedForms);

            PreparedStatement statement5 = connectDB.prepareStatement(query5);
            ResultSet rs5 = statement5.executeQuery();
            rs5.next();
            PPtotalSubmittedForms = rs5.getInt(1);
            System.out.println("Total number of submitted student forms: " + PPtotalSubmittedForms);

            PPtotalSavedForms = PPtotalSavedForms - PPtotalSubmittedForms;




        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Creating pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Started student form (" + totalSavedForms + ")", totalSavedForms),
                        new PieChart.Data("Submitted student form (" + totalSubmittedForms + ")", totalSubmittedForms),
                        new PieChart.Data("Not started/submitted (" + totalUsers + ")", totalUsers));
        //Set pie chart with data
        final PieChart pieChart = new PieChart(pieChartData);
        piechartOne.setData(pieChartData);

        //Creating pie charts
        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                new PieChart.Data("Started provider forms (" + PPtotalSavedForms + ")", PPtotalSavedForms),
                new PieChart.Data("Submitted provider forms (" + PPtotalSubmittedForms + ")", PPtotalSubmittedForms));
        //Set pie chart with data
        final PieChart pieChart1 = new PieChart(pieChartData2);
        piechartTwo.setData(pieChartData2);
    }
}
