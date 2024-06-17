package Controller.Group;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import Model.*;
import dbController.GroupControllerDB;
import Controller.Group.GroupController;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
import java.io.IOException;

public class NewGroupController implements Initializable {

	
    private final GroupController controller;
    public NewGroupController(GroupController controller) {
        this.controller = controller;
    }
    public VBox UserVbox;
    public TextField groupNameLbl;
    public TextField groupIDLbl;
    public Button addIngredientBtn;
    public ListView ingredientLV;
    public ChoiceBox sectionChoiceBox;
    public Button saveBtn;
    public Button cancelBtn;

    @Override    
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Member();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Model.getInstance().getUsers().addListener((ListChangeListener.Change<? extends User> c) -> {
                    try {
                        Member();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        saveBtn.setOnAction(event -> onSave());
        cancelBtn.setOnAction(event -> onCancel());
    }

    private void Member() throws IOException {
        UserVbox.getChildren().clear();
        for (User member : Model.getInstance().getUsers()) {
            if (member.getGroupID() == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/group/userList.fxml"));
                UserListController controller = new UserListController(member);
                loader.setController(controller);
                UserVbox.getChildren().add(loader.load());
            }
        }
    }
    
    public void createNewGroup() throws SQLException, ClassNotFoundException {

        String groupName = groupNameLbl.getText();
        int groupID = Integer.parseInt(groupIDLbl.getText());
        Model.getInstance().getUser().setGroupID(groupID);
        try {
        	LocalDate localDate = LocalDate.now();
        	Date createDate = Date.valueOf(localDate);
            Group newGroup = new Group(groupID, groupName, Model.getInstance().getUser().getUsername(), createDate);
            GroupControllerDB.addGroup(newGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void onSave() {
        try {
            createNewGroup();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } 
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
    private void onCancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
