package Controller.Group;

import Model.Model;
import Controller.Group.NewGroupController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {
    public Button groupMemberBtn;
    public Button groupNewBtn;
    public Button groupDeleteBtn;
    public BorderPane groupView;
    public NewGroupController newGroupController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
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
    }
}
