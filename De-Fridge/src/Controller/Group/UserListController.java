package Controller.Group;

import Model.Model;
import Model.User;
import dbController.GroupControllerDB;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBox;


public class UserListController implements Initializable {
    public Label nameLbl;
    public Label usernameLbl;
    public Label emailLbl;
    public Label groupIDLbl;
    public CheckBox selectUserCheckBox;

    private final User member;

    public UserListController(User member) {
        this.member = member;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User currentUser = Model.getInstance().getUser();
        nameLbl.setText(member.getName());
        usernameLbl.setText(member.getUsername());
        emailLbl.setText(member.getEmail());
        groupIDLbl.setText(String.valueOf(member.getGroupID()));
        setupSelectUserCheckBox(currentUser);
    }

    private void setupSelectUserCheckBox(User currentUser) {
        selectUserCheckBox.setSelected(member.getGroupID() == currentUser.getGroupID());
        selectUserCheckBox.setOnAction(event -> onSelect());
    }

    private void onSelect() {
        try {
            save();
        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    private void save() throws SQLException, ClassNotFoundException {
        int myGroupID = Model.getInstance().getUser().getGroupID();
        if (selectUserCheckBox.isSelected()) {
            member.setGroupID(myGroupID);
            GroupControllerDB.addUserInGroup(member.getUsername(), myGroupID);
        }
    }
}
