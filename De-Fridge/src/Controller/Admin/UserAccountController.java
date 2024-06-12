package Controller.Admin;

import Model.Model;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {
    public VBox userAccVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            test();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void test() throws IOException{
        for (User user: Model.getInstance().getUsers()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/admin/userItem.fxml"));
            UserItemController controller = new UserItemController(user);
            loader.setController(controller);
            userAccVbox.getChildren().add(loader.load());
        }

    }
}
