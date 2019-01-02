package core.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.db.Const;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import core.animation.Shaker;
import core.db.DatabaseHandler;
import core.model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    private int userId;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton loginSignupBtn;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private Label appLabel;

    @FXML
    private Label loginFailed;

    private DatabaseHandler databaseHandler;


    @FXML
    void initialize() {

        // Set App Name from Const
        appLabel.setText(Const.APP_NAME);

        databaseHandler = new DatabaseHandler();

        loginBtn.setOnAction(event -> {

            String loginText = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();

            User user = new User();
            user.setUserName(loginText);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);

            int counter = 0;

            try {
                while (userRow.next()) {
                    counter++;
                    String name = userRow.getString("firstname");
                    String lastname = userRow.getString("lastname");
                    userId = userRow.getInt("userid");

                    System.out.println("Welcome, " + name + " " + lastname + "!");

                }
                if (counter == 1) {

                   showAddItemScreen();

                } else {
                    Shaker userNameShaker = new Shaker(loginUsername);
                    Shaker passwordShaker = new Shaker(loginPassword);
                    Shaker loginBtnShaker = new Shaker(loginBtn);
                    loginBtnShaker.shake();
                    passwordShaker.shake();
                    userNameShaker.shake();

                    System.out.println("Login failed! Please check the credentials... ");
                    loginFailed.setVisible(true);


                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        loginSignupBtn.setOnAction(event -> {

            // Take users to Sign Up screen
            loginSignupBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/core/view/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

    private void showAddItemScreen() {
        // Take users to Add Item screen
        loginSignupBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/core/view/addItem.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        AddItemController addItemController = loader.getController();
        addItemController.setUserId(userId);

        stage.showAndWait();
    }

}
