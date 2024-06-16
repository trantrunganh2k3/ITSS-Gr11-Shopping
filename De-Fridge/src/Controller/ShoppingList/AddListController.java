package Controller.ShoppingList;

import Model.*;
import dbController.ShoppingListController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddListController implements Initializable {
    public TextField listNameTf;
    public Button cancelBtn;
    public Button saveBtn;

    private final MyListViewController controller;
    public AddListController(MyListViewController controller){
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
        cancelBtn.setOnAction(event -> onCancel());
        saveBtn.setOnAction(event -> {
            try {
                onSubmit();
            } catch (IOException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void onCancel () {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
    private void onSubmit () throws IOException, SQLException, ClassNotFoundException {
        if (listNameTf.getText() == null) {
            //TODO: add a error label
        } else {
            ShoppingList list = new ShoppingList();
            list.setListName(listNameTf.getText());
            list.setDate(Date.valueOf(controller.datePicker.getValue().toString()));
            list.setShoppingItems(new ArrayList<>());
            ShoppingListController.addShoppingList(list);
            //Model.getInstance().setShoppingLists(Date.valueOf(controller.datePicker.getValue()));
            controller.displayList();
            onCancel();
        }
    }
}
