package Controller.Admin;

import Model.Model;
import Model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UserItemController implements Initializable {


    private final User user;
    public Label usernameLbl;
    public Label emailLbl;
    public Label nameLbl;
    public Label statusLbl;
    public Button viewBtn;

    private UserAccountController controller;

    public UserItemController(User user, UserAccountController controller){
        this.controller = controller;
        this.user = user;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLbl.setText(user.getUsername());
        emailLbl.setText(user.getEmail());
        nameLbl.setText(user.getName());
        statusLbl.setText(user.getStatus());
        addListener();
    }

    private void addListener(){
        viewBtn.setOnAction(actionEvent -> onDetail());
    }

    private void onDetail(){
        UserDetailController controller = new UserDetailController(this.user, this.controller);
        Model.getInstance().getViewFactory().showUserAccDetail(controller);
    }

}
