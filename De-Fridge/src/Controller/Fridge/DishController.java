package Controller.Fridge;

import Model.Dish;
import Model.Model;
import Model.DishIngredient;
import dbController.DishIngredientController;
import dbController.IngredientController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DishController implements Initializable {
    public Label nameLbl;
    private final Dish dish;
    public Button deleteBtn;
    public Button seeDetailBtn;
    private final FridgeDishController controller;

    public DishController(Dish dish, FridgeDishController controller){
        this.dish = dish;
        this.controller = controller;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLbl.setText(dish.getName());
        addListener();
    }

    private void addListener () {
        seeDetailBtn.setOnAction(event -> onDetail());
        deleteBtn.setOnAction(event -> {
            try {
                onDelete();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onDetail () {
        DishDetailController controller = new DishDetailController(this.dish, this);
        Model.getInstance().getViewFactory().showDishDetailView(controller);
    }

    private void onDelete () throws SQLException, ClassNotFoundException {
        deleteQuantity();
        for (DishIngredient dishIngredient: dish.getIngredients()) {
            IngredientController.updateQuan(dishIngredient.getQuantity(),dishIngredient.getIngredientId(),"+");
        }
        Model.getInstance().setIngredients();
        dbController.DishController.deleteDish(this.dish.getDishID());
        Model.getInstance().setDishes(Date.valueOf(controller.datePicker.getValue()));
        displayDish();
    }

    public void deleteQuantity () {

    }
    public void displayDish () {
        controller.displayDish();
    }
}
