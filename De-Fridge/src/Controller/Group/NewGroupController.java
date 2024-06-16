package Controller.Group;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import Model.*;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class NewGroupController implements Initializable {
    private final GroupController controller;
    public NewGroupController(GroupController controller) {
        this.controller = controller;
    }
    public VBox UserVbox;
    public TextField groupNameLbl;
    public TextField groupIDLbl;
    public Button addIngredientBtn;
    public ListView ingredientLV;
    public ChoiceBox sectionChoiceBox;
    public Button saveBtn;
    public Button cancelBtn;

    @Override    
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Member();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Model.getInstance().getUsers().addListener((ListChangeListener.Change<? extends User> c) -> {
                    try {
                        Member();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private void Member() throws IOException {
        UserVbox.getChildren().clear();
        for (User member : Model.getInstance().getUsers()) {
            if (member.getGroupID() == 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/group/userList.fxml"));
                UserListController controller = new UserListController(member);
                loader.setController(controller);
                UserVbox.getChildren().add(loader.load());
            }
        }
    }
}
