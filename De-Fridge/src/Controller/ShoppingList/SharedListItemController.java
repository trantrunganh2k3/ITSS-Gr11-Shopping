package Controller.ShoppingList;

import Model.Ingredient;
import Model.Model;
import Model.ShoppingItems;
import dbController.ShoppingListController;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private final ShoppingItems item;
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
        }
    }
    private void init() {
        editBtn.setVisible(false);
        deleteBtn.setVisible(false);
        if (item.getBoughtBy() == null) {
            checkBtn.setVisible(false);
            addListener();
        } else {
            line.setVisible(true);
            checkBtn.setVisible(false);
        }
        nameTf.setText(item.getItemName());
        categoryCB.setValue(item.getCategory());
        quantityTf.setText(String.valueOf(item.getQuantity()));
        unitCB.setValue(item.getUnit());

    }
    private void addListener() {
        expireDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                checkBtn.setVisible(true);
            }
        });
        checkBtn.setOnAction(event -> {
            try {
                onCheck();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void onCheck () throws SQLException, ClassNotFoundException {
        //TODO: Add to fridge in Database
        Ingredient newIngredient = new Ingredient();
        item.setBoughtBy(Model.getInstance().getUser().getUsername());
        item.setPurchaseDay(Date.valueOf(LocalDate.now()));
        ShoppingListController.checkItem(item);

        newIngredient.setExpiryDay(Date.valueOf(expireDatePicker.getValue()));
        newIngredient.setName(item.getItemName());
        newIngredient.setCategory(item.getCategory());
        newIngredient.setUnit(item.getUnit());
        newIngredient.setQuantity(item.getQuantity());
        newIngredient.setPurchaseDay(Date.valueOf(LocalDate.now()));
        Model.getInstance().getIngredients().add(newIngredient);
        line.setVisible(true);
    }

}
