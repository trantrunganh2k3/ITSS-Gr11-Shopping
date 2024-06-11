package Controller;

import Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginSignupController implements Initializable {
    @FXML
    private BorderPane loginSignupView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getLoginSignupSelect().addListener((observableValue, oldVal, newVal) -> {
            if (newVal.equals("Signup")) {
                loginSignupView.setCenter(Model.getInstance().getViewFactory().getSignupView());
            } else {
                loginSignupView.setCenter(Model.getInstance().getViewFactory().getLoginView());
            }
        });
    }
}
