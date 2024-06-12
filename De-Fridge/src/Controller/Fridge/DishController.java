package Controller.Fridge;

import Model.Dish;
import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DishController implements Initializable {
    public Label nameLbl;
    private final Dish dish;
    public Button deleteBtn;
    public Button seeDetailBtn;
    private FridgeDishController controller;

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
        deleteBtn.setOnAction(event -> onDelete());
    }

    private void onDetail () {
        DishDetailController controller = new DishDetailController(this.dish, this);
        Model.getInstance().getViewFactory().showDishDetailView(controller);
    }

    private void onDelete () {
        deleteQuantity();
        Model.getInstance().getDishes().remove(this.dish);
        displayDish();
    }

    public void deleteQuantity () {

    }
    public void displayDish () {
        controller.displayDish();
    }
}
