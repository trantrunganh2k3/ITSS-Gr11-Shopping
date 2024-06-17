package Controller.Group;

import Model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberListController implements Initializable {
    public Label nameLbl;
    public Label usernameLbl;
    public Label emailLbl;
    public Label groupIDLbl;
    
   private final User Member;

    public MemberListController(User Member) {
        this.Member = Member;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLbl.setText(Member.getName());
        usernameLbl.setText(Member.getUsername());
        emailLbl.setText(Member.getEmail());
        groupIDLbl.setText(String.valueOf(Member.getGroupID()));
    }
}
