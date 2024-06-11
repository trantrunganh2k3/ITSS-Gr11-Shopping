package Model;

import View.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;

    private ObservableList<Ingredient> ingredients;
    private ObservableList<Dish> dishes;

    public Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void setIngredients() {
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();

        ingredient1.setIngredientID(1);
        ingredient1.setName("Banana");
        ingredient1.setQuantity(2);
        ingredient1.setExpiryDay("2024-06-15");
        ingredient1.setUnit("kg");
        ingredient1.setCategory("Fruit");

        ingredient2.setIngredientID(2);
        ingredient2.setName("Lettuce");
        ingredient2.setQuantity(1);
        ingredient2.setExpiryDay("2024-06-15");
        ingredient2.setUnit("kg");
        ingredient2.setCategory("Vegetable");

        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();;
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        this.ingredients = ingredients;
    }

    public ObservableList<Ingredient> getIngredients() {
        if (ingredients == null) {
            setIngredients();
        }
        return this.ingredients;
    }

    public ObservableList<Dish> getDishes() {
        if (this.dishes == null) {
            setDishes();
        }
        return this.dishes;
    }
    public void setDishes() {
        dishes = FXCollections.observableArrayList();
        Dish dish1 = new Dish();
        Dish dish3 = new Dish();
        Dish dish2 = new Dish();

        DishIngredient ingredient = new DishIngredient();
        ingredient.setUnit("kg");
        ingredient.setIngredientId(1);
        ingredient.setIngredientName("Banana");
        ingredient.setQuantity(1);
        List<DishIngredient> list = new ArrayList<>();
        list.add(ingredient);

        dish1.setMeal("Breakfast");
        dish1.setName("Cereal");
        dish1.setForDate("2024-06-12");
        dish1.setIngredients(list);

        /*dish2.setMeal("Lunch");
        dish2.setName("Pho");
        dish2.setForDate("2024-06-12");

        dish3.setMeal("Dinner");
        dish3.setName("Pizza");
        dish3.setForDate("2024-06-12");*/

        this.dishes.add(dish1);
        //this.dishes.add(dish2);
        //this.dishes.add(dish3);
    }
}