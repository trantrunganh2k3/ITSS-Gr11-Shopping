package Controller.ShoppingList;

import Model.Model;
import Model.ShoppingList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MyListController implements Initializable {

    public Button addListBtn;
    public VBox listContainerVbox;
    public DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        datePicker.setValue(LocalDate.now());
        addListener();
    }

    public void displayList() throws IOException {
        listContainerVbox.getChildren().clear();
        for (ShoppingList shoppingList: Model.getInstance().getShoppingLists()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/shoppingList/list.fxml"));
            ListController controller = new ListController(shoppingList, this);
            loader.setController(controller);
            AnchorPane anchorPane = loader.load();
            listContainerVbox.getChildren().add(anchorPane);
        }

    }

    private void addListener () {
        addListBtn.setOnAction(event -> addList());
    }
    private void addList () {
        AddListController controller = new AddListController(this);
        Model.getInstance().getViewFactory().showAddList(controller);
    }
}
