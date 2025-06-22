package com.example.plcmttrkr;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController { //implements Initializable {
    public static String foreN;
    public static String surN;
    public static String rol;
    public static String userN;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane dragBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button minimiseButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label msg;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signUpButton;
    private boolean loggedIn = false;
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

    public void loginButtonOnAction(ActionEvent event) throws IOException, SQLException {
        msg.setText("Invalid username or password!");
        if (usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) { //Checking if fields are blank
            validateLogin(); //Proceed to database validation
            if(loggedIn == true){
                if (rol.equals("Student")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root = fxmlLoader.load();
                    DashboardController dashboardC = fxmlLoader.getController();
                    dashboardC.setName(foreN, surN);
                    dashboardC.setDashboard();
                    dashboardC.setAboutMe();
                    Scene dashboardScene = new Scene(root);
                    Stage dashboardStage = (Stage) signUpButton.getScene().getWindow();
                    dashboardStage.setScene(dashboardScene);
                    dashboardStage.centerOnScreen();
                    dashboardStage.show();
                }
                else if (rol.equals("Placement Tutor")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tutorDashboard.fxml"));
                    Parent root = fxmlLoader.load();
                    TutorDashboardController tutorDashboardC = fxmlLoader.getController();
                    tutorDashboardC.setName(foreN, surN);
                    tutorDashboardC.setPieChart();
                    Scene dashboardScene = new Scene(root);
                    Stage dashboardStage = (Stage) signUpButton.getScene().getWindow();
                    dashboardStage.setScene(dashboardScene);
                    dashboardStage.centerOnScreen();
                    dashboardStage.show();
                }
                else if (rol.equals("Placement Team")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("placement_teamDashboard.fxml"));
                    Parent root = fxmlLoader.load();
                    PlacementTeamDashboardController providerDashboardC = fxmlLoader.getController();
                    providerDashboardC.setName(foreN, surN);
                    providerDashboardC.setPieChart();
                    Scene dashboardScene = new Scene(root);
                    Stage dashboardStage = (Stage) signUpButton.getScene().getWindow();
                    dashboardStage.setScene(dashboardScene);
                    dashboardStage.centerOnScreen();
                    dashboardStage.show();
                }
            }
        } else {
            msg.setText("Please enter the username and password!"); //Error message if fields are blank
        }
    }

    public void signUpButtonOnAction(ActionEvent event) { //Loading sign up page on action
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_up.fxml"));
            Parent root = fxmlLoader.load();
            Scene signUpScene = new Scene(root);
            Stage signUpStage = (Stage) signUpButton.getScene().getWindow();
            signUpStage.setScene(signUpScene);
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateLogin() {
        DataBaseConnection connectNow = new DataBaseConnection(); //Create instance of a database connection
        Connection connectDB = connectNow.getConnection();

        String strSelect = "SELECT * FROM accounts WHERE BINARY username = '" + usernameTextField.getText() + "' AND BINARY userPassword ='" + passwordField.getText() + "'";
        String verifyLogin = "SELECT count(1) FROM accounts WHERE BINARY username = '" + usernameTextField.getText() + "' AND BINARY userPassword ='" + passwordField.getText() + "'"; //SQL query to check details in database
        String fname = "SELECT firstName FROM accounts WHERE BINARY username = '" + usernameTextField.getText() + "' AND BINARY userPassword ='" + passwordField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            PreparedStatement pstmt = connectDB.prepareStatement(fname);

            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    System.out.print(pstmt);
                    loggedIn = true;
                    PreparedStatement st = connectDB.prepareStatement(strSelect);
                    ResultSet rs = st.executeQuery();
                    while (rs.next()) {
                        userN = rs.getString(1);
                        foreN = rs.getString(2);
                        surN = rs.getString(3);
                        rol = rs.getString(5);
                    }
                } else {
                    msg.setText("Invalid login. Please try again!");
                }
            }
        } catch (Exception e) { //Any other errors
            e.printStackTrace();
            e.getCause();
        }
    }
}