package Controller.ShoppingList;

import Model.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddListController implements Initializable {
    public TextField listNameTf;
    public Button cancelBtn;
    public Button saveBtn;

    private final MyListController controller;
    public AddListController(MyListController controller){
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void onCancel () {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
    private void onSubmit () throws IOException {
        if (listNameTf.getText() == null) {
            //TODO: add a error label
        } else {
            ShoppingList list = new ShoppingList();
            list.setListName(listNameTf.getText());
            list.setDate(controller.datePicker.getValue().toString());
            list.setShoppingItems(new ArrayList<>());
            Model.getInstance().getShoppingLists().addFirst(list);
            controller.displayList();
            onCancel();
        }
    }
}
