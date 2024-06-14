package Controller.ShoppingList;

import Model.Ingredient;
import Model.Model;
import Model.ShoppingItems;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class SharedListItemController implements Initializable {
    public AnchorPane listItem;
    public TextField nameTf;
    public ChoiceBox categoryCB;
    public ChoiceBox unitCB;
    public DatePicker expireDatePicker;
    public Button editBtn;
    public Button checkBtn;
    public Button deleteBtn;

    public Line line;
    private ShoppingItems item;
    public TextField quantityTf;

    public SharedListItemController(ShoppingItems item) {
        this.item = item;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        if (item.getBoughtBy() == null) {
            nameTf.setEditable(false);
            quantityTf.setEditable(false);
            line.setVisible(false);
            addListener();

        } else {
            line.setVisible(true);
            listItem.setDisable(true);
        }
    }
    private void init() {
        editBtn.setVisible(false);
        deleteBtn.setVisible(false);
        if (item.getBoughtBy() == null) {
            nameTf.setText(item.getItemName());
            checkBtn.setVisible(false);
            categoryCB.setValue(item.getCategory());
            quantityTf.setText(String.valueOf(item.getQuantity()));
            unitCB.setValue(item.getUnit());
            addListener();
        } else {
            line.setVisible(true);
            listItem.setDisable(true);
        }

    }
    private void addListener() {
        expireDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                checkBtn.setVisible(true);
            }
        });
        checkBtn.setOnAction(event -> onCheck());
    }
    private void onCheck () {
        //TODO: Add to fridge in Database
        Ingredient newIngredient = new Ingredient();
        newIngredient.setExpiryDay(Date.valueOf(expireDatePicker.getValue()));
        newIngredient.setName(item.getItemName());
        newIngredient.setCategory(item.getCategory());
        newIngredient.setUnit(item.getUnit());
        newIngredient.setQuantity(item.getQuantity());
        Model.getInstance().getIngredients().add(newIngredient);
        line.setVisible(true);
        listItem.setDisable(true);
    }

}
