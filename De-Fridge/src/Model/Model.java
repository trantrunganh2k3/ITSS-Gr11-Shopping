package Model;

import View.ViewFactory;
import dbController.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;

    private User user;
    private ObservableList<Ingredient> ingredients;
    private ObservableList<Dish> dishes;
    private ObservableList<User> users;
    private ObservableList<FavoriteRecipe> recipes;
    private ObservableList<ShoppingList> shoppingLists;
    private ObservableList<ShoppingList> sharedShoppingLists;
    private ObservableList<Category> categories;
    private ObservableList<Unit> units;
    private Fridge fridge;


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

    public void setUser(User user){
        this.user = user;
    }
    public void setFridge() throws SQLException, ClassNotFoundException {this.fridge = FridgeController.getFridge(this.user);}
    public Fridge getFridge () throws SQLException, ClassNotFoundException {
        if (this.fridge == null) {
            setFridge();
        }
        return fridge;
    }

    public User getUser() {return this.user;}


    public void setCategories() {
        categories = CategoryController.listCate();
    }
    public ObservableList<Category> getCategories(){
        if (categories ==null) {
            setCategories();
        }
        return this.categories;
    }
    public void setUnits() {
        units = UnitController.listUnit();
    }
    public void setUnits() {
        units = UnitController.listUnit();
    }
    public ObservableList<Unit> getUnits(){
        if (units ==null) {
            setUnits();
        }
        return this.units;
    }

    public ObservableList<ShoppingList> getShoppingLists() throws SQLException, ClassNotFoundException {
        if (this.shoppingLists == null ) {
            return null;
        }
        return this.shoppingLists;
    }

    public void setSharedShoppingLists(Date date) throws SQLException, ClassNotFoundException {
        sharedShoppingLists = ShoppingListController.getShoppingList(user.getUsername(), date, false);

    }
    public ObservableList<ShoppingList> getSharedShoppingLists() {
        if (this.sharedShoppingLists == null ) {
            return FXCollections.observableArrayList();
        }
        return this.sharedShoppingLists;
    }

    public void setShoppingLists(Date date) throws SQLException, ClassNotFoundException {
        shoppingLists = ShoppingListController.getShoppingList(user.getUsername(),date,true);

    }

    public ObservableList<FavoriteRecipe> getRecipes() {
        if (recipes == null) {
            setRecipes();
        }
        return recipes;
    }

    public void setRecipes() {
        recipes = FXCollections.observableArrayList();
        recipes = FavoriteRecipeController.listRecipe(this.user.getUsername());
    }

    public void setIngredients() {
        this.ingredients = IngredientController.listIngre();
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
        dish1.setForDate(Date.valueOf("2024-06-12"));
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

    public void setUser(){
        users = FXCollections.observableArrayList();
        users = UserController.listUser();
    }

    public ObservableList<User> getUsers(){
        if (users == null) setUser();
        return this.users;
    }
}