package com.example.plcmttrkr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.Objects;

public class DashboardController extends LoginController{
    private double score = 0;
    private String username, uniName, course, sNumber;
    public String sn;
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
    private Label welcomeUser;
    @FXML
    private Label dateLabel;
    @FXML
    private ProgressBar progessBar;
    @FXML
    private Label text1;
    @FXML
    private Label text2;
    @FXML
    private Label text3;
    @FXML
    private Label text4;
    @FXML
    private Label text5;
    @FXML
    private Label text6;
    @FXML
    private TextField uniTextField;

    @FXML
    private TextField courseTextField;
    @FXML
    private TextField snumberTextField;
    @FXML
    private Button updateInformationButton;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label errorMsgLabel;
    @FXML
    private Button helpButton;




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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form.fxml"));
        Parent root = fxmlLoader.load();
        FormController formC = fxmlLoader.getController();
        formC.setForm();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) formButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
    }

    public void helpButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_help.fxml"));
        Parent root = fxmlLoader.load();
        StudentHelpController formC = fxmlLoader.getController();
        Scene dashboardScene = new Scene(root);
        Stage dashboardStage = (Stage) helpButton.getScene().getWindow();
        dashboardStage.setScene(dashboardScene);
        //dashboardStage.centerOnScreen();
        dashboardStage.show();
    }

    public void setName(String p1, String p2) {
        welcomeUser.setText("Welcome " + p1 + " " + p2 + "!");
        dateLabel.setText(String.valueOf(java.time.LocalDate.now()));
    }

    public void setDashboard() throws SQLException {
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);
        text5.setVisible(false);
        text6.setVisible(false);
        progessBar.setProgress(0);
        // Create a database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement sb = connectDB.createStatement();

        // Define the SQL query
        String sqlQuery = "SELECT studentNumber FROM savedStudentForm WHERE username = '" + userN + "'";

        try {
            // Create a prepared statement with the SQL query and set the student number parameter
            PreparedStatement statement = connectDB.prepareStatement(sqlQuery);
            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();
            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the current row
                sn = resultSet.getString(1);
                System.out.println(sn);
            }
            //Set the correct text labels
            String rowExsistsQuery = "SELECT COUNT(1) FROM savedStudentForm WHERE username = '" + userN + "'";
            PreparedStatement statement1 = connectDB.prepareStatement(rowExsistsQuery);
            ResultSet resultSet1 = statement1.executeQuery();
            while (resultSet1.next()) {
                if (resultSet1.getInt(1) == 1) {
                    score += 0.166;
                    text1.setVisible(true);
                }
            }
            String rowExsistsQuery1 = "SELECT COUNT(1) FROM studentForm WHERE username = '" + userN + "'";
            PreparedStatement statement2 = connectDB.prepareStatement(rowExsistsQuery1);
            ResultSet resultSet2 = statement2.executeQuery();
            while (resultSet2.next()) {
                if (resultSet2.getInt(1) == 1) {
                    score += 0.166;
                    text2.setVisible(true);
                }
            }
            String rowExsistsQuery2 = "SELECT COUNT(1) FROM savedProviderForm WHERE studentNumber = '" + sn + "'";
            PreparedStatement statement3 = connectDB.prepareStatement(rowExsistsQuery2);
            ResultSet resultSet3 = statement3.executeQuery();
            while (resultSet3.next()) {
                if (resultSet3.getInt(1) == 1) {
                    score += 0.166;
                    text3.setVisible(true);
                }
            }
            String rowExsistsQuery3 = "SELECT COUNT(1) FROM providerForm WHERE studentNumber = '" + sn + "'";
            PreparedStatement statement4 = connectDB.prepareStatement(rowExsistsQuery3);
            ResultSet resultSet4 = statement4.executeQuery();
            while (resultSet4.next()) {
                if (resultSet4.getInt(1) == 1) {
                    score += 0.166;
                    text4.setVisible(true);
                }
            }
            String rowExsistsQuery4 = "SELECT COUNT(1) FROM savedTutorForm WHERE studentNumber = '" + sn + "'";
            PreparedStatement statement5 = connectDB.prepareStatement(rowExsistsQuery4);
            ResultSet resultSet5 = statement5.executeQuery();
            while (resultSet5.next()) {
                if (resultSet5.getInt(1) == 1) {
                    score += 0.166;
                    text5.setVisible(true);
                }
            }
            String rowExsistsQuery5 = "SELECT COUNT(1) FROM tutorForm WHERE studentNumber = '" + sn + "'";
            PreparedStatement statement6 = connectDB.prepareStatement(rowExsistsQuery5);
            ResultSet resultSet6 = statement6.executeQuery();
            while (resultSet6.next()) {
                if (resultSet6.getInt(1) == 1) {
                    score += 0.166;
                    text6.setVisible(true);
                }
            }
            progessBar.setProgress(score);
            score = 0;
        } catch (Exception e) { //Any other errors
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateInformationButtonOnAction(ActionEvent event) {
        //Database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();


        //SQL query to update table
        String updateFields = "UPDATE aboutMe SET university='" + uniTextField.getText()
                + "', course='" + courseTextField.getText()
                + "', sNumber='" + snumberTextField.getText()
                + "' WHERE username = '" + userN + "'";

        try {
            //Execute statement and update user
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateFields);
            errorMsgLabel.setText("Successfully updated!");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void setAboutMe() throws SQLException {
        // Create a database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Define the SQL query
        String sqlQuery = "SELECT * FROM aboutMe WHERE username = ?";

        try {
            // Create a prepared statement with the SQL query and set the username parameter
            PreparedStatement statement = connectDB.prepareStatement(sqlQuery);
            statement.setString(1, userN);

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                username = resultSet.getString(1);
                uniName = resultSet.getString(2);
                course = resultSet.getString(3);
                sNumber = resultSet.getString(4);
            }

            usernameLabel.setText(username); //Setting the data
            if (Objects.equals(uniName, "null")) {
                uniTextField.setText("");
            }
            else {
                uniTextField.setText(uniName);
            }
            if (Objects.equals(course, "null")) {
                courseTextField.setText("");
            }
            else {
                courseTextField.setText(course);
            }
            if (Objects.equals(uniName, "null")) {
                snumberTextField.setText("");
            }
            else {
                snumberTextField.setText(sNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
