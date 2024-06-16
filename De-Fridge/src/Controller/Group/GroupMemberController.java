package Controller.Group;

import Model.Model;
import Model.User;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupMemberController implements Initializable {
    public Button addMemberBtn;
    public VBox MemberVbox;
    public VBox UserVbox;
    public AddMemberController addMemberController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
        try {
            updateMemberList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Model.getInstance().getUsers().addListener((ListChangeListener.Change<? extends User> c) -> {
            try {
                updateMemberList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void addListener() {
        addMemberBtn.setOnAction(event -> onAddMember());
    }

    private void onAddMember() {
        addMemberController = new AddMemberController(this);
        Model.getInstance().getViewFactory().showAddMemberView(addMemberController);
    }

    public void updateMemberList() throws IOException {
        MemberVbox.getChildren().clear();
        int myGroupID = Model.getInstance().getUser().getGroupID();
        for (User member : Model.getInstance().getUsers()) {
            if (member.getGroupID() == myGroupID) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/group/memberList.fxml"));
                MemberListController controller = new MemberListController(member);
                loader.setController(controller);
                MemberVbox.getChildren().add(loader.load());
            }
        }

        if (Model.getInstance().getUser().getGroupID() == myGroupID) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/group/memberList.fxml"));
            MemberListController controller = new MemberListController(Model.getInstance().getUser());
            loader.setController(controller);
            MemberVbox.getChildren().add(loader.load());
        }
    }
    
    public void addNewMember(User newMember) {
        int myGroupID = Model.getInstance().getUser().getGroupID();
        newMember.setGroupID(myGroupID);
        Model.getInstance().getUsers().add(newMember);
        try {
            updateMemberList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
