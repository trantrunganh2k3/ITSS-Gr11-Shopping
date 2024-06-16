package Controller.Group;

import Model.Model;
import Model.User;
import Controller.Group.NewGroupController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {
    public Button groupMemberBtn;
    public Button groupReportBtn;
    public Button groupNewBtn;
    public Button groupDeleteBtn;
    public BorderPane groupView;
    public NewGroupController newGroupController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
        groupReportBtn.setOnAction(event -> onGroupReport());
        groupMemberBtn.setOnAction(event -> onGroupMember());
        groupNewBtn.setOnAction(event -> onGroupNew());
        groupDeleteBtn.setOnAction(event -> onGroupDelete());
    }
    private void onGroupMember () {
        groupView.setCenter(Model.getInstance().getViewFactory().getGroupMemberView());
    }
    private void onGroupReport () {
        groupView.setCenter(Model.getInstance().getViewFactory().getGroupReportView());
    }
    private void onGroupNew () {
        newGroupController = new NewGroupController(this);
        Model.getInstance().getViewFactory().showNewGroupView(newGroupController);
    }
    private void onGroupDelete() {
        int myGroupID = Model.getInstance().getUser().getGroupID();
        updateUsersInGroup(myGroupID, 0);
    }

    private void updateUsersInGroup(int groupID, int newGroupID) {
    	for (User member : Model.getInstance().getUsers()) {
            if (member.getGroupID() == groupID) {
                member.setGroupID(newGroupID);
            }
        }
    }

    /*private void updateUserInDatabase(User user) {
        // Viết logic để cập nhật thông tin user vào cơ sở dữ liệu
        userService.updateUser(user);
    }

    private void deleteGroup(int groupID) {
        // Xóa nhóm khỏi cơ sở dữ liệu
        deleteGroupFromDatabase(groupID);
    }

    private void deleteGroupFromDatabase(int groupID) {
        // Viết logic để xóa nhóm khỏi cơ sở dữ liệu
        groupService.deleteGroup(groupID);
    }*/
}
