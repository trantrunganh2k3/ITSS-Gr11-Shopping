package Controller.ShoppingList;

import Model.Model;
import Model.ShoppingList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SharedListViewController implements Initializable {
    public VBox listContainerVbox;
    public DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datePicker.setValue(LocalDate.now());
        try {
            displayList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayList () throws IOException {
        listContainerVbox.getChildren().clear();
        for (ShoppingList shoppingList: Model.getInstance().getSharedShoppingLists()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/shoppingList/list.fxml"));
            SharedListController controller = new SharedListController(shoppingList, this);
            loader.setController(controller);
            AnchorPane anchorPane = loader.load();
            listContainerVbox.getChildren().add(anchorPane);
        }

    }
}
