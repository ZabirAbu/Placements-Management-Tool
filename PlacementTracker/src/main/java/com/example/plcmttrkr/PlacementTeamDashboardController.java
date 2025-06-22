package com.example.plcmttrkr;

import javafx.collections.FXCollections;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlacementTeamDashboardController extends LoginController {
    private int totalSavedForms, totalSubmittedForms, totalUsers;
    private int TTtotalSavedForms, TTtotalSubmittedForms;
    private double xOffset = 0;
    private double yOffset = 0;
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
    private Button helpbutton;
    @FXML
    private Label welcomeUser;
    @FXML
    private Label dateLabel;
    @FXML
    private PieChart piechartOne;
    @FXML
    private PieChart piechartTwo;


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
        Alert logoutAlert = new Alert (Alert.AlertType.CONFIRMATION) ;
        logoutAlert.setTitle("Logout"); logoutAlert.setHeaderText ("Are you sure you want to log out?");

        if(logoutAlert.showAndWait().get() == ButtonType.OK){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.setScene(loginScene);
            loginStage.show();
        }
    }

    public void formButtonOnAction(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("provider_form.fxml"));
        Parent root = fxmlLoader.load();
        ProviderFormController ProviderFormC = fxmlLoader.getController();
        ProviderFormC.lockForm();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) formButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }

    public void helpButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("provider_help.fxml"));
        Parent root = fxmlLoader.load();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) helpbutton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        dashboardStage.show();
    }

    public void setName(String p1, String p2) { //Set usernames details on dashboard
        welcomeUser.setText("Welcome " + p1 + " " + p2 + "!");
        dateLabel.setText(String.valueOf(java.time.LocalDate.now())); //Setting time
    }

    public void setPieChart() {
        //Creating database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        //SQL queries for pie chart
        String query = "SELECT COUNT(username) FROM accounts WHERE userRole = 'Student'";
        String query1 = "SELECT COUNT(username) FROM savedStudentForm";
        String query2 = "SELECT COUNT(username) FROM studentForm";


        String query4 = "SELECT COUNT(username) FROM savedTutorForm";
        String query5 = "SELECT COUNT(username) FROM tutorForm";


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

            totalUsers = totalUsers - totalSavedForms;
            totalSavedForms = totalSavedForms - totalSubmittedForms;


            // Create a prepared statement with the SQL query
            PreparedStatement statement4 = connectDB.prepareStatement(query4);
            ResultSet rs4 = statement4.executeQuery();
            rs4.next();
            TTtotalSavedForms = rs4.getInt(1);
            System.out.println("Total number of saved provider forms: " + TTtotalSavedForms);

            PreparedStatement statement5 = connectDB.prepareStatement(query5);
            ResultSet rs5 = statement5.executeQuery();
            rs5.next();
            TTtotalSubmittedForms = rs5.getInt(1);
            System.out.println("Total number of submitted provider forms: " + TTtotalSubmittedForms);

            TTtotalSavedForms = TTtotalSavedForms - TTtotalSubmittedForms;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( //Create pie charts
                new PieChart.Data("Started student form (" + totalSavedForms + ")", totalSavedForms),
                new PieChart.Data("Submitted student form (" + totalSubmittedForms + ")", totalSubmittedForms),
                new PieChart.Data("Not started/submitted (" + totalUsers + ")", totalUsers));
        final PieChart pieChart = new PieChart(pieChartData);
        piechartOne.setData(pieChartData);

        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                new PieChart.Data("Started tutor forms (" + TTtotalSavedForms + ")", TTtotalSavedForms), //Setting pie charts to correct elements
                new PieChart.Data("Submitted tutor forms (" + TTtotalSubmittedForms + ")", TTtotalSubmittedForms));
        final PieChart pieChart1 = new PieChart(pieChartData2);
        piechartTwo.setData(pieChartData2);
    }
}
