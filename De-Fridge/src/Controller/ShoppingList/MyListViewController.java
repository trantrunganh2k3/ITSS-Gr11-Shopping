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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MyListViewController implements Initializable {

    public Button addListBtn;
    public VBox listContainerVbox;
    public DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addListener();
            datePicker.setValue(LocalDate.now());
            displayList();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayList() throws IOException, SQLException, ClassNotFoundException {
        Model.getInstance().setShoppingLists(Date.valueOf(datePicker.getValue()));
        listContainerVbox.getChildren().clear();
        for (ShoppingList shoppingList: Model.getInstance().getShoppingLists()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/shoppingList/list.fxml"));
            MyListController controller = new MyListController(shoppingList, this);
            loader.setController(controller);
            AnchorPane anchorPane = loader.load();
            listContainerVbox.getChildren().add(anchorPane);
        }

    }

    private void addListener () {
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                displayList();
                System.out.println(newValue.toString());
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }

        });
        addListBtn.setOnAction(event -> addList());
    }
    private void addList () {
        AddListController controller = new AddListController(this);
        Model.getInstance().getViewFactory().showAddList(controller);
    }
}
