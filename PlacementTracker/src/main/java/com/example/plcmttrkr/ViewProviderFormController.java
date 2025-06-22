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

public class ViewProviderFormController {
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane dragBar;
    @FXML
    private Button cancelButton;
    @FXML
    private Button minimiseButton;
    @FXML
    private Label v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28, v29, v30, v31, v32, v33, v34, v35, v36, v37, v38, v39, v40, v41, v42, v43, v44, v45, v46, v47, v48, v49, v50, v51, v52, v53, v54, v55, v56, v57, v58, v59, v60, v61, v62, v63, v64;
    private String column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17, column18, column19, column20, column21, column22, column23, column24, column25, column26, column27, column28, column29, column30, column31, column32, column33, column34, column35, column36, column37, column38, column39, column40, column41, column42, column43, column44, column45, column46, column47, column48, column49, column50 ,column51, column52, column53, column54, column55, column56, column57, column58, column59, column60, column61, column62, column63, column64;


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

    public void viewProviderFormSet(String p1) throws SQLException {
        // Create a database connection
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Define the SQL query
        String sqlQuery = "SELECT * FROM providerForm WHERE studentNumber = ?";

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
                column53 = resultSet.getString(55);
                column54 = resultSet.getString(56);
                column55 = resultSet.getString(57);
                column56 = resultSet.getString(58);
                column57 = resultSet.getString(59);
                column58 = resultSet.getString(60);
                column59 = resultSet.getString(61);
                column60 = resultSet.getString(62);
                column61 = resultSet.getString(63);
                column62 = resultSet.getString(64);
                column63 = resultSet.getString(65);
                column64 = resultSet.getString(66);

                v1.setText("Name of Organisation: " + column1);
                v2.setText("Address where the placement will be based: " + column2);
                v3.setText("Postcode: " + column3);
                v4.setText("Web Address: " + column4);
                v5.setText("Activity in harmful areas: " + column5);
                v6.setText("More information: " + column6);

                v7.setText("Name of University of Leicester student: " + column7);

                v8.setText("Name of main contact: " + column8);
                v9.setText("Contact Job Title: " + column9);
                v10.setText("Contact Email: " + column10);
                v11.setText("Contact Telephone Number: " + column11);

                v12.setText("Job Title: " + column12);
                v13.setText("Role Start Date: " + column13);
                v14.setText("Role End Date: " + column14);
                v15.setText("Working hours per week: " + column15);
                v16.setText("Probation period: " + column16);
                v17.setText("Probation period information: " + column17);
                v18.setText("Student expectations: " + column18);

                v19.setText("Student exposed to hazards: " + column19);
                v20.setText("More Information on hazards: " + column20);
                v21.setText("Requires training for placement: " + column21);
                v22.setText("More Information on training: " + column22);
                v23.setText("Working from home: " + column23);
                v24.setText("How frequently: " + column24);
                v25.setText("Students support: " + column25);

                v26.setText("Different sites: " + column26);
                v27.setText("More Information on sites: " + column27);
                v28.setText("Outside UK travel: " + column28);

                v29.setText("Main location present risks: " + column29);
                v30.setText("More Information on risks and prevention: " + column30);

                v31.setText("Precautionary measures required: " + column31);
                v32.setText("More details on measures: " + column32);

                v33.setText("Support students with personal factors: " + column33);

                v34.setText("Public Liability Insurance or equivalent: " + column34);
                v35.setText("Name of provider: " + column35);
                v36.setText("Expiry date: " + column36);
                v37.setText("What will happen if a member of the public makes a claim: " + column37);
                v38.setText("Employer’s Liability Insurance or equivalent: " + column38);
                v39.setText("Name of provider: " + column39);
                v40.setText("Expiry date: " + column40);
                v41.setText("What will happen if the placement student becomes ill or injured: " + column41);
                v42.setText("Professional Indemnity Insurance or equivalent: " + column42);
                v43.setText("Name of provider: " + column43);
                v44.setText("Expiry date: " + column44);

                v45.setText("Would your organisation’s insurances cover liability if your business is held responsible for injury or damage to a client, contractor, or member of the public: " + column45);
                v46.setText("Name of provider: " + column46);
                v47.setText("Expiry date: " + column47);
                v48.setText("Would your organisation’s insurances cover liability arising from injury sustained by a student as a result of their duties in the role: " + column48);
                v49.setText("Name of provider: " + column49);
                v50.setText("Expiry date:: " + column50);
                v51.setText("Would your organisation’s insurances cover any legal/compensation costs arising from the placement student’s advice or professional services: " + column51);
                v52.setText("Name of provider: " + column52);
                v53.setText("Expiry date: " + column53);

                v54.setText("Procedure for recording and reporting accidents/incidents: " + column54);
                v55.setText("Does the organisation have a written health and safety policy: " + column55);
                v56.setText("Does the organisation provide health and safety training for new employees: " + column56);

                v57.setText("Will staff from the University be able to undertake site visits during the placement as required in consultation with appropriate staff at your organisation: " + column47);
                v58.setText("If no, reasoning: " + column54);
                v59.setText("Are there any issues relating to confidentiality or disclosure which the University will need to take into account in its procedures for assessing work undertaken by students on placement in your organisation: " + column55);
                v60.setText("If yes, details: " + column60);

                v61.setText("Name: " + column61);
                v62.setText("Job Title: " + column62);
                v63.setText("Signature: " + column63);
                v64.setText("Date: " + column64);

            }
        } catch (SQLException e) {
            // Handle any errors that occur
            e.printStackTrace();
        }
    }
}
