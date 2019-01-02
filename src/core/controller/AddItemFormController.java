package core.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import core.db.Const;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import core.db.DatabaseHandler;
import core.model.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class AddItemFormController {

    private int userId;

    private DatabaseHandler databaseHandler;

    @FXML
    private JFXButton saveTaskBtn;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private Label successLabel;

    @FXML
    private Label failedLabel;

    @FXML
    private JFXButton todosBtn;

    @FXML
    private ImageView backBtn;

    @FXML
    private Label appLabel;

    @FXML
    void initialize() {

        // Set App Name from Const
        appLabel.setText(Const.APP_NAME);

        databaseHandler = new DatabaseHandler();
        Task task = new Task();

        saveTaskBtn.setOnAction(event -> {

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String newTaskText = taskField.getText().trim();
            String newDescriptionText = descriptionField.getText().trim();

            if (!newTaskText.equals("") || !newDescriptionText.equals("")) {

                System.out.println("User ID: " + AddItemController.userId);

                task.setUserId(AddItemController.userId);
                task.setDatecreated(timestamp);
                task.setDescription(newDescriptionText);
                task.setTask(newTaskText);

                databaseHandler.insertTask(task);

                successLabel.setVisible(true);
                failedLabel.setVisible(false);

                todosBtn.setVisible(true);

                int taskNumber = 0;
                try {
                    taskNumber = databaseHandler.getAllTasks(AddItemController.userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                todosBtn.setText("My tasks: " + "(" + taskNumber + ")");


                // clean fields after submitting a task
                taskField.setText("");
                descriptionField.setText("");

                todosBtn.setOnAction(event1 -> {

                    // Hide screen when click My tasks button
                    saveTaskBtn.getScene().getWindow().hide();

                    // send users to list screen
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

            } else {

                System.out.println("Something goes wrong. Nothing has been added!");
                successLabel.setVisible(false);
                failedLabel.setVisible(true);


            }


        });


        // Add back Button
        backBtn.setOnMouseClicked(event -> {
            backBtn.getScene().getWindow().hide();

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
            stage.show();

        });

    }

    // getting user ID

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("Add a task for user with ID: " + this.userId);
    }

}
