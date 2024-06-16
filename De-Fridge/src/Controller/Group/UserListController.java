package Controller.Group;

import Model.User;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBox;

public class UserListController implements Initializable {
    public Label nameLbl;
    public Label usernameLbl;
    public Label emailLbl;
    public Label groupIDLbl;
    public CheckBox selectUserCheckBox;
    
   private final User Member;

    public UserListController(User Member) {
        this.Member = Member;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLbl.setText(Member.getName());
        usernameLbl.setText(Member.getUsername());
        emailLbl.setText(Member.getEmail());
        groupIDLbl.setText(String.valueOf(Member.getGroupID()));
        selectUserCheckBox.setSelected(false);
    }
}
