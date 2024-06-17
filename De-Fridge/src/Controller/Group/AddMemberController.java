package Controller.Group;

import Model.Model;
import Model.User;
import dbController.GroupControllerDB;
import dbController.UserController;
import Controller.Group.GroupMemberController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class AddMemberController implements Initializable {
    public TextField searchField;
    public ChoiceBox sectionChoiceBox;
    public Button saveBtn;
    public Button cancelBtn;
    public VBox UserVbox;
   
    private GroupMemberController groupMemberController;

    public AddMemberController(GroupMemberController groupMemberController) {
        this.groupMemberController = groupMemberController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sectionChoiceBox.getItems().addAll("Member", "Leader");
       /* addListener();*/
        saveBtn.setOnAction(event -> onSave());
        cancelBtn.setOnAction(event -> onCancel());
        try {
            populateUserList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                populateUserList(newValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Model.getInstance().getUsers().addListener((ListChangeListener.Change<? extends User> c) -> {
            try {
                populateUserList(searchField.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void populateUserList() throws IOException {
        populateUserList("");
    }

    private void populateUserList(String searchText) throws IOException {
        UserVbox.getChildren().clear();
        Integer myGroupID = Model.getInstance().getUser().getGroupID();
        for (User member : Model.getInstance().getUsers()) {
            if (member.getGroupID() == 0 && (searchText.isEmpty() || member.getUsername().toLowerCase().contains(searchText.toLowerCase()))) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/group/userList.fxml"));
                UserListController controller = new UserListController(member);
                loader.setController(controller);
                UserVbox.getChildren().add(loader.load());
            }
        }
    }

    private void onSave() {
    	try {
    		groupMemberController.updateMemberList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    	Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }


    private void onCancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}

  