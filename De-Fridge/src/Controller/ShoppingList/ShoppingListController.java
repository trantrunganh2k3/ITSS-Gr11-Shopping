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
        myListBtn.setStyle("-fx-background-color: #38686A");
        sharedListBtn.setStyle("-fx-background-color: #578F91");
    }

    private void addListener () {
        myListBtn.setOnAction(event -> toMyList());
        sharedListBtn.setOnAction(event -> toSharedList());
    }
    private void toMyList () {
        myListBtn.setStyle("-fx-background-color: #38686A");
        sharedListBtn.setStyle("-fx-background-color: #578F91");
        ShoppingList.setCenter(Model.getInstance().getViewFactory().getMyListView());
    }

    private void toSharedList (){
        sharedListBtn.setStyle("-fx-background-color: #38686A");
        myListBtn.setStyle("-fx-background-color: #578F91");
        ShoppingList.setCenter(Model.getInstance().getViewFactory().getSharedListView());
    }

}
