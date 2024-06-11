package Controller.Fridge;

import Model.DishIngredient;
import Model.Model;
import Model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class DishIngredientController implements Initializable {
    public ChoiceBox nameChoiceBox;
    public Label unitLbl;
    public TextField quantityInput;
    public Button deleteBtn;

    public DishIngredient ingredient;
    private double maxQuantity;

    private AddDishController controller;
    private DishDetailController detailController;
    public DishIngredientController(AddDishController controller, DishIngredient ingredient) {
        this.controller = controller;
        this.ingredient = ingredient;
    }
    public DishIngredientController(DishDetailController controller, DishIngredient ingredient) {
        this.detailController = controller;
        this.ingredient = ingredient;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
        init();
    }

    private void addListener (){
        deleteBtn.setOnAction(event -> onDelete());

        nameChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                for (Ingredient ingredient: Model.getInstance().getIngredients()) {
                    if (ingredient.getName().equals(newValue)) {
                        this.ingredient.setIngredientId(ingredient.getIngredientID());
                        this.ingredient.setIngredientName(ingredient.getName());
                        this.ingredient.setUnit(ingredient.getUnit());
                        this.maxQuantity = ingredient.getQuantity() + this.ingredient.getQuantity();
                    }
                }
                if (this.ingredient != null) {
                    unitLbl.setText(this.ingredient.getUnit());
                    addLimitToQuantity();
                }
            }

        });
        quantityInput.textProperty().addListener((observable, oldValue, newValue) -> {
            this.ingredient.setQuantity(Double.valueOf(newValue));
        });
    }


    private void onDelete () {
        if (controller != null) {
            controller.removeIngredient(ingredient);
        } else {
            detailController.removeIngredient(ingredient);
        }

    }

    private void init() {
            ObservableList<String> options = FXCollections.observableArrayList();
            for (Ingredient ingredient: Model.getInstance().getIngredients()) {
                options.add(ingredient.getName());
            }

            nameChoiceBox.setItems(options);
            nameChoiceBox.setValue(this.ingredient.getIngredientName());


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
            TextFormatter<Double> textFormatter = new TextFormatter<>(new DoubleStringConverter(),this.ingredient.getQuantity() , filter);
            quantityInput.setTextFormatter(textFormatter);

            if (this.ingredient.getUnit() != null) {
                unitLbl.setText(this.ingredient.getUnit());
            }
    }
    private void addLimitToQuantity (){
        quantityInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    double value = Double.parseDouble(newValue);
                    if (value > maxQuantity) {
                        quantityInput.setText(String.valueOf(maxQuantity));
                    }
                    this.ingredient.setQuantity(value);
                } catch (NumberFormatException e) {
                    quantityInput.setText(oldValue); // Revert to old value if parse fails
                }
            }
        });
    }
}
