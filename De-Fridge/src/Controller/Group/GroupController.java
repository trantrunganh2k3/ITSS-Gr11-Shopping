package Controller.Group;

import Model.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {
    public BorderPane groupView;
    public Button groupMember;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
        groupMember.setOnAction(event -> onMember());
    }
    private void onMember () {
        groupView.setCenter(Model.getInstance().getViewFactory().getGroupMemberView());
    }
}