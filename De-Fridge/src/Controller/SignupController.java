package Controller;

import Model.*;
import db.DBConnection;
import dbController.FridgeController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    public Label loginLbl;
    public Label errorLbl;
    public Button signupBtn;
    public TextField tfUsername;
    public ChoiceBox cboxGender;
    public TextField tfEmail;
    public PasswordField tfPassword;
    public PasswordField tfPassCf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLbl.setVisible(false);
        cboxGender.getItems().add("Male");
        cboxGender.getItems().add("Female");
        cboxGender.getItems().add("Sc");
        tfPassCf.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                try{
                    onSignUp();
                }catch (SQLException | ClassNotFoundException e){
                    throw new RuntimeException(e);
                }
            }
        });
        Platform.runLater(() -> tfUsername.requestFocus());
        addListener();
    }

    private void addListener () {
        signupBtn.setOnMouseClicked(mouseEvent -> {
            try {
                onSignUp();
            } catch (SQLException |ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        loginLbl.setOnMouseClicked(mouseEvent -> toLogin());
    }
    private void toLogin() {
        Model.getInstance().getViewFactory().getLoginSignupSelect().set("Login");
    }

    private void onSignUp() throws SQLException, ClassNotFoundException {
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String pass = tfPassword.getText();
        String passCf = tfPassCf.getText();
        String gender = cboxGender.getValue().toString();

        if(username.isEmpty() || pass.isEmpty() || passCf.isEmpty() || email.isEmpty() || gender.isEmpty()){
            errorLbl.setText("Error: Missing text field(s).");
            errorLbl.setVisible(true);
        }else if(!pass.equals(passCf)){
            errorLbl.setText("Error: Password Confirm is not correct.");
            errorLbl.setVisible(true);
        }else {
            boolean userHad = dbController.UserController.searchUsernameForSignup(username);
            if(userHad){
                errorLbl.setText("Error: Username had already exited.");
                errorLbl.setVisible(true);
            }else {
                User user = new User(username, pass, gender, email );
                boolean add = dbController.UserController.addUser(user);
                FridgeController.addFridge(user);
                if(!add){
                    errorLbl.setText("Error: Cannot add user account!");
                    errorLbl.setVisible(true);
                }else{
                    toLogin();
                }
            }
        }
    }

}
