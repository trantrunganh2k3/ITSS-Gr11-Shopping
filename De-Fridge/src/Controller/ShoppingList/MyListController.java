package Controller.ShoppingList;

import Model.Model;
import Model.ShoppingItems;
import Model.ShoppingList;
import dbController.ShoppingListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyListController implements Initializable {
    public Label listTitleLbl;
    public ListView itemsLV;
    public Button addItemsBtn;
    public Button cancelBtn;
    public Button deleteBtn;
    public Button saveBtn;
    private final ShoppingList list;

    public ObservableList<ShoppingItems> items;

    private final MyListViewController controller;
    public Button shareBtn;

    public MyListController(ShoppingList list, MyListViewController controller) {
        this.list = list;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void addListener () {
        addItemsBtn.setOnAction(event -> addItem());
        cancelBtn.setOnAction(event -> {
            reset();
            endEdit();
        });
        saveBtn.setOnAction(event -> {
            try {
                onSave();
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        deleteBtn.setOnAction(event -> {
            try {
                onDelete();
            } catch (IOException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        shareBtn.setOnAction(event -> {
            try {
                onShare();
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void addItem () {
        startEdit();
        ShoppingItems item = new ShoppingItems();
        items.add(item);
        deleteBtn.setVisible(false);
    }

    private void init() {
        reset();
        listTitleLbl.setText(list.getListName());
        cancelBtn.setVisible(false);
        saveBtn.setVisible(false);
        deleteBtn.setVisible(items.isEmpty());
        System.out.println(list.isShared());
        if (list.isShared()) {

            shareBtn.setVisible(false);
        } else {

            shareBtn.setVisible(true);
        }
    }
    private void onShare () throws SQLException, ClassNotFoundException, IOException {
        ShoppingListController.shareList(list);
        controller.displayList();
        shareBtn.setVisible(!list.isShared());
    }

    private void reset () {
        items = FXCollections.observableArrayList();
        for (ShoppingItems shoppingItems: list.getShoppingItems()) {
            ShoppingItems item = new ShoppingItems();
            item.setItemID(shoppingItems.getItemID());
            item.setItemName(shoppingItems.getItemName());
            item.setUnit(shoppingItems.getUnit());
            item.setQuantity(shoppingItems.getQuantity());
            item.setCategory(shoppingItems.getCategory());
            item.setBoughtBy(shoppingItems.getBoughtBy());
            items.add(item);
        }
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
                    MyListItemController controller = new MyListItemController(item, MyListController.this);
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

    public void startEdit () {
        cancelBtn.setVisible(true);
        saveBtn.setVisible(true);
        deleteBtn.setVisible(false);
    }

    public void endEdit() {
        cancelBtn.setVisible(false);
        saveBtn.setVisible(false);
        deleteBtn.setVisible(true);
        deleteBtn.setVisible(items.isEmpty());
    }

    private void onDelete () throws IOException, SQLException, ClassNotFoundException {
        Model.getInstance().getShoppingLists().remove(this.list);
        ShoppingListController.deleteShoppingList(list);
        controller.displayList();
    }

    public void onSave () throws SQLException, ClassNotFoundException, IOException {
        for (ShoppingItems item: items) {
            if (item.getItemName() ==null || item.getCategory() == null || item.getQuantity() == 0
            || item.getUnit() == null
            ) return;
        }
        List<ShoppingItems> newList = new ArrayList<>();
        for (ShoppingItems shoppingItems: items) {
            ShoppingItems item = new ShoppingItems();
            item.setItemID(shoppingItems.getItemID());
            item.setItemName(shoppingItems.getItemName());
            item.setUnit(shoppingItems.getUnit());
            item.setQuantity(shoppingItems.getQuantity());
            item.setCategory(shoppingItems.getCategory());
            item.setBoughtBy(shoppingItems.getBoughtBy());
            item.setListID(shoppingItems.getListID());
            newList.add(item);
        }
        list.setShoppingItems(newList);
        ShoppingListController.updateShoppingList(list);
        controller.displayList();
        endEdit();
        init();
    }

}
