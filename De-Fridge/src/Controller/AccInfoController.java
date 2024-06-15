package Controller;

import Model.Model;
import Model.User;
import com.sun.security.auth.NTNumericCredential;
import dbController.UserController;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccInfoController implements Initializable {
    public PasswordField tfOldPassword;
    public PasswordField tfNewPassword;
    public PasswordField tfConfirmNewPassword;
    public Label lblPassError;
    public Button btnChangePassword;
    public TextField tfUsername;
    public TextField tfName;
    public TextField tfEmail;
    public TextField tfAddress;
    public Button btnSave;
    public Button btnEdit;
    public Button btnCancel;

    private final User user = Model.getInstance().getUser();
    public ChoiceBox cbGender;
    private UserController userController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initInfo();
        addListener();
    }

    private void addListener () {
        btnEdit.setOnAction(event -> editInfo());
        btnCancel.setOnAction(event -> initInfo());
        btnSave.setOnAction(event -> {
            try {
                save();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        btnChangePassword.setOnAction(event -> {
            try {
                changePassword();
            } catch (SQLException e) {


            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initInfo () {
        tfUsername.setText(user.getUsername());
        tfName.setText(user.getName());
        tfAddress.setText(user.getAddress());
        tfEmail.setText(user.getEmail());
        cbGender.getItems().clear();
        cbGender.setValue(user.getGender());

        tfUsername.setEditable(false);
        tfName.setEditable(false);
        tfAddress.setEditable(false);
        tfEmail.setEditable(false);

        btnCancel.setVisible(false);
        btnEdit.setVisible(true);
        btnSave.setVisible(false);
    }

    private void editInfo() {
        btnCancel.setVisible(true);
        btnEdit.setVisible(false);
        btnSave.setVisible(true);

        tfName.setEditable(true);
        tfAddress.setEditable(true);
        tfEmail.setEditable(true);
        cbGender.getItems().add("Male");
        cbGender.getItems().add("Female");
        cbGender.getItems().add("Secret");
    }

    private void save() throws SQLException, ClassNotFoundException {
        user.setName(tfName.getText());
        user.setEmail(tfEmail.getText());
        user.setAddress(tfAddress.getText());
        user.setGender(cbGender.getValue().toString());
        userController.updateUserInfo(this.user);
        initInfo();
    }

    private void changePassword() throws SQLException, ClassNotFoundException {
        String oldPassword = tfOldPassword.getText();
        String newPassword = tfNewPassword.getText();
        String newPasswordConfirm = tfConfirmNewPassword.getText();
        if (oldPassword.isEmpty() || newPassword.isEmpty() || newPasswordConfirm.isEmpty() ) {
            lblPassError.setText("Missing input fields!");
        } else if (!Model.getInstance().getUser().getPassword().equals(oldPassword) ) {
            lblPassError.setText("Password incorrect!");
        } else if (!newPasswordConfirm.equals(newPassword)) {
            lblPassError.setText("Password confirmation incorrect!");
        } else  {
            Model.getInstance().getUser().setPassword(newPassword);
            userController.updateUserInfo(this.user);
            lblPassError.setText("Password changed.");
            tfOldPassword.clear();
            tfNewPassword.clear();
            tfConfirmNewPassword.clear();
        }
    }

}
