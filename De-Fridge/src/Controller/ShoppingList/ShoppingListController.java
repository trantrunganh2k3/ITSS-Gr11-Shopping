package Controller.ShoppingList;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingListController implements Initializable {
    public Button myListBtn;
    public Button sharedListBtn;
    public BorderPane ShoppingList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener () {
        myListBtn.setOnAction(event -> toMyList());
        sharedListBtn.setOnAction(event -> toSharedList());
    }
    private void toMyList () {
        ShoppingList.setCenter(Model.getInstance().getViewFactory().getMyListView());
    }

    private void toSharedList (){
        ShoppingList.setCenter(Model.getInstance().getViewFactory().getSharedListView());
    }

}
