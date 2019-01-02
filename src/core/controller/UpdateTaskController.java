package core.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class UpdateTaskController {

    @FXML
    public JFXButton updateTaskButton;
    @FXML
    private JFXTextField updateTaskField;
    @FXML
    private JFXTextField updateDescriptionField;

    @FXML
    private JFXButton updateTaskCancelButton;

    @FXML
    void initialize() {

        // Add Cancel Button
        updateTaskCancelButton.setOnAction(event ->
                updateTaskCancelButton.getScene().getWindow().hide());

    }

    public void setTaskField(String task) {
        this.updateTaskField.setText(task);
    }

    public String getTask() {
        return this.updateTaskField.getText().trim();
    }

    public void setUpdateDescriptionField(String description) {
        this.updateDescriptionField.setText(description);
    }

    public String getDescription() {
        return this.updateDescriptionField.getText().trim();
    }
}
