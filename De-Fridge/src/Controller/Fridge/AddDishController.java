package Controller.Fridge;

import Model.Dish;
import Model.DishIngredient;
import Model.Model;
import dbController.DishController;
import dbController.IngredientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddDishController implements Initializable {
    public TextField nameDishLbl;
    public Button addIngredientBtn;
    public ListView ingredientLV;
    public ChoiceBox sectionChoiceBox;
    public Button saveBtn;
    public Button cancelBtn;
    private final FridgeDishController controller;
    public AddDishController(FridgeDishController controller) {
        this.controller = controller;
    }

    private ObservableList<DishIngredient> ingredients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sectionChoiceBox.getItems().addAll("Breakfast","Lunch","Dinner");
        ingredientLV.setItems(ingredients);
        addListener();
        ingredientLV.setCellFactory(lv -> new ListCell<DishIngredient>() {
            @Override
            protected void updateItem(DishIngredient item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/dishIngredient.fxml"));
                    DishIngredientController controller = new DishIngredientController(AddDishController.this, item);
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

    private void addListener () {
        addIngredientBtn.setOnAction(event -> onAddIngredient());
        saveBtn.setOnAction(event -> {
            try {
                onSubmit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        cancelBtn.setOnAction(event -> closeStage());
    }

    private void onAddIngredient(){
        ingredients.add(new DishIngredient());
    }

    public void removeIngredient (DishIngredient ingredient) {
        ingredients.remove(ingredient);
    }



    private void onSubmit () throws SQLException, ClassNotFoundException {
        if (nameDishLbl.getText() == null || sectionChoiceBox.getValue() ==null) {

        } else {
            for (DishIngredient ingredient: this.ingredients) {
                if (ingredient.getIngredientName() == null ||
                ingredient.getQuantity() == 0  ) {
                    return;
                }
            }
            Dish dish = new Dish();
            dish.setUsername(Model.getInstance().getUser().getUsername());
            dish.setForDate(Date.valueOf(controller.datePicker.getValue()));
            dish.setFridgeID(Model.getInstance().getFridge().getFridgeID());
            dish.setIngredients(ingredients);
            dish.setName(nameDishLbl.getText());
            dish.setMeal((String) sectionChoiceBox.getValue());
            for (DishIngredient dishIngredient: dish.getIngredients()) {
                IngredientController.updateQuan(dishIngredient.getQuantity(),dishIngredient.getIngredientId(),"-");
            }
            Model.getInstance().setIngredients();
            DishController.addDish(dish);
            controller.displayDish();
            closeStage();
        }
    }
    private void closeStage () {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
