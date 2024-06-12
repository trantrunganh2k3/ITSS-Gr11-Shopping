package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label signupLbl;
    public Button loginBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener () {
        signupLbl.setOnMouseClicked(mouseEvent -> toSignup());
        loginBtn.setOnAction(event -> toAdminHome());
    }

    private void toSignup () {
        Model.getInstance().getViewFactory().getLoginSignupSelect().set("Signup");
    }
    private void toHome () {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showHome();
    }

    private void toAdminHome(){
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showAdHome();
    }
}
