package Controller.Group;

import Model.Model;
import Model.User;
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
    public AddMemberController addMemberController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
        try {
            test();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void addListener () {
        addMemberBtn.setOnAction(event -> onAddMember());
    }

    private void onAddMember () {
        addMemberController = new AddMemberController(this);
        Model.getInstance().getViewFactory().showAddMemberView(addMemberController);
    }

    private void test () throws IOException {

        for (User Member: Model.getInstance().getMember()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/group/memberList.fxml"));
            MemberListController controller = new MemberListController(Member);
            loader.setController(controller);
            MemberVbox.getChildren().add(loader.load());
        }
    }
}
