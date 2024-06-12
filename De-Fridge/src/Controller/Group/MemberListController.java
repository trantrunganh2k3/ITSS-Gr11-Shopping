package Controller.Group;

import Model.User;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberListController implements Initializable {
    public Label nameLbl;
    public Label usernameLbl;
    public Label emailLbl;
    public Label groupIDLbl;
    
   /* member1.setName("Duy");
    member1.setUsername("duyvu");
    member1.setEmail("Duy@gmail.com");
    member1.setGroupID(2003);*/

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
