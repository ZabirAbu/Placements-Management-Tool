package com.example.plcmttrkr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewTutorFormController {
    private String column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17, column18, column19, column20, column21, column22, column23, column24, column25, column26, column27, column28, column29, column30, column31, column32, column33, column34;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane dragBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button minimiseButton;
    @FXML
    private Label v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28, v29, v30, v31, v32, v33, v34;

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

    public void viewStudentFormSet(String p1) throws SQLException {
        // Create a database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Define the SQL query
        String sqlQuery = "SELECT * FROM tutorForm WHERE studentNumber = ?";

        try {
            // Create a prepared statement with the SQL query and set the student number parameter
            PreparedStatement statement = connectDB.prepareStatement(sqlQuery);
            statement.setString(1, p1);

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


                v1.setText("Student Name: " + column1);

                v2.setText("Name of Organisation: " + column2);
                v3.setText("University’s reputation risk: " + column3);

                v4.setText("Same location and name: " + column4);

                v5.setText("Minimum requirements for duration and hours met: " + column5);
                v6.setText("Minimum academic requirements met: " + column6);
                v7.setText("Placement provider confirmed responsibilities: " + column7);
                v8.setText("Placement provider appropiate partner: " + column8);
                v9.setText("Student visa: " + column9);
                v10.setText("Student visa comply with placement duration: " + column10);

                v11.setText("Work condition: " + column11);
                v12.setText("Appropriate training: " + column12);
                v13.setText("Working from home likelihood: " + column13);
                v14.setText("Legitimacy of international remote work: " + column14);

                v15.setText("Significant travel issues: " + column15);
                v16.setText("Multiple site work: " + column16);

                v17.setText("Risks at main placement address: " + column17);
                v18.setText("Student having inappropiate accommodation arrangements: " + column18);

                v19.setText("Precautionary measures taken by student: " + column19);

                v20.setText("Personal factors affecting placement: " + column20);

                v21.setText("Public liability insurance: " + column21);
                v22.setText("Employer’s liability insurance: " + column22);
                v23.setText("Professional Indemnity Insurance (or equivalent): " + column23);
                v24.setText("Health and Safety Policy at the location(s): " + column24);

                v25.setText("Placement Provider have any objections: " + column25);
                v26.setText("Placement Provider have any issues relating to confidentiality or disclosure: " + column26);

                v27.setText("Decision: " + column27);
                v28.setText("Reasons for decision: " + column28);
                v29.setText("Name of Tutor: " + column29);
                v30.setText("Placement Tutor Signature: " + column30);
                v31.setText("Date: " + column31);
                v32.setText("Allocated Placement Supervisor: " + column32);

                v33.setText("College Director of Operations Signature: " + column33);
                v34.setText("Date: " + column34);

            }
        } catch (SQLException e) {
            // Handle any errors that occur
            e.printStackTrace();
        }
    }
}
