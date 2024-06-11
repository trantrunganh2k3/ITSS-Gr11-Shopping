package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    public Label loginLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener () {
        loginLbl.setOnMouseClicked(mouseEvent -> toLogin());
    }
    private void toLogin() {
        Model.getInstance().getViewFactory().getLoginSignupSelect().set("Login");
    }
}
