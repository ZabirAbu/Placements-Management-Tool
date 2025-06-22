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

public class ViewStudentFormController {
    private String column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17, column18, column19, column20, column21, column22, column23, column24, column25, column26, column27, column28, column29, column30, column31, column32, column33, column34, column35, column36, column37, column38, column39, column40, column41, column42, column43, column44, column45, column46, column47, column48, column49, column50 ,column51, column52;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane dragBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button minimiseButton;
    @FXML
    private Label v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28, v29, v30, v31, v32, v33, v34, v35, v36, v37, v38, v39, v40, v41, v42, v43, v44, v45, v46, v47, v48, v49, v50, v51, v52, v53;

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
        String sqlQuery = "SELECT * FROM studentForm WHERE studentNumber = ?";

        try {
            // Create a prepared statement with the SQL query and set the student number parameter
            PreparedStatement statement = connectDB.prepareStatement(sqlQuery);
            statement.setString(1, p1);

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

                v1.setText("First Name: " + column1);
                v2.setText("Surname: " + column2);
                v3.setText("Student Number: " + column3);
                v4.setText("Email Address: " + column4);
                v5.setText("Programme of study: " + column5);
                v6.setText("School/Department: " + column6);
                v7.setText("Contact Telephone number: " + column7);
                v8.setText("International student with a Student Visa: " + column8);
                v9.setText("Student Visa duration accounts for placement: " + column9);

                v10.setText("Name of Organisation: " + column10);
                v11.setText("Address where the placement will be based: " + column11);
                v12.setText("Postcode: " + column12);
                v13.setText("Web Address: " + column13);
                v14.setText("Contact Name: " + column14);
                v15.setText("Contact Job Title: " + column15);
                v16.setText("Contact Email: " + column16);
                v17.setText("Contact Telephone Number: " + column17);

                v18.setText("Role Title: " + column18);
                v19.setText("Role Start date: " + column19);
                v20.setText("Role End date: " + column20);
                v21.setText("Working hours per week: " + column21);
                v22.setText("Role include a probation period: " + column22);
                v23.setText("Probation period length: " + column23);
                v24.setText("Salary per year: " + column24);
                v25.setText("Source role: " + column25);
                v27.setText("Informed placement provider this form is part of degree: " + column26);
                v28.setText("Role Description: " + column27);

                v29.setText("Home/Remote work: " + column28);
                v30.setText("Overview: " + column29);
                v31.setText("Why does the role involve working from home: " + column30);

                v32.setText("Travel to/from residence/placement: " + column31);
                v33.setText("Travel (other): " + column32);
                v34.setText("Different location for placement: " + column33);
                v35.setText("Different location details: " + column34);
                v36.setText("Overseas: " + column35);
                v37.setText("Read overseas travel guidance: " + column36);
                v38.setText("Overseas finance consideration: " + column37);

                v39.setText("Accommodation arrangements: " + column38);
                v40.setText("Accommodation arrangements (other): " + column39);
                v41.setText("Foreign, Commonwealth and Development Office checked for overseas: " + column40);
                v42.setText("Risks at overseas location: " + column41);
                v43.setText("Risks at overseas location (further information): " + column42);

                v44.setText("Precautionary measures: " + column43);
                v45.setText("Precautionary measures (information): " + column44);
                v46.setText("Downloaded SafeZone app:" + column45);
                v47.setText("Overseas global insurance health card: " + column46);

                v48.setText("Adjustments: " + column47);
                v49.setText("Overseas student travel application form: " + column48);
                v50.setText("Overseas risk escalation: " + column49);

                v51.setText("Name: " + column50);
                v52.setText("Signature: " + column51);
                v53.setText("Date: " + column52);

            }
        } catch (SQLException e) {
            // Handle any errors that occur
            e.printStackTrace();
        }
    }
}




