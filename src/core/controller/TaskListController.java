package core.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import core.db.Const;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class TaskListController {

    @FXML
    private ImageView listRefreshBtn;

    @FXML
    private JFXListView<Task> listTask;

    @FXML
    private JFXTextField listTaskField;

    @FXML
    private JFXTextField listDescriptionField;

    @FXML
    private JFXButton listSaveTaskBtn;

    @FXML
    private ImageView backBtn;

    @FXML
    private Label appLabel;

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTasks;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {

        // Set App Name from Const
        appLabel.setText(Const.APP_NAME);

        // print user ID to check that it is passed to new screen [test]
//        System.out.println("User ID From TaskCellController: " + AddItemController.userId);

        tasks = FXCollections.observableArrayList();

        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            tasks.addAll(task);


            // read user tasks [test]
//            System.out.println("User tasks: " + resultSet.getString("task"));
        }


        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new TaskCellController());

        listRefreshBtn.setOnMouseClicked(event -> {
            try {
                refreshList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        listSaveTaskBtn.setOnAction(event -> {
            addNewTask();
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

    // refresh tasks list
    public void refreshList() throws SQLException {

        System.out.println("Refresh List in TaskListController called");

        refreshedTasks = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AddItemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));

            refreshedTasks.addAll(task);
        }

        listTask.setItems(refreshedTasks);
        listTask.setCellFactory(CellController -> new TaskCellController());


    }

    // make add task works on the list view
    public void addNewTask() {

        if (!listTaskField.getText().equals("")
                || !listDescriptionField.getText().equals("")) {
            Task myNewTask = new Task();

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            myNewTask.setUserId(AddItemController.userId);
            myNewTask.setTask(listTaskField.getText().trim());
            myNewTask.setDescription(listDescriptionField.getText().trim());
            myNewTask.setDatecreated(timestamp);

            databaseHandler.insertTask(myNewTask);

            listTaskField.setText("");
            listDescriptionField.setText("");

            // update tasks list thro reinitialise the process
            try {
                initialize();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }


}

