package Controller.Admin;

import Model.Model;
import Model.User;
import dbController.UserController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserDetailController implements Initializable {
    public TextField tfUsername;
    public TextField tfGender;
    public TextField tfName;
    public TextField tfEmail;
    public TextField tfAddress;
    public Button btnSave;
    public Button btnEdit;
    public Button btnCancel;
    public Button btnClose;

    public ChoiceBox cbStatus;

    private final User user;
    private final UserAccountController controller;

    public UserDetailController(User user, UserAccountController controller) {
        this.user = user;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void addListener () {
        btnSave.setOnAction(event -> {
            try {
                save();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        btnEdit.setOnAction(event -> startEdit());
        btnCancel.setOnAction(event -> init());
        btnClose.setOnAction(event -> {
            try {
                close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void init() {
        tfUsername.setText(user.getUsername());
        tfAddress.setText(user.getAddress());
        tfEmail.setText(user.getEmail());
        tfGender.setText(user.getGender());
        tfName.setText(user.getName());
        System.out.println(user.getStatus());
        cbStatus.getItems().clear();
        cbStatus.setValue(user.getStatus());



        tfUsername.setEditable(false);
        tfAddress.setEditable(false);
        tfEmail.setEditable(false);
        tfGender.setEditable(false);
        tfName.setEditable(false);

        btnClose.setVisible(true);
        btnCancel.setVisible(false);
        btnEdit.setVisible(true);
        btnSave.setVisible(false);
    }

    private void startEdit () {
        btnClose.setVisible(false);
        btnCancel.setVisible(true);
        btnEdit.setVisible(false);
        btnSave.setVisible(true);

        cbStatus.getItems().add("Active");
        cbStatus.getItems().add("Blocked");
    }

    private void save() throws SQLException, ClassNotFoundException {
        this.user.setStatus(cbStatus.getValue().toString());
        UserController.setUserStatus(user);
        init();
    }

    private void close() throws IOException {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        if (controller.searchTf.getText().isEmpty()) {
            controller.displayAcc();
        } else {
            controller.searchAcc();
        }
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
