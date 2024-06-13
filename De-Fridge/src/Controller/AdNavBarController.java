package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdNavBarController implements Initializable {

    public AnchorPane navUser;
    public AnchorPane navCate;
    public AnchorPane navAcc;
    public AnchorPane navOut;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener(){
        navUser.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 15px 15px 0 0;");
        navCate.setStyle("-fx-background-color: transparent;");
        navAcc.setStyle("-fx-background-color: transparent;");
        navOut.setStyle("-fx-background-color: transparent;");

        navUser.setOnMouseClicked(mouseEvent -> onUser());
    }

    private void onUser() {
        navUser.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 15px 15px 0 0;");
        navCate.setStyle("-fx-background-color: transparent;");
        navAcc.setStyle("-fx-background-color: transparent;");
        navOut.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("UserList");
    }
}
