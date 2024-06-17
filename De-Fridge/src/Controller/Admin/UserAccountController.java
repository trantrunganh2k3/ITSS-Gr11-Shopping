package Controller.Admin;

import Model.Model;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserAccountController implements Initializable {
    public VBox userAccVbox;
    public Button searchBtn;
    public TextField searchTf;
    public Button unSearchBtn;
    public Label errorLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            displayAcc();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        addListener();
    }

    public void displayAcc() throws IOException{
        unSearchBtn.setVisible(false);
        userAccVbox.getChildren().clear();
        for (User user: Model.getInstance().getUsers()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/admin/userItem.fxml"));
            UserItemController controller = new UserItemController(user, this);
            loader.setController(controller);
            userAccVbox.getChildren().add(loader.load());
        }
    }

    private void addListener () {
        searchBtn.setOnAction(event -> {
            if (searchTf.getText().isEmpty()) {

            } else {
                try {
                    searchAcc();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        unSearchBtn.setOnAction(event -> {
            searchTf.clear();
            try {
                displayAcc();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void searchAcc() throws IOException {

        userAccVbox.getChildren().clear();
        unSearchBtn.setVisible(true);
        Model.getInstance().searchUser(searchTf.getText());

        if (Model.getInstance().getSearchUsers().isEmpty()) {
            System.out.println("No result");
            errorLbl.setText("No results found for '" + searchTf.getText() +
                    "'. Please try a different keyword or refine your search.");
            userAccVbox.getChildren().add(errorLbl);

        } else {
            for (User user: Model.getInstance().getSearchUsers()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/admin/userItem.fxml"));
                UserItemController controller = new UserItemController(user, this);
                loader.setController(controller);
                userAccVbox.getChildren().add(loader.load());
            }
        }


    }
}
