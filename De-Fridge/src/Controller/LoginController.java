package Controller;

import Model.*;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label signupLbl;
    public Button loginBtn;
    public TextField tfUsername;
    public PasswordField tfPassword;
    public Label errorLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLbl.setVisible(false);
        tfUsername.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                tfPassword.requestFocus();
            } else if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    onLogin();
                } catch (SQLException |ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        tfPassword.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP) {
                tfUsername.requestFocus();
            } else if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    onLogin();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Set focus on username label later (after UI is loaded)
        Platform.runLater(() -> tfUsername.requestFocus());
        addListener();
    }

    private void addListener () {
        signupLbl.setOnMouseClicked(mouseEvent -> toSignup());
        loginBtn.setOnAction(event -> {
            try {
                onLogin();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
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

    private void onLogin() throws SQLException, ClassNotFoundException {
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        if (username.isEmpty() || password.isEmpty()){
            errorLbl.setText("Error: Missing text field(s).");
            errorLbl.setVisible(true);
        }else{
            User user = dbController.UserController.findUserForLogin(username, password);
            if(user != null){
                if(username.equals("admin")){
                    toAdminHome();
                }else toHome();
            }else {
                errorLbl.setText("Login Failed!");
                errorLbl.setVisible(true);
            }
        }
    }
}
