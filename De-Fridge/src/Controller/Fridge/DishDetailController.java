package Controller.Fridge;

import Model.Dish;
import Model.DishIngredient;
import Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DishDetailController implements Initializable {
    public TextField nameLbl;
    public Button addIngredientBtn;
    public ListView ingredientLV;
    public ChoiceBox sectionCB;
    public Button saveBtn;
    public Button editBtn;
    public Button cancelBtn;

    private Dish dish;
    public ObservableList<DishIngredient> ingredients;
    private final DishController controller;

    public DishDetailController(Dish dish, DishController controller) {
        this.dish = dish;
        this.controller = controller;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();

    }
    public void removeIngredient (DishIngredient ingredient) {
        ingredients.remove(ingredient);
    }

    private void addListener () {
        cancelBtn.setOnAction(event -> init());
        editBtn.setOnAction(event -> edit());
        addIngredientBtn.setOnAction(event -> onAddIngredient());
        saveBtn.setOnAction(event -> onSubmit());
    }
    private void onAddIngredient(){
        ingredients.add(new DishIngredient());
    }


    private void init() {
        nameLbl.setText(this.dish.getName());
        sectionCB.getItems().addAll("Breakfast","Lunch","Dinner");
        sectionCB.setValue(this.dish.getMeal());
        ingredients = FXCollections.observableArrayList();
        for (DishIngredient ingredient: dish.getIngredients()){
            DishIngredient dishIngredient = new DishIngredient();
            dishIngredient.setQuantity(ingredient.getQuantity());
            dishIngredient.setIngredientId(ingredient.getIngredientId());
            dishIngredient.setUnit(ingredient.getUnit());
            dishIngredient.setDishID(ingredient.getDishID());
            dishIngredient.setIngredientName(ingredient.getIngredientName());
            ingredients.add(dishIngredient);
        }
        ingredientLV.setItems(ingredients);
        ingredientLV.setCellFactory(lv -> new ListCell<DishIngredient>() {

            @Override
            protected void updateItem(DishIngredient item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/dishIngredient.fxml"));
                    DishIngredientController controller = new DishIngredientController(DishDetailController.this, item);
                    loader.setController(controller);
                    setText(null);
                    try {
                        setGraphic(loader.load());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    setDisable(true);
                }
            }
        });
        ingredientLV.setEditable(false);
        nameLbl.setEditable(false);
        addIngredientBtn.setVisible(false);
        cancelBtn.setVisible(false);
        saveBtn.setVisible(false);
        sectionCB.setDisable(true);
        editBtn.setVisible(true);
    }

    private void edit () {
        ingredientLV.setItems(ingredients);
        ingredientLV.setCellFactory(lv -> new ListCell<DishIngredient>() {

            @Override
            protected void updateItem(DishIngredient item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/dishIngredient.fxml"));
                    DishIngredientController controller = new DishIngredientController(DishDetailController.this, item);
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
        ingredientLV.setEditable(true);
        nameLbl.setEditable(true);
        addIngredientBtn.setVisible(true);
        cancelBtn.setVisible(true);
        saveBtn.setVisible(true);
        sectionCB.setDisable(false);
        editBtn.setVisible(false);
    }

    private void onSubmit () {
        if (nameLbl.getText() == null || sectionCB.getValue() ==null) {

        } else {
            for (DishIngredient ingredient: this.ingredients) {
                if (ingredient.getIngredientName() == null ||
                        ingredient.getQuantity() == 0  ) {
                    return;
                }
            }
            //TODO: Add the quantity of ingredient, and substract it to
            Dish dish = new Dish();
            dish.setIngredients(ingredients);
            dish.setName(nameLbl.getText());
            dish.setMeal((String) sectionCB.getValue());
            Model.getInstance().getDishes().remove(this.dish);
            Model.getInstance().getDishes().add(dish);
            controller.displayDish();
            closeStage();
        }
    }
    private void closeStage () {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

}
