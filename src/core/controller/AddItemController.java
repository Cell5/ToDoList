package core.controller;

import com.jfoenix.controls.JFXButton;
import core.db.Const;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import core.animation.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {

    public static int userId;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addBtn;

    @FXML
    private Label noTaskLabel;

    @FXML
    private ImageView backBtn;

    @FXML
    private Label appLabel;

    @FXML
    private JFXButton todosBtn;


    @FXML
    void initialize() {

        // Set App Name from Const
        appLabel.setText(Const.APP_NAME);

        // Make My Tasks list button works
        todosBtn.setOnAction(event -> {
            todosBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/core/view/taskList.fxml"));

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

        // Make Add button works
        addBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addBtn);
            buttonShaker.shake();

            FadeTransition addBtnTransition = new FadeTransition(Duration.millis(2000), addBtn);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), noTaskLabel);


            // for testing
            System.out.println("Add Button Clicked");

            addBtn.relocate(0, 0);
            noTaskLabel.relocate(0, 140);

            addBtn.setOpacity(0);
            noTaskLabel.setOpacity(0);

            addBtnTransition.setFromValue(1f);
            addBtnTransition.setToValue(0f);
            addBtnTransition.setCycleCount(3);
            addBtnTransition.setAutoReverse(false);
            addBtnTransition.play();

            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();

            try {

                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/core/view/addItemForm.fxml"));

                AddItemController.userId = getUserId();

                // deprecated info since using global variable

//                AddItemFormController addItemFormController = new AddItemFormController();
//                addItemFormController.setUserId(getUserId());

                FadeTransition rootTransition = new FadeTransition(Duration.millis(700), formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootAnchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }


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

    public void setUserId(int userId) {

        this.userId = userId;
        System.out.println("User ID is " + this.userId);

    }

    public int getUserId() {
        return this.userId;
    }

}
