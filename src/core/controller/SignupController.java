package core.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.db.Const;
import core.db.DatabaseHandler;
import core.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {


    @FXML
    private JFXButton signUpBtn;

    @FXML
    private ImageView backBtn;

    @FXML
    private JFXTextField signUpFirstName;

    @FXML
    private JFXTextField signUpLastName;

    @FXML
    private JFXCheckBox signUpCheckBoxMale;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private JFXCheckBox signUpCheckBoxFemale;

    @FXML
    private Label appLabel;

    @FXML
    void initialize() {

        // Set App Name from Const
        appLabel.setText(Const.APP_NAME);

        // Make Sign Up Button Works
        signUpBtn.setOnAction(event -> {
            createUser();

            signUpBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/core/view/login.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        });

        // Add back Button
        backBtn.setOnMouseClicked(event -> {
            backBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/core/view/login.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });

    }

    private void createUser() {

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();

        String gender;
        if (signUpCheckBoxFemale.isSelected()) {
            gender = "Female";
        } else gender = "Male";

        User user = new User(name, lastName, userName, password, location, gender);

        databaseHandler.signUpUser(user);

    }

}