package Controller.Group;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {
    public Button groupMemberBtn;
    public Button groupReportBtn;
    public BorderPane groupView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
        groupReportBtn.setOnAction(event -> onGroupReport());
        groupMemberBtn.setOnAction(event -> onGroupMember());
    }
    private void onGroupMember () {
        groupView.setCenter(Model.getInstance().getViewFactory().getGroupMemberView());
    }
    private void onGroupReport () {
        groupView.setCenter(Model.getInstance().getViewFactory().getgroupReportView());
    }
}
