package com.example.plcmttrkr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private String[] rolesList = {"Student", "Placement Team", "Placement Tutor"};
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Button cancelButton;
    @FXML
    private Button minimiseButton;
    @FXML
    private AnchorPane dragBar;
    @FXML
    private TextField forenameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField createUsernameTextField;
    @FXML
    private PasswordField createPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private Label msg;
    @FXML
    private Button signInButton;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleChoiceBox.getItems().addAll(rolesList);
    }

    public void signUpButtonOnAction(ActionEvent event) {
        if (forenameTextField.getText().isBlank() || surnameTextField.getText().isBlank() || createUsernameTextField.getText().isBlank() || createPasswordField.getText().isBlank() || confirmPasswordField.getText().isBlank() || roleChoiceBox.getValue() == null) {
            msg.setText("Please fill in all fields!");
        } else {
            if (createPasswordField.getText().equals(confirmPasswordField.getText())) {
                signUpUser();
            } else {
                msg.setText("Password fields do not match!!");
            }
        }
    }

    public void signInButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = fxmlLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = (Stage) signInButton.getScene().getWindow();
            loginStage.setScene(loginScene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signUpUser() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String forename = forenameTextField.getText();
        String surname = surnameTextField.getText();
        String username = createUsernameTextField.getText();
        String password = createPasswordField.getText();
        String role = roleChoiceBox.getValue().toString();

        String insertFields = "INSERT INTO accounts(username, firstname, lastname, userPassword, userRole) VALUES ('";
        String insertValues = username + "','" + forename + "','" + surname + "','" + password + "','" + role + "')";
        String insertToRegister = insertFields + insertValues;

        String insertAboutMe = "INSERT INTO aboutMe(username) VALUES ('";
        String insertValuesAboutMe = username + "')";
        String insertAboutMeValues = insertAboutMe + insertValuesAboutMe;

        try {
            Statement statement = connectDB.createStatement();

            String rowExsistsQuery = "SELECT count(1) FROM accounts WHERE username = '" + createUsernameTextField.getText() + "'";

            ResultSet queryResult = statement.executeQuery(rowExsistsQuery);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    msg.setText("Username already exists!");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            statement.executeUpdate(insertAboutMeValues);

            msg.setText("Successfully registered!");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}

