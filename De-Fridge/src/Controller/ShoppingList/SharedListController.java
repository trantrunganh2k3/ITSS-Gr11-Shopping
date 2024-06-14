package Controller.ShoppingList;

import Model.ShoppingItems;
import Controller.ShoppingList.*;
import Model.ShoppingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SharedListController implements Initializable {
    public Label listTitleLbl;
    public ListView itemsLV;
    public Button addItemsBtn;
    public Button cancelBtn;
    public Button deleteBtn;
    public Button saveBtn;
    private final ShoppingList list;

    public ObservableList<ShoppingItems> items;

    private final SharedListViewController controller;

    public Button shareBtn;
    public Label shareStatusLbl;

    public SharedListController(ShoppingList list, SharedListViewController controller1) {
        this.list = list;
        controller = controller1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addItemsBtn.setVisible(false);
        listTitleLbl.setText(list.getListName());
        shareBtn.setVisible(false);
        shareStatusLbl.setVisible(false);
        deleteBtn.setVisible(false);
        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        items = FXCollections.observableArrayList(list.getShoppingItems());
        itemsLV.setItems(items);
        itemsLV.setCellFactory(lv -> new ListCell<ShoppingItems>() {
            @Override
            protected void updateItem(ShoppingItems item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/shoppingList/listItems.fxml"));
                    SharedListItemController controller = new SharedListItemController(item);
                    loader.setController(controller);
                    setText(null);
                    try {
                        setGraphic(loader.load());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
