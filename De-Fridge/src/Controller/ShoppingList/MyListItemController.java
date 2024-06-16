package Controller.ShoppingList;

import Model.*;
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
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class MyListItemController implements Initializable {
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
    private MyListController controller;

    public MyListItemController(ShoppingItems item, MyListController controller) {
        this.item = item;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init() {

        if (item.getBoughtBy() == null) {
            UnaryOperator<TextFormatter.Change> filter = change -> {
                String newText = change.getControlNewText();
                if (newText.isEmpty()) {
                    return change;
                }
                try {
                    Double.parseDouble(newText);
                    return change;
                } catch (NumberFormatException e) {
                    return null;
                }
            };
            TextFormatter<Double> textFormatter = new TextFormatter<>(new DoubleStringConverter(),0.0, filter);
            quantityTf.setTextFormatter(textFormatter);
            checkBtn.setVisible(false);
            line.setVisible(false);
            nameTf.setText(item.getItemName());
            categoryCB.setValue(item.getCategory());
            quantityTf.setText(String.valueOf(item.getQuantity()));
            unitCB.getItems().clear();
            unitCB.setValue(item.getUnit());
            nameTf.setEditable(false);
            quantityTf.setEditable(false);
            addListener();
        } else {
            line.setVisible(true);
            expireDatePicker.setEditable(false);
            nameTf.setText(item.getItemName());
            categoryCB.setValue(item.getCategory());
            quantityTf.setText(String.valueOf(item.getQuantity()));
            unitCB.getItems().clear();
            unitCB.setValue(item.getUnit());
            nameTf.setEditable(false);
            quantityTf.setEditable(false);
        }

    }

    private void addListener () {
        editBtn.setOnAction(event -> onEdit());
        deleteBtn.setOnAction(event -> onDelete());
        categoryCB.valueProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue != null) {
               unitCB.setValue("");
               updateUnit(newValue.toString());
               item.setCategory(newValue.toString());
           }
        });
        nameTf.textProperty().addListener((observable, oldValue, newValue) -> {
            this.item.setItemName(newValue);
            }
        );
        quantityTf.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Double.parseDouble(newValue);
                this.item.setQuantity(Double.parseDouble(newValue));
            } catch (NumberFormatException e) {
                this.item.setQuantity(0);
            }}
        );
        unitCB.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                item.setUnit(newValue.toString());
            }
        });
        expireDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                checkBtn.setVisible(true);
            }
        });
        checkBtn.setOnAction(event -> {
            try {
                onCheck();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }
    private void onEdit() {
        nameTf.setEditable(true);
        quantityTf.setEditable(true);
        for (Category category:Model.getInstance().getCategories()){
            categoryCB.getItems().add(category.getCategory());
        }
        if (categoryCB.getValue() != null) {
            updateUnit(categoryCB.getValue().toString());
        }
        controller.startEdit();
        editBtn.setVisible(false);
        listItem.setStyle("-fx-background-color: #EAF4DA ");
    }

    public void onDelete () {
        controller.items.remove(this.item);
        controller.startEdit();
    }

    private void updateUnit(String currentCategory) {
        for (Category category:Model.getInstance().getCategories()){
            if (currentCategory.equals(category.getCategory())) {
                unitCB.getItems().clear();
                for (Unit unit: category.getUnits()){
                    unitCB.getItems().add(unit.getUnit());
                }
            }
        }
    }

    private void onCheck () throws SQLException, ClassNotFoundException {
        //TODO: Add to fridge
        Ingredient newIngredient = new Ingredient();
        item.setBoughtBy(Model.getInstance().getUser().getUsername());
        ShoppingListController.checkItem(item);
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
